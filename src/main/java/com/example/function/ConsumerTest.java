package com.example.function;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author: GuanBin
 * @date: Created in 下午6:02 2020/5/3
 */
public class ConsumerTest {

    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {

            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer);
    }


}
