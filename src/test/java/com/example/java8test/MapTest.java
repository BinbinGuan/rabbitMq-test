package com.example.java8test;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.misc.Hash;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 上午9:44 2021/1/12
 */
public class MapTest {

    public static void main(String[] args) {

        Map result = new HashMap<>();
        Map map = new HashMap<>();
        Map map1 = new HashMap<>();
        Map map2 = new HashMap<>();

        map.put("status", "failed");
        map1.put("status", "failed");
        map2.put("status", "success");
        result.put("1", map);
        result.put("2", map1);
        result.put("3", map2);

    }
}
