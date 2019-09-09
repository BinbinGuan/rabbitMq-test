package com.example.jackjson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: GuanBin
 * @date: Created in 下午5:28 2019/9/8
 */
public class JsonBindData {

//    //country.json{
//  "country_id": "China",
//          "birthDate": "1949-10-01",
//          "nation": [
//          "Han",
//          "Meng",
//          "Hui",
//          "WeiWuEr",
//          "Zang"
//          ],
//          "lakes": [
//          "Qinghai Lake",
//          "Poyang Lake",
//          "Dongting Lake",
//          "Taihu Lake"
//          ],
//          "provinces": [
//    {
//        "name": "Shanxi",
//            "population": 37751200
//    },
//    {
//        "name": "ZheJiang",
//            "population": 55080000
//    }
//  ],
//          "traffic": {
//        "HighWay(KM)": 4240000,
//                "Train(KM)": 112000
//    }
//}


    /**
     * 解析Json字符串反序列化为Java对象
     */
    public static void main(String[] args) throws IOException {
        //ObjectMapper类用序列化与反序列化映射器
        ObjectMapper mapper = new ObjectMapper();
        String countryStr="{\"country_id\":\"China\",\"birthDate\":\"1949-10-01\",\"nation\":[\"Han\",\"Meng\",\"Hui\",\"WeiWuEr\",\"Zang\"],\"lakes\":[\"Qinghai Lake\",\"Poyang Lake\",\"Dongting Lake\",\"Taihu Lake\"],\"provinces\":[{\"name\":\"Shanxi\",\"population\":37751200},{\"name\":\"ZheJiang\",\"population\":55080000}],\"traffic\":{\"HighWay(KM)\":4240000,\"Train(KM)\":112000}}";
        //当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
        //因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Country country = mapper.readValue(countryStr, Country.class);
        System.out.println("country_id:" + country.getCountry_id());
        //设置时间格式，便于阅读
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String birthDate = dateformat.format(country.getBirthDate());
        System.out.println("birthDate:" + birthDate);

        List < Province > provinces = country.getProvinces();
        for (Province province: provinces) {
            System.out.println("province:" + province.name + "\n" + "population:" + province.population);
        }

    }

    class Province {

        public String name;
        public int population;
        public String[] city;

    }


    class Country {
        // 注意：被序列化的bean的private属性字段需要创建getter方法或者属性字段应该为public
        private String country_id;
        private Date birthDate;
        private List<String> nation = new ArrayList<String>();
        private String[] lakes;
        private List<Province> provinces = new ArrayList<Province>();
        private Map<String, Integer> traffic = new HashMap<String, Integer>();

        public Country() {
            // TODO Auto-generated constructor stub
        }

        public Country(String countryId) {
            this.country_id = countryId;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public List<String> getNation() {
            return nation;
        }

        public void setNation(List<String> nation) {
            this.nation = nation;
        }

        public String[] getLakes() {
            return lakes;
        }

        public void setLakes(String[] lakes) {
            this.lakes = lakes;
        }

        public Integer get(String key) {
            return traffic.get(key);
        }

        public Map<String, Integer> getTraffic() {
            return traffic;
        }

        public void setTraffic(Map<String, Integer> traffic) {
            this.traffic = traffic;
        }

        public void addTraffic(String key, Integer value) {
            traffic.put(key, value);
        }

        public List<Province> getProvinces() {
            return provinces;
        }

        public void setProvinces(List<Province> provinces) {
            this.provinces = provinces;
        }

        @Override
        public String toString() {
            return "Country [country_id=" + country_id + ", birthDate=" + birthDate
                    + ", nation=" + nation + ", lakes=" + Arrays.toString(lakes)
                    + ", province=" + provinces + ", traffic=" + traffic + "]";
        }
    }
}
