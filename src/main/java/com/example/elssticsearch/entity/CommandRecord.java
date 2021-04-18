package com.example.elssticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author: GuanBin
 * @date: Created in 上午9:28 2021/3/18
 */
@Data
@Document(indexName = "command-record",type = "command-record", shards = 1,replicas = 0, refreshInterval = "-1")
public class CommandRecord implements Serializable {
    private static final long serialVersionUID = 2196582713625799003L;
    @Id
    private String id;
    @Field
    private String vmId;
    @Field
    private String vmName;
    @Field(type = FieldType.Keyword)
    private String userId;
    @Field
    private String userName;
    @Field
    private String tenantId;
    @Field
    private String ip;
    @Field
    private String catalogId;
    @Field
    private String catalogName;
    @Field
    private String groupId;
    @Field
    private String groupName;
    @Field
    private String businessGroupId;
    @Field
    private String businessGroupName;
    @Field
    private String deploymentId;
    @Field
    private String deploymentName;
    @Field
    private String resourceBundleId;
    @Field
    private String resourceBundleName;
    @Field
    private String commandText;
    @Field
    private Long startTime;
    @Field
    private Long   endTime;
    @Field
    private String title;
    @Field(type = FieldType.Keyword)
    private String type;

}

