package netty.guide.ch02.bio;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by frank on 2018-08-03.
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            while (true) {
                String body = in.readLine();
                if (body == null) break;

                System.out.println("The time server receive order: " + body);
                boolean invaidOrder = "QUERY TIME ORDER".equals(body);
                out.write(invaidOrder ? new Date().toString() : "BAD ORDER");
                out.println();
                out.flush();

            }
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                if (out != null) {
                    out.close();
                    out = null;
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    this.socket = null;
                }
            }
        }
    }
}
