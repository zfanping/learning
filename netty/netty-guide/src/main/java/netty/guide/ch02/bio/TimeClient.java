package netty.guide.ch02.bio;

import java.io.*;
import java.net.Socket;

/**
 * Created by frank on 2018-08-03.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 50000;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            System.out.println("connect ...");
            socket = new Socket("127.0.0.1", port);
            System.out.println("connected");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            System.out.println("sending ... ");
            out.println("QUERY TIME ORDER");

            System.out.println("receving ... ");
            String resp = in.readLine();
            System.out.println("resp = " + resp);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
