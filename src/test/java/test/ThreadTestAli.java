package test;

import java.util.LinkedList;

/**
 * @author: GuanBin
 * @date: Created in 下午11:58 2021/4/18
 */
public class ThreadTestAli {
  private LinkedList list=  new LinkedList();

  private synchronized void add(Object x){
      synchronized (list){
          list.add(x);
          notify();
      }
  }
  private synchronized void pop() throws InterruptedException {
      synchronized (list){
         if(list.size()<=0){
             wait();
         }
         list.removeLast();
      }
  }
}
