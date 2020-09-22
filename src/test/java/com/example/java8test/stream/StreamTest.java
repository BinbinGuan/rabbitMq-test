package com.example.java8test.stream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: GuanBin
 * @date: Created in 下午5:40 2020/8/25
 */
public class StreamTest {

    public static void main(String[] args) {

        int year = LocalDateTime.now().getYear();

        Stream<String> stream1 = Stream.of("a", "b", "c");

        List<String> collect = stream1.map(String::toUpperCase).collect(Collectors.toList());

        System.out.println("转换之后的数据" + collect);

        String str = stream1.collect(Collectors.joining(",")).toString();
        System.out.println(str);
        //转换为 Array
        String[] strings = stream1.toArray(String[]::new);
        //转化 Collection
        List<String> list1 = stream1.collect(Collectors.toList());
        List<String> list2 = stream1.collect(Collectors.toCollection(ArrayList::new));
        List<String> list3 = stream1.collect(Collectors.toCollection(Stack::new));


        Set<String> set1 = stream1.collect(Collectors.toSet());


    }
}
