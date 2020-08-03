package thread.timer;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: GuanBin
 * @date: Created in 上午11:41 2020/7/13
 */
public class TimerTest {
    public static void main(String[] args) {
        new Timer().schedule(new TimerTask(){
            public void run() {
                try {
                    System.out.println("execute task!  "+new SimpleDateFormat("yyyyMMddHHmmss").format(this.scheduledExecutionTime()));
                    Random random = new Random();
                    int slpTime = random.nextInt(3)*1000 + 4000;
                    System.out.println("exec time:" + slpTime);
                    Thread.sleep(slpTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1000, 5 * 1000);
    }
}
