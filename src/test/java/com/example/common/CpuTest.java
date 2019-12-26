package com.example.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: GuanBin
 * @date: Created in 上午11:31 2019/10/30
 */
public class CpuTest {

    public static void main(String[] args) {

        Person person = new Person();
        person.setTest(Lists.newArrayList("1","2"));
        System.out.println(person.toString());

        String[] patternMatch = {"()"};
        List<String> patternList = new ArrayList<String>();

        patternList.add("Avg Volume Units product A + Volume Units product A");
        patternList.add("Avg Volume Units /  Volume Units product A");
        patternList.add("Avg retailer On Hand / Volume Units Plan / Store Count");
        patternList.add("Avg Hand Volume Units Plan Store Count");
        patternList.add("1 - Avg merchant Volume Units");
        patternList.add("Total retailer shipment Count");

        for (String s :patternList ){

            for(int i=0;i<patternMatch.length;i++){
                Pattern pattern = Pattern.compile(patternMatch[i]);

                Matcher matcher = pattern.matcher(s);
                System.out.println(s);
                if (matcher.matches()) {

                    System.out.println("Passed");
                }else
                    System.out.println("Failed;");
            }

        }
    }
    @Data
   static class Person {
        List<String> test=new ArrayList<>();
    }
}
