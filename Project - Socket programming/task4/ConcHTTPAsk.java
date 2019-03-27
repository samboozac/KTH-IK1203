/***
* Author: Sam Florin
* Created: 2019/02/22
* Description: A concurrent webserver which sends a client-request to a remote webserver and displays the output.
***/

import java.net.*;
import java.io.*;
import java.lang.Runnable;

public class ConcHTTPAsk {
  public static void main(String[] args) throws IOException {
    ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(args[0]));
    while(true) {
      MyRunnable myrun = new MyRunnable(welcomeSocket.accept());
      Thread thread = new Thread(myrun);
      thread.start();
    }
  }
}
