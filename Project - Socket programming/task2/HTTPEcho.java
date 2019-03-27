/***
* Author: Sam Florin
* Date: 2019/02/27
* Description: A webserver that echoes back the HTTP response data.
***/

import java.net.*;
import java.io.*;

public class HTTPEcho {
    public static void main( String[] args) throws IOException {
        String clientDataBuffer;
        ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(args[0]));
        while(true) {
          Socket connectionSocket = welcomeSocket.accept();
          BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
          DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
          StringBuilder serverResponse = new StringBuilder();

          while(!(clientDataBuffer = inFromClient.readLine()).isEmpty()) {
            serverResponse.append(clientDataBuffer + "\r\n");
          }
          
          outToClient.writeBytes("HTTP/1.1 200 OK\r\n\r\n");
          outToClient.writeBytes(serverResponse.toString());
          connectionSocket.close();
    }
  }
}
