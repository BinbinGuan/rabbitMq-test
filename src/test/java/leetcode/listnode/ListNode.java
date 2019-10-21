package leetcode.listnode;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 下午3:06 2019/8/19
 */
public class ListNode {
    int val;
    ListNode nextNode;

    ListNode(int val) {
        this.val = val;
        this.nextNode = null;
    }


    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a", "b", "c");
        List<String> m = Arrays.asList("a", "b", "c");

        for(String s: strings){
            System.out.println(s);
            m.stream().filter(t-> StringUtils.equalsIgnoreCase(t,"a")).findAny().ifPresent(k->{
                System.out.println("结束");

            });
            System.out.println(s);
        }
    }

}
