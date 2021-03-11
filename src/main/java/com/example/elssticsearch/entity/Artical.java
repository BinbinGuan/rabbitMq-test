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
@Document(indexName = "artical",type = "artical", shards = 1,replicas = 0, refreshInterval = "-1")
public class Artical {
    @Id
    private String id;
    @Field
    private String title;
    @Field
    private String name;
}
