package com.example.java8test.sort;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 下午8:18 2018/12/10
 */
public class RemoveTest {
    public static void main(String[] args) {

        //https://www.cnblogs.com/dolphin0520/p/3933551.html
        //1.Arrays.asList("a", "b", "c", "d", "d")执行remove操作会抛UnsupportedOperationException异常，是因为java.util.Arrays$ArrayList中未从


        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        for (String str : strings) {
            if (StringUtils.endsWithIgnoreCase(str, "a")) {
                strings.remove(str);
            }
        }
        System.out.println(strings);

        Collections.emptyList();

        ArrayList<Object> objects = new ArrayList<>();

        Collections.emptyMap();

        Collections.emptySet();
    }
}
