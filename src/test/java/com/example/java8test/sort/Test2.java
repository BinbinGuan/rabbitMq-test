package com.example.java8test.sort;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author: GuanBin
 * @date: Created in 下午2:52 2019/6/3
 */
public class Test2 {

    public static void main(String[] args) {
//        List<String> test= new ArrayList<>();
//        test.add("asd");
//        test.add("as");
//        System.out.println(test);
//        String msg="{abdcadf}";
//        int i = test.indexOf("{");
//        int m = test.lastIndexOf("}");
//        System.out.println(i);
//        System.out.println(m);
//        System.out.println(test.indexOf("p"));
//        System.out.println(msg.substring(1,msg.length()-1));
//        System.out.println(msg);
//
//        System.out.println(msg.contains("ab"));


//        String str ="${OWNER_NAME}${DEPLOYMENT_NAME}${KEY_VALUE}";
//
//        List<String> ruleList = Arrays.asList(str.split("\\$"));
//
//        System.out.println(ruleList);
//
//        String rule="${Tag::env}";
//        String key = StringUtils.substring(rule, rule.lastIndexOf(":")+1, rule.lastIndexOf("}"));
//        System.out.println(key);
//
//
//
//        Map<String,Object> map=new HashMap<>();
//        map.put("a","2123");
//
//        map.remove("b");


//        String Str = new String("${Tag::env}-");
//        System.out.print("返回值 :" );
//        System.out.println(Str.substring(0,Str.lastIndexOf("}")+1));
//        System.out.print("返回值 :" );
//        System.out.println(Str.substring(4, 10));

//        List<String> objects = new ArrayList<>();
//
//        objects.add("a");
//
//        List<String> strings = Arrays.asList( "b", "c");
//
//
//        objects.retainAll(strings);
//
//        System.out.println(objects);

                List<String> strings = Arrays.asList( "a","b", "c");

                for(String str:strings){
                    test3(str);
                    System.out.println(str);
                }

    }

    private static void test3(String str){

        if(StringUtils.equalsIgnoreCase(str,"c")){
            return;
        }

    }
}
