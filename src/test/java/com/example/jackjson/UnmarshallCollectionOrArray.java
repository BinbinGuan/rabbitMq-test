package com.example.jackjson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 下午2:33 2019/8/31
 * <p>
 * 直译为“编排”， 在计算机中特 指将数据按某种描述格式编排出来，通常来说一般是从非文本格式到文本格式的数据转化。unmarshal自然是指marshal的逆过程。比如在WebService中，我们需要把java对象以xml方式表示并在网络间传输，把java对象转化成xml片段的过程就是marshal.
 */
public class UnmarshallCollectionOrArray {

    @Test
    public void unmarshallToArray() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = Lists.newArrayList(new User("tom", 10), new User("sam", 11));
        String str = mapper.writeValueAsString(users);
        System.out.println("user json:" + str);
        //若user没无参构造方法会报错
        //com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.example.jackjson.UnmarshallCollectionOrArray$User` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        // at [Source: (String)"[{"name":"tom","age":10},{"name":"sam","age":11}]"; line: 1, column: 3] (through reference chain: java.lang.Object[][0])
        User[] userArray = mapper.readValue(str, User[].class);
        Assert.assertTrue(userArray[0] instanceof User);
    }

    @Test
    public void unmarshallToList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = Lists.newArrayList(new User("tom", 10), new User("sam", 11));
        String str = mapper.writeValueAsString(users);
        System.out.println("user json:" + str);
        //若user没无参构造方法会报错
        //com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.example.jackjson.UnmarshallCollectionOrArray$User` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        // at [Source: (String)"[{"name":"tom","age":10},{"name":"sam","age":11}]"; line: 1, column: 3] (through reference chain: java.lang.Object[][0])
        List list = mapper.readValue(str, List.class);
        Assert.assertTrue(list.get(0) instanceof LinkedHashMap);
    }

    /**
     * There are two ways to help Jackson understand the right type information – we can either use the TypeReference provided by the library for this very purpose:
     * @throws IOException
     */
    @Test
    public void unmarshallToListOneWay() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = Lists.newArrayList(new User("tom", 10), new User("sam", 11));
        String str = mapper.writeValueAsString(users);
        System.out.println("user json:" + str);
        //若user没无参构造方法会报错
        //com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.example.jackjson.UnmarshallCollectionOrArray$User` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        // at [Source: (String)"[{"name":"tom","age":10},{"name":"sam","age":11}]"; line: 1, column: 3] (through reference chain: java.lang.Object[][0])
        List<User> list = mapper.readValue(
                str, new TypeReference<List<User>>() { });
        Assert.assertTrue(list.get(0) instanceof User);
    }

    @Test
    public void unmarshallToListTwoWay() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = Lists.newArrayList(new User("tom", 10), new User("sam", 11));
        String str = mapper.writeValueAsString(users);
        System.out.println("user json:" + str);
        //若user没无参构造方法会报错
        //com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.example.jackjson.UnmarshallCollectionOrArray$User` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        // at [Source: (String)"[{"name":"tom","age":10},{"name":"sam","age":11}]"; line: 1, column: 3] (through reference chain: java.lang.Object[][0])

        CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
        List<User> list = mapper.readValue(str, javaType);
        Assert.assertTrue(list.get(0) instanceof User);
    }

    static class User {
        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
