package com.example.string;

/**
 * @author: GuanBin
 * @date: Created in 下午1:44 2021/4/19
 */
public class StringTest {
    public static void main(String[] args) {
        String a="1"+"1";
        String a1=new String("11");
        String a2=new String("11");
        String b="11";
        System.out.println(a==a1);
        System.out.println(a==b);
        System.out.println(a1==a2);
    }
}
