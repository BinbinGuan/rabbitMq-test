package com.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
//        ExecutorService threadPool =  Executors.newSingleThreadExecutor();
//        Future<String> future =
//                threadPool.submit(
//                        new Callable<String>() {
//                            public String call() throws Exception {
//                                Thread.sleep(1000);
//                                return "hello";
//                            };
//                        }
//                );
//        System.out.println("....");
//        try {
//            System.out.println("get value:" + future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // 创建线程池
        ExecutorService threadPool =  Executors.newFixedThreadPool(3);
        List<FutureTask<String>> tasks = new ArrayList<FutureTask<String>>();
        for (int i = 0; i < 10; i++) {
            FutureTask<String> futureTask = new FutureTask<String>(new ThreadPoolTask(i));
            threadPool.submit(futureTask);
            tasks.add(futureTask);
        }
        for (FutureTask<String> futureTask : tasks) {
            try {
                // 阻塞一直等待执行完成拿到结果
                System.out.println("future result:" + futureTask.get());
                // 阻塞一直等待执行完成拿到结果，如果在超时时间内，没有拿到则抛出异常
                // System.out.println("future result:"+futureTask.get(1,TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } // 捕获超时异常
            // catch (TimeoutException e) {
            // e.printStackTrace();
            // }
        }
        System.out.println("--------------------------");
        threadPool.shutdown();
    }
    public static class ThreadPoolTask implements Callable<String> {
        private int value;
        public ThreadPoolTask(int value) {
            this.value = value;
        }
        public String call() throws Exception {
            System.out.println("value-----" + value++);
            Thread.sleep(2000);
            return String.valueOf(value);
        }
    }
}
