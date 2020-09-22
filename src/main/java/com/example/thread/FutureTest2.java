package com.example.thread;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTest2 {
    public static void main(String[] args) {
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for (int i = 1; i <= 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(2000));
                    return seq;
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(
                        completionService.take().get());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("======");
        threadPool2.shutdown();
    }
}
