package com.example.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: GuanBin
 * @date: Created in 下午10:38 2019/8/27
 */
public class SingelThreadPool {
    @Test
    public void test() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        MyThread thread3 = new MyThread();
        MyThread thread4 = new MyThread();
        singleThreadExecutor.execute(thread1);
        singleThreadExecutor.execute(thread2);
        singleThreadExecutor.execute(thread3);
        singleThreadExecutor.execute(thread4);

    }


    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在执行。。。");
        }
    }
}
