/***
* Author: Sam Florin
* Created: 2019/02/22
* Description: A webserver which sends a client-request to a remote webserver and displays the output.
***/

import java.net.*;
import java.io.*;

public class HTTPAsk {
    public static void main( String[] args) throws IOException{
      int port = 0;
      String host = null;
      String ToServer = null;

      String clientDataBuffer;
      ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(args[0]));
      while(true) {
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        StringBuilder serverResponse = new StringBuilder();

        clientDataBuffer = inFromClient.readLine();
        String[] parts = clientDataBuffer.split("[\\=&?\\u0020]");

        for(int i = 0; i < parts.length-1; i++) {
          if (parts[i].equals("hostname")) {host = parts[i+1];}
          if  (parts[i].equals("port")) {port = Integer.parseInt(parts[i+1]);}
          if (parts[i].equals("string")) {ToServer = parts[i+1];}
        }

        outToClient.writeBytes("HTTP/1.1 200 OK\r\n\r\n");
        outToClient.writeBytes(TCPClient.askServer(host, port, ToServer));
        connectionSocket.close();
      }
    }
}
