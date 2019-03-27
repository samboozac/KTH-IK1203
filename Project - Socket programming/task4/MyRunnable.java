/***
* Author:
* Date:
* Description: To DO: Try Catch..(nested)
***/


import java.net.*;
import java.io.*;
import java.lang.Runnable;

public class MyRunnable implements Runnable {
  private Socket clientSocket;
  public MyRunnable(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public void run() {
    int port = 0;
    String host = null;
    String ToServer = null;
    String clientDataBuffer;
      try {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
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
        clientSocket.close();
      } catch(IOException e) {}

  }
}
