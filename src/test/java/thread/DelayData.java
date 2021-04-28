package thread;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayData implements Delayed {

    private Integer number;
    private long delayTime=5;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.delayTime;
    }

    @Override
    public int compareTo(Delayed o) {
        DelayData compare= (DelayData)o;
        return this.number.compareTo(compare.getNumber());
    }

    public static void main(String[] args) {
        DelayQueue<DelayData> queue = new DelayQueue<DelayData>();
//        DelayData delayData = new DelayData();
//        delayData.setNumber(10);
        queue.add(new DelayData());
        while (true){
            try {
                DelayData take = queue.take();
                System.out.println(take.getNumber());
            }catch (Exception e){

            }
        }

    }

}
