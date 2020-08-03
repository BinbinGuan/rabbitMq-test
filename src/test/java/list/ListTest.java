package list;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author: GuanBin
 * @date: Created in 下午1:47 2020/4/1
 */
public class ListTest {

    public static void main(String[] args) {
//        List<String> strings = Arrays.asList("a", "d", "c");
////        strings.add("d");
//
//       List<String> strings1 = new ArrayList<>(strings);
////        Collections.addAll(strings1,"d");
//        strings1.add("d");
//        System.out.println(strings1.size()+""+strings1);

//        Set<String> sets = new HashSet<>();
//        sets.add("a");
//        System.out.println(sets.contains("a"));

        List<String> test= new ArrayList<>();
        test.add("a");
        test.add("b");
        test.add("c");

        test.forEach(s -> {
            if(StringUtils.equalsIgnoreCase("b",s)){
                return;
            }
            System.out.println(s);

        });


        test.stream().anyMatch(s -> {
            System.out.println(s);
            return StringUtils.equalsIgnoreCase(s,"b");
        });

      System.out.println(StringUtils.containsIgnoreCase("server:123456",""));


    }
}
