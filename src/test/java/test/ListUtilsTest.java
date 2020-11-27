package test;

import org.apache.commons.collections4.ListUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 上午10:02 2020/10/16
 */
public class ListUtilsTest {

    public static void main(String[] args) {
        List<String> a1 = Arrays.asList("a", "b", "c");
        List<String> a2 = Arrays.asList("e", "f", "g");
        List<String> a3 = Arrays.asList("a", "o", "p");
        List<String> a4 = Arrays.asList("");
        List<String> a5 = Arrays.asList("");

        //两者都有数据，无任何交集
        System.out.println(ListUtils.retainAll(a1,a2));
        //两者都有数据，有交集
        System.out.println(ListUtils.retainAll(a1,a3));
        //一方有数据，一方为空，无任何交集
        System.out.println(ListUtils.retainAll(a1,a4));
        //一方有数据，一方为null，无任何交集
        System.out.println(ListUtils.retainAll(a1,a5));
        //两者都无数据
        System.out.println(ListUtils.retainAll(a4,a5));
    }
}
