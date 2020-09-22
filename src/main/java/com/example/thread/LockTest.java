package com.example.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        new LockTest().init();
    }

    private void init(){
        final Outputer outputer = new Outputer();
        new Thread(new Runnable(){
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output("zhangxiaoxiang");
                }

            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output("lihuoming");
                }

            }
        }).start();

    }

    static class Outputer{
        Lock lock = new ReentrantLock();
        public void output(String name){
            lock.lock();
            try{
                System.out.println(name);
            }finally{
                lock.unlock();
            }
        }
    }
}
