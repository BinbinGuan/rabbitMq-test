package thread;

/**
 * @author: GuanBin
 * @date: Created in 下午11:14 2020/6/7
 */
public class TestThread2 {
    public static void main(String args[]) {
        ThreadDemo T1 = new ThreadDemo( "Thread-1");
        T1.start();

        ThreadDemo T2 = new ThreadDemo( "Thread-2");
        T2.start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
    }
}
