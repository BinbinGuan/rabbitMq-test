package com.example.java8test.bulider;

import com.example.entity.Person;

/**
 * @author: GuanBin
 * @date: Created in 下午7:30 2019/5/26
 */
public class BuliderTest {

    public static void main(String[] args) {

        Person person = new Person.Builder()
                .name("guanbin")
                .location("shanghai")
                .habit("football")
                .job("it")
                .build();


    }
}
