package thread;

/**
 * @author: GuanBin
 * @date: Created in 下午11:24 2020/6/7
 */
public class DisplayMessage implements Runnable {
    private String message;

    public DisplayMessage(String message) {
        this.message = message;
    }

    public void run() {
        while(true) {
            System.out.println(message);
        }
    }
}
