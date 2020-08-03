package com.example.function;

import java.util.function.Predicate;

/**
 * @author: GuanBin
 * @date: Created in 下午6:10 2020/5/3
 */
public class PredicateTest {
    public static void main(String[] args) {
        //① 使用Predicate接口实现方法,只有一个test方法，传入一个参数，返回一个bool值
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if(integer > 5){
                    return true;
                }
                return false;
            }
        };

        System.out.println(predicate.test(6));

        System.out.println("********************");

        //② 使用lambda表达式，
        predicate = (t) -> t > 5;
        System.out.println(predicate.test(1));
        System.out.println("********************");
    }
}
