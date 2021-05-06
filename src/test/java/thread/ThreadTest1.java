package thread;

import java.util.LinkedList;

/**
 * @author: GuanBin
 * @date: Created in 下午3:40 2021/4/19
 */
public class ThreadTest1 {
    private LinkedList list =new LinkedList();

    private synchronized void put(Object x){
        synchronized (list){
            list.add(x);
            notify();
        }
    }

    private synchronized void pop() throws InterruptedException {
        if(list.size()<=0){
            wait();
        }
        list.removeLast();
    }

    public static void main(String[] args) {
        ThreadTest1 threadTest1 = new ThreadTest1();
        threadTest1.put(1);

        new Thread(()->{
            threadTest1.put("a");
        }).start();
        new Thread(()->{
            try {
                threadTest1.pop();
                threadTest1.pop();
                threadTest1.pop();
                threadTest1.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            threadTest1.put("b");
        }).start();
        new Thread(()->{
            threadTest1.put("c");
        }).start();
        new Thread(()->{
            try {
                threadTest1.pop();
                threadTest1.pop();
                threadTest1.pop();
                threadTest1.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(threadTest1.list.toString());
    }
}
