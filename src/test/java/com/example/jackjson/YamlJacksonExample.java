package com.example.jackjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.io.File;
import java.io.IOException;

/**
 * @author: GuanBin
 * @date: Created in 上午10:17 2020/6/15
 */
public class YamlJacksonExample {
    public static void main(String[] args) {


        try {
            //从yaml文件读取数据
            reaedYamlToEmployee();
            //写入yaml文件
            reaedEmployeeToYaml();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 从yaml文件读取数据
     * @throws IOException
     */
    private static void reaedYamlToEmployee() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Employee employee = mapper.readValue(new File("src/test/java/com/example/jackjson/EmployeeYaml.yml"), Employee.class);
        System.out.println(employee.getName() + "********" + employee.getEmail());

    }

    /**
     * 写入yaml文件
     * @throws IOException
     */
    private static void reaedEmployeeToYaml() throws IOException {
        //去掉三个破折号
        ObjectMapper  mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        //禁用掉把时间写为时间戳
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Employee employee = new Employee("test2", "999@qq.com");
        mapper.writeValue(new File("src/test/java/com/example/jackjson/EmployeeYamlOutput.yml"), employee);
    }
}
