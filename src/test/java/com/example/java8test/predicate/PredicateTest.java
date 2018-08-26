package com.example.java8test.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: GuanBin
 * @date: Created in 下午5:14 2018/8/8
 */
public class PredicateTest {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //输出大于5的数
        PredicateTest predicateTest = new PredicateTest();
        List<Integer> list = predicateTest.conditionFilter(integers, integer -> integer > 5);
        System.out.println("输出大于5的数" + list);
        list.forEach(System.out::println);
        //输出小于5的数
        List<Integer> gtIntegerList = predicateTest.conditionFilter(integers, integer -> integer < 5);
        System.out.println("输出小于5的数" + gtIntegerList);
        gtIntegerList.forEach(System.out::println);
        //输出所有的数
        List<Integer> trueIntegerList = predicateTest.conditionFilter(integers, integer -> true);
        System.out.println("输出所有的数" + trueIntegerList);
        trueIntegerList.forEach(System.out::println);

        //输出大于5且是偶数的数字
        List<Integer> list1 = predicateTest.conditionFilterAnd(integers, integer -> integer > 5, integer1 -> integer1 % 2 == 0);
        System.out.println("输出大于5且是偶数的数字" + list1);
        list1.forEach(System.out::println);

        //输出大于5或是偶数的数字
        List<Integer> list2 = predicateTest.conditionFilterOr(integers, integer -> integer > 5, integer1 -> integer1 % 2 == 0);
        System.out.println("输出大于5或是偶数的数字" + list2);
        list2.forEach(System.out::println);

        //测试negate方法
        List<Integer> list3 = predicateTest.conditionFilterNegate(integers, integer -> integer > 5, integer1 -> integer1 % 2 == 0);
        System.out.println("测试negate方法" + list3);
        list3.forEach(System.out::println);

        //Predicate.isEqual方法
        List<Integer> collect = integers.stream().filter(Predicate.isEqual(8)).collect(Collectors.toList());
        System.out.println("isEqual方法"+collect);

    }


    private List<Integer> conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    private List<Integer> conditionFilterAnd(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate1) {
        return list.stream().filter(predicate.and(predicate1)).collect(Collectors.toList());
    }

    private List<Integer> conditionFilterOr(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate1) {
        return list.stream().filter(predicate.or(predicate1)).collect(Collectors.toList());
    }

    private List<Integer> conditionFilterNegate(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate1) {
        return list.stream().filter(predicate.negate().or(predicate1.negate())).collect(Collectors.toList());
    }

}
