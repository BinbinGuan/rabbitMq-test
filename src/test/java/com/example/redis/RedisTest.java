package com.example.redis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 上午11:08 2019/11/12
 */
public class RedisTest {
   static Jedis jedis = new Jedis("127.0.0.1", 6379);
   static String a="a";

    public static void main(String[] args) {
        Person tom = new Person(1, "tom");
        Person alice = new Person(1, "alice");
        List<Person> personList =new ArrayList<>();
        personList.add(tom);
        personList.add(alice);
//        jedis.hdel("资源池1".getBytes(),"部署".getBytes());
        jedis.hset("资源池1".getBytes(),"部署".getBytes(),SerializeUtil.serialize(personList));
//        jedis.hset("资源池1","部署",SerializeUtil.serialize(personList));

//        Object unserialize = SerializeUtil.unserialize(jedis.get("资源池1".getBytes()));
        List<Person> unserialize = (List<Person>) SerializeUtil.unserialize(jedis.hget("资源池1".getBytes(), "部署".getBytes()));
        unserialize.forEach(person -> {
            System.out.println(person.getName());
        });

    }
}
