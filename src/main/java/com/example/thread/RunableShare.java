package com.example.thread;

public class RunableShare {
    public static void main(String[] args) {
        final ShareData1 shareData1 = new ShareData1();
        new Thread(new MyRunnable1(shareData1)).start();
        new Thread(new MyRunnable1(shareData1)).start();
    }
    static class MyRunnable1 implements Runnable{
        private ShareData1 shareData1;
        public void run() {
            System.out.printf(shareData1.a);
        }
        public MyRunnable1(ShareData1 shareData1){
            this.shareData1 = shareData1;
        }
    }
    static class MyRunnable2 implements Runnable{
        private ShareData1 shareData1;
        public void run() {
            System.out.printf(shareData1.a);
        }
        public MyRunnable2(ShareData1 shareData1){
            this.shareData1 = shareData1;
        }
    }
    static class ShareData1 {

        private String a="Test";
    }
}
