package com.example.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ReadLog {
    public static void main(String[] args) {
        /*此处有一个巧合：这里ArrayBlockingQueue<String>(1)和ArrayBlockingQueue<String>(16)
         * 达到的效果一样，并且前者产生的数据组合更整齐；目前推测是巧合，希望大牛发现因果了告知一声
         */
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            String log = queue.take();
                            parseLog(log);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        /*
         * 模拟处理16个日志，下面代码产生了16个日志对象；当前代码需要运行16秒才能打印完成这些日志对象;
         * 修改程序代码，开四个线程让16个对象在4秒内打完
         */
        for (int i = 0; i < 16; i++) { // 这行代码不能改动
            final String log = "" + (i + 1); // 这行代码不能改动
            {
                // ReadLog.parseLog(log);
                try {
                    queue.put(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // parseLog内部代码不能动
    public static void parseLog(String log) {
        System.out.println(log + ":" + System.currentTimeMillis() / 1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
