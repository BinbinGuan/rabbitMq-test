package com.example.function;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author: GuanBin
 * @date: Created in 下午6:07 2020/5/3
 */
public class SupplierTest {
    public static void main(String[] args) {
        //① 使用Supplier接口实现方法,只有一个get方法，无参数，返回一个值
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return new Random().nextInt();
            }
        };

        System.out.println(supplier.get());

        System.out.println("********************");

        //② 使用lambda表达式，
        supplier = () -> new Random().nextInt();
        System.out.println(supplier.get());
        System.out.println("********************");

        //③ 使用方法引用
        Supplier<Double> supplier2 = Math::random;
        System.out.println(supplier2.get());


    }
}
