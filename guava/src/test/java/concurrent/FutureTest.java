package concurrent;

import junit.framework.TestCase;

import java.util.concurrent.*;

/**
 * Created by wshcatkin on 2018-09-16.
 */
public class FutureTest extends TestCase {
    public void test() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<Integer> result = pool.submit(new Task());

        Thread.sleep(1000);


        System.out.println("主线程在执行任务");

        System.out.println("task运行结果" + result.get());

        System.out.println("所有任务执行完毕");
    }

    public void test_future_task() throws InterruptedException, ExecutionException {
        Task task = new Task();
        FutureTask<Integer> f = new FutureTask<>(task);
        Thread thread = new Thread(f);
        thread.start();
        Thread.sleep(1000);


        System.out.println("主线程在执行任务");

        System.out.println("task运行结果" + f.get());

        System.out.println("所有任务执行完毕");
    }

    private static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++)
                sum += i;
            return sum;
        }
    }
}
