package monitor;

/**
 * @author: GuanBin
 * @date: Created in 下午3:00 2020/6/11
 */
public class JstackTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Thread1());
        thread.start();
    }
}

class Thread1 implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println(1);
        }
    }
}
