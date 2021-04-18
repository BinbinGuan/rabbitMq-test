package test;

import org.junit.platform.commons.util.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: GuanBin
 * @date: Created in 下午2:24 2021/3/23
 */
public class CursorTest {
    private static AtomicInteger cursor = new AtomicInteger(0);

    public static void main(String[] args) {
        int a=10;
        a=a-1;
        System.out.println(a);

        StringBuffer buffer= new StringBuffer("abcdefg");
        buffer.insert(2,"好人");
        System.out.println(buffer.toString());
        cursor.getAndIncrement();
        System.out.println(cursor);
    }
}
