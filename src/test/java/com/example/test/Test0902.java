package com.example.test;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: GuanBin
 * @date: Created in 下午4:05 2020/9/2
 */
public class Test0902 {

    static AtomicInteger atomicInteger = new AtomicInteger();


    public static void main(String[] args) {
        Map map= new HashMap();
        Map propertis= new HashMap();
        propertis.put("a","a");
        map.put("propertis",propertis);
        map.put("1","1");

        MapUtils.getMap(map, "propertis").put("b", "b");

        System.out.println(map);


        ExecutorService executorService = Executors.newCachedThreadPool();
         int i=0;

        while (true){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(atomicInteger.getAndIncrement());
                }
            });
        }


    }



}
