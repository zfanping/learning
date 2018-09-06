package nia.ch04;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wshcatkin on 2018-08-18.
 */
public class PlainBioServer {
    public void serve(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            final Socket socket = serverSocket.accept();
            System.out.println(
                    "Accepted connection from " + socket);
            new Thread(new Runnable() {
                public void run() {
                    OutputStream out;
                    try {
                        out = socket.getOutputStream();
                        out.write("Hi!\r\n".getBytes());
                        out.flush();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }
}
