package com.example.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class ReadLog1 {
    public static void main(String[] args) {

        //使用semaphore信号灯相当于上一个lock锁
        final Semaphore semaphore = new Semaphore(1);
        //新的队列方式
        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        String input = queue.take();
                        String output = TestDo.doSome(input);
                        System.out.println(Thread.currentThread().getName() + ":" + output);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));

        for (int i = 0; i < 10; i++) { // 这行代码不能改动
            String input = i + ""; // 这行代码不能改动
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// TestDo类不能动
class TestDo {

    public static String doSome(String input) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String output = input + ":" + (System.currentTimeMillis() / 1000);
        return output;
    }

}
