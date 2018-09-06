package netty.guide.ch21;

import java.util.concurrent.TimeUnit;

/**
 * Created by wshcatkin on 2018-08-18.
 */
public class ThreadStopExample {
    private static boolean stop;

    public static void main(String[] args) throws InterruptedException {
        Thread workThread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        workThread.start();

        TimeUnit.SECONDS.sleep(3);
        stop = true;
    }
}
