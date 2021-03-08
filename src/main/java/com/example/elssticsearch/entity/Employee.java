package com.example.elssticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author: GuanBin
 * @date: Created in 上午11:09 2021/3/8
 */
@Data
@Document(indexName = "company",type = "employee", shards = 1,replicas = 0, refreshInterval = "-1")
public class Employee {
    @Id
    private String id;
    @Field
    private String firstName;
    @Field
    private String lastName;
    @Field
    private Integer age;
    @Field
    private String about;

}
