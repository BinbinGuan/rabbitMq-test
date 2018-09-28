package com.example.java8test.sort;

/**
 * @author: GuanBin
 * @date: Created in 下午6:14 2018/9/7
 */
public class RegTest {

    public static void main(String[] args) {
//        ^[a-zA-Z0-9][a-zA-Z0-9_-]{5,19}$

        //只能以字母或数字开头，可以包含字母、数字、下划线、连接符及64个字符只能
        String regName="^[A-Za-z0-9][A-Za-z0-9_-]{1,63}$";

//        String reg1 = "^(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]+$";
//        String reg = "[a-zA-Z0-9\\S]+$";
        System.out.println("123".matches(regName));//1true
        System.out.println("123@@".matches(regName));//2false
        System.out.println("zzzzzzzzzzz".matches(regName));//3true
        System.out.println("zzzzzzzz@@zzz".matches(regName));//4false
        System.out.println("sss123".matches(regName));//5true
        System.out.println("sss123-".matches(regName));//6true
        System.out.println("sss_123-".matches(regName));//7true
        System.out.println("sgg123@@$@".matches(regName));//8false
        System.out.println("sgg@123_".matches(regName));//9false
        System.out.println("sgg_".matches(regName));//10true
        System.out.println("sgg.".matches(regName));//11false
        System.out.println("sgg_.".matches(regName));//12false
        System.out.println("123sgg_.".matches(regName));//13false
        System.out.println("123-sgg_.".matches(regName));//14false
        System.out.println("123-sgg_".matches(regName));//15true
        System.out.println(("1asdfakdkasdfasdfasdfasjfaksdjfffffffffffffffffffff").matches(regName));//16true
        System.out.println("-123-sgg_".matches(regName));//17false


    }
}
