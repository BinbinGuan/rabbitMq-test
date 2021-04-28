package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 下午4:10 2021/3/29
 */
public class TmpTest {
    public static void main(String[] args) throws JsonProcessingException {
        Map map = new HashMap<>();
        Map configMap = new HashMap<>();
        configMap.put("endpoint","");
        configMap.put("accessKeyId","");
        configMap.put("accessKeySecret","");
        configMap.put("bucketName","");
        configMap.put("url","");

        map.put("enable",true);
        map.put("storageType","oss");
        map.put("retentionTime",180);
        map.put("storageConfig",configMap);
        map.toString();
        System.out.println(map.toString());
        System.out.println(map);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(map));


    }
}
