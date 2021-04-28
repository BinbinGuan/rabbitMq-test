package com.example.elssticsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: GuanBin
 * @date: Created in 下午8:53 2021/3/19
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Log implements Serializable {
    private static final long serialVersionUID = 4139142104981655835L;

    private String id;
    //cloudify/celery任务执行id
    private String execution_id;
    //cmp的租户id
    private String tenant_id;
    //任务允许的状态
    private String status;
    //脚本名称
    private String script_name;
    //脚本状态
    private String script_type;
    //脚本id
    private String script_id;
    //执行任务的人名称
    private String executor;

    //执行任务的人id
    private String executor_id;
    //日志内容
    private String log;

    //目标机用户
    private String user;
    //目标机ip
    private String target_host_ipv4;
    //doc的索引名
    private String index_name;
    //cloudify的operation
    private String operation;
    //请求时间
    private String requested_at;
    //任务开始时间
    private String started_at;
    //日志的更新时间
    private String log_updated_at;
    //任务结束时间
    private String finished_at;
    //任务执行方法，cloudify/celery
    private String execute_method;
    //服务部署id
    private String deployment_id;
    //os类型
    private String os_type;
    //任务执行时间
    private String spend_time;

    //卡片id
    private String catalog_id;

    //卡片名称
    private String catalog_name;

    //项目id
    private String group_id;

    //项目名称
    private String group_name;

    //业务组id
    private String business_group_id;

    //业务组名称
    private String business_group_name;

    //部署名称
    private String deployment_name;

    //资源池id
    private String resource_bundle_id;

    //资源池名称
    private String resource_bundle_name;

    //标题
    private String title;

    //类型
    private String type;

    //主机名
    private String external_name;

    //云平台id
    private String cloud_entry_id;

    //云平台类型id
    private String cloud_entry_type_id;

    //主机展示名
    private String vm_name;
}
