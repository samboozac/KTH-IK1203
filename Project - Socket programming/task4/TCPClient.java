/***
* Creator: Sam Florin
* Date: 2019-02-11
* Description: TCP-client socket connection
***/

import java.net.*;
import java.io.*;

public class TCPClient {
  public static String askServer(String hostname, int port, String ToServer) throws  IOException {
    if(ToServer == null) {
      return askServer(hostname, port);
    } else {
      String FromServer = "";
      Socket clientSocket = new Socket(hostname, port);
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader inFromServer =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

      outToServer.writeBytes(ToServer + '\n');
      StringBuilder sb = new StringBuilder();
      clientSocket.setSoTimeout(3000);

      try {
        while((FromServer = inFromServer.readLine()) != null) {
          sb.append(FromServer + "\n");
        }
      }
      catch(SocketTimeoutException s) {
      }

      clientSocket.close();
      return sb.toString();
    }
  }

  public static String askServer(String hostname, int port) throws  IOException, SocketTimeoutException {
    String FromServer = "";
    Socket clientSocket = new Socket(hostname, port);
    BufferedReader inFromServer =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    StringBuilder sb = new StringBuilder();
    clientSocket.setSoTimeout(3000);
    try {
      while((FromServer = inFromServer.readLine()) != null) {
        sb.append(FromServer + "\n");
      }
    }
    catch(SocketTimeoutException s) {
    }
    clientSocket.close();
    return sb.toString();
  }
}
