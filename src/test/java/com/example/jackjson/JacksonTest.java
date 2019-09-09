package com.example.jackjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author: GuanBin
 * @date: Created in 下午4:28 2019/8/1
 */
public class JacksonTest {

    //测试json2
//    {
//        "country_id": "China",
//        "birthDate": "1949-10-01",
//        "nation": ["Han", "Meng", "Hui", "WeiWuEr", "Zang"],
//        "lakes": ["QingHai Lake", "Poyang Lake", "Dongting Lake", "Taihu Lake"],
//        "provinces": [{
//        "name": "Shanxi",
//        "population": 37751200
//    },
//        {
//            "name": "ZheJiang",
//                "population": 55080000
//        }],
//        "traffic": {
//        "HighWay(KM)": 4240000,
//                "Train(KM)": 112000
//    }
//    }

    public static void main(String[] args) throws IOException {

        String content="{\"country_id\":\"China\",\"birthDate\":\"1949-10-01\",\"nation\":[\"Han\",\"Meng\",\"Hui\",\"WeiWuEr\",\"Zang\"],\"lakes\":[\"QingHai Lake\",\"Poyang Lake\",\"Dongting Lake\",\"Taihu Lake\"],\"provinces\":[{\"name\":\"Shanxi\",\"population\":37751200},{\"name\":\"ZheJiang\",\"population\":55080000}],\"traffic\":{\"HighWay(KM)\":4240000,\"Train(KM)\":112000}}";
        ObjectMapper mapper = new ObjectMapper();
        // Jackson提供一个树节点被称为"JsonNode",ObjectMapper提供方法来读json作为树的JsonNode根节点
        JsonNode node = mapper.readTree(content);
        // 看看根节点的类型
        System.out.println("node JsonNodeType:" + node.getNodeType());
        // 是不是一个容器
        System.out.println("node is container Node ? " + node.isContainerNode());
        // 得到所有node节点的子节点名称
        System.out.println("---------得到所有node节点的子节点名称-------------------------");
        Iterator< String > fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            System.out.print(fieldName + " ");
        }
        System.out.println("\n-----------------------------------------------------");
        // as.Text的作用是有值返回值，无值返回空字符串
        JsonNode country_id = node.get("country_id");
        System.out.println("country_id:" + country_id.asText() + " JsonNodeType:" + country_id.getNodeType());

        JsonNode birthDate = node.get("birthDate");
        System.out.println("birthDate:" + birthDate.asText() + " JsonNodeType:" + birthDate.getNodeType());

        JsonNode nation = node.get("nation");
        System.out.println("nation:" + nation + " JsonNodeType:" + nation.getNodeType());

        JsonNode lakes = node.get("lakes");
        System.out.println("lakes:" + lakes + " JsonNodeType:" + lakes.getNodeType());

        JsonNode provinces = node.get("provinces");
        System.out.println("provinces JsonNodeType:" + provinces.getNodeType());

        boolean flag = true;
        for (JsonNode provinceElements: provinces) {
            //为了避免provinceElements多次打印，用flag控制打印，能体现provinceElements的JsonNodeType就可以了
            if (flag) {
                System.out.println("provinceElements JsonNodeType:" + provinceElements.getNodeType());
                System.out.println("provinceElements is container node? " + provinceElements.isContainerNode());
                flag = false;
            }
            Iterator < String > provinceElementFields = provinceElements.fieldNames();
            while (provinceElementFields.hasNext()) {
                String fieldName = (String) provinceElementFields.next();
                String province;
                if ("population".equals(fieldName)) {
                    province = fieldName + ":" + provinceElements.get(fieldName).asInt();
                } else {
                    province = fieldName + ":" + provinceElements.get(fieldName).asText();
                }
                System.out.println(province);
            }
        }
    }
}
