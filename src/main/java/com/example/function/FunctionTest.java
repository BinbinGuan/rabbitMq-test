package com.example.function;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author: GuanBin
 * @date: Created in 下午6:12 2020/5/3
 */
public class FunctionTest {
    public static void main(String[] args) {
        //① 使用map方法，泛型的第一个参数是转换前的类型，第二个是转化后的类型
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();//获取每个字符串的长度，并且返回
            }
        };

        Stream<String> stream = Stream.of("aaa", "bbbbb", "ccccccv");
        Stream<Integer> stream1 = stream.map(function);
        stream1.forEach(System.out::println);

        System.out.println("********************");
    }
}
