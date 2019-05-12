package com.example.java8test.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: GuanBin
 * @date: Created in 下午6:14 2018/9/7
 */
public class RegTest {

    public static void main(String[] args) {
//        ^[a-zA-Z0-9][a-zA-Z0-9_-]{5,19}$
//        (?<!bc)
        //只能以字母或数字开头和结尾，可以包含字母、数字、下划线、连接符及64个字符只能
//        String regName="^(?!-)(?!_)(?!.*?-$)(?!.*?_$)[a-zA-Z0-9_-]{1,63}+$";
//        String regName="^[A-Za-z0-9][A-Za-z0-9_-]+(?<!_-){1,63}$";
//        String regName="^[A-Za-z0-9][A-Za-z0-9_-]+(?<!_-){1,63}$";
//
//        String regName  "^[a-z][0-9a-z_]{1,63}";
//
//
//        //只能以英文字母开头，必须包含字母数字和连接符，且不能以连接符结尾，且连接符不能连续出现两个
//
//         String as= "^(?![0-9]+$)(?![a-z]+$)[0-9a-z]{1,63}$";
//
//
//        String re =  "/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/";
//
//        String regName="^(?!--)(?!.*?-$)(?!.*?@$)[a-zA-Z0-9@-]{1,63}+$";
//
//        String reg1 = "^(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]+$";
//        String reg = "[a-zA-Z0-9\\S]+$";
//
//                System.out.println("-123".matches(regName));
//                System.out.println("_123".matches(regName));
//                System.out.println("*123".matches(regName));
//                System.out.println("1-23".matches(regName));
//                System.out.println("1_23".matches(regName));
//                System.out.println("1@23".matches(regName));
//                System.out.println("123-".matches(regName));
//                System.out.println("123Qa_".matches(regName));
//                System.out.println("123Qa._".matches(regName));
//                System.out.println("123,Qa".matches(regName));
//                System.out.println("123.Qa".matches(regName));
//
//
//        System.out.println("123".matches(regName));//1true
//        System.out.println("123@@".matches(regName));//2false
//        System.out.println("zzzzzzzzzzz".matches(regName));//3true
//        System.out.println("zzzzzzzz@@zzz".matches(regName));//4false
//        System.out.println("sss123".matches(regName));//5true
//        System.out.println("sss123-".matches(regName));//6false
//        System.out.println("sss_123-".matches(regName));//7false
//        System.out.println("sgg123@@$@".matches(regName));//8false
//        System.out.println("sgg@123_".matches(regName));//9false
//        System.out.println("sgg_".matches(regName));//10false
//        System.out.println("sgg.".matches(regName));//11false
//        System.out.println("sgg_.".matches(regName));//12false
//        System.out.println("123sgg_.".matches(regName));//13false
//        System.out.println("123-sgg_.".matches(regName));//14false
//        System.out.println("123-sgg_".matches(regName));//15false
//        System.out.println(("1asdfakdkasdfasdfasdfasjfaksdjfffffffffffffffffffff").matches(regName));//16true
//        System.out.println("-123-sgg_".matches(regName));//17false
//        System.out.println("123-".matches(regName));//1false
//        System.out.println("123_".matches(regName));//1false
//        System.out.println("12.3".matches(regName));//1false
//
//        boolean b = Pattern.compile("gaan").matcher("guan1").find();
//        System.out.println(b);

//        boolean flag = false;

//        List<Integer> list = Arrays.asList(0,1, 2);
//        List<Integer> nums = Arrays.asList(0,1, 2,3);
//        int a=0;
//        for(Integer i:nums){
//           if(list.contains(i)){
//               a++;
//           }
//        }
//
//        System.out.println(a==nums.size());

//        Object a= null;
//        if(a instanceof String){
//            System.out.println(a);
//        }

        boolean a=false;

//        if (a=true) {
//            System.out.println(a);
//        }

        if(a==true){
            System.out.println(a);
        }

//        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);

//        list1.stream().forEach(integer -> {
//            try {
//                if(integer==2){
//                    int i = 2 / 0;
//                }
//            }catch (Exception e){
//                System.out.println("Error compute");
//                return;
//            }
//
//            System.out.println(integer);
//        });
//        for(Integer num: list1){
//            try {
//                if(num==2){
//                    int i = 2 / 0;
//                }
//            }catch (Exception e){
//                System.out.println("Error compute");
//                return;
//            }
//
//            System.out.println(num);
//        }

    }


//    private void test(){
//        List<Integer> list= new ArrayList<>();
//
//        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
//
//        try {
//            for (int a : list1) {
//
//                try {
//                    if (a==2) {
//                        int i = 2 / 0;
//                    }
//                } catch (Exception e) {
//                    System.out.println(e);
//                    throw new IllegalAccessException();
//                }
//                list.add(a);
//            }
//
//
//        } finally {
//            System.out.println("asdf");
//        }
//
//    }


    private void test(){
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        for(Integer num: list1){
            try {
                if(num==2){
                    int i = 2 / 0;
                }
            }catch (Exception e){
                System.out.println("Error compute");
                continue;
            }

            System.out.println(num);
        }
    }
}
