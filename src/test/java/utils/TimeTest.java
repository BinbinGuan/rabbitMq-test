package utils;

/**
 * @author: GuanBin
 * @date: Created in 下午4:06 2021/3/27
 */
public class TimeTest {

    public static void main(String[] args) {
        int a = 99999999;
        long recycleRetentionTime = System.currentTimeMillis()+ a * 24 * 60 * 60 * 1000L;

        System.out.println(recycleRetentionTime);
    }
}
