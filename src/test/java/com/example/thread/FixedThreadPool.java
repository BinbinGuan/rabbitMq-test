package com.example.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: GuanBin
 * @date: Created in 下午10:38 2019/8/27
 */
public class FixedThreadPool {
    @Test
    public void test() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            MyThread myThread = new MyThread();
            fixedThreadPool.execute(myThread);

        }
        fixedThreadPool.shutdown();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在执行。。。");
        }
    }
}
