package utils;

import java.util.Random;

/**
 * @author: GuanBin
 * @date: Created in 上午11:18 2020/3/18
 */
public class NumberTest {

    public static void main(String[] args) {
        Random random = new Random();
        for(int i=0;i<10;i++){
            int n = random.nextInt(5);
            System.out.println(n);
        }
    }
}
