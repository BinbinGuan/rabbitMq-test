package com.example.thread;

import java.util.Random;

public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    public static void main(String[] args) {

        //启动两个线程
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //创建每个线程私有的变量
                    int data = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName()+" has put data: "+data);
                    //往local里面设置值
                    threadLocal.set(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A{
        public void get(){
            int data =threadLocal.get();
            System.out.println("A from "+Thread.currentThread().getName()+" has get data: "+data);
        }
    }

    static class B{
        public void get(){
            int data =threadLocal.get();
            System.out.println("B from "+Thread.currentThread().getName()+" has get data: "+data);
        }
    }

}
