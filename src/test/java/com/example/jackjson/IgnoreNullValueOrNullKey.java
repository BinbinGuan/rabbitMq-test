package com.example.jackjson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 下午1:52 2019/8/31
 */
public class IgnoreNullValueOrNullKey {

    @Test
    public void testSerializMapNullKey() throws JsonProcessingException {
        //ignore map null value
        Map<String, Object> map = new HashMap<>();
        map.put(null, "test");
        ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializerProvider().setNullKeySerializer(new NullKeySerializer());
        System.out.println(mapper.writeValueAsString(map));

    }

    @Test
    public void testIgnoreMapNullValue() throws JsonProcessingException {
        //ignore null key
        Map<String, Object> map = new HashMap<>();
        map.put("key", "test");
        map.put("key1", null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(mapper.writeValueAsString(map));

    }

    @Test
    public void testIgnoreNullFiled() throws JsonProcessingException {
        //test ignore null filed
        User user = new User();
        user.setName(null);
        user.setHouse("asdf");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(user));

    }


    @Test
    public void testIgnoreNullFiledGlobally() throws JsonProcessingException {
        //test ignore null filed
        User user = new User();
        user.setName(null);
        user.setHouse("asdf");
        ObjectMapper mapper = new ObjectMapper();

        //Ignore Null Fields Globally
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(mapper.writeValueAsString(user));

    }


    /**
     * Exception: Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)
     * <p>
     * Map null key serializer and override the default behavior:
     * <p>
     * if you want to learn more. Please visit https://www.baeldung.com/jackson-map-null-values-or-null-key
     *  https://github.com/eugenp/tutorials/tree/master/jackson-simple
     */
    static class NullKeySerializer extends StdSerializer<Object> {
        public NullKeySerializer() {
            this(null);
        }

        public NullKeySerializer(Class<Object> t) {
            super(t);
        }

        @Override
        public void serialize(Object nullKey, JsonGenerator jsonGenerator, SerializerProvider unused)
                throws IOException, JsonProcessingException {
            jsonGenerator.writeFieldName("");
        }
    }

    /**
     * ignore null filed
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class User {
        private String name;
        private String house;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }
    }
}
