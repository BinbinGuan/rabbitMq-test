package com.example.mq.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: GuanBin
 * @date: Created in 下午7:41 2018/8/13
 */
@RestController
@RequestMapping(value = "/api/mq")
public class RabbitMqController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/send")
    @ResponseBody
    @ApiOperation(value = "分页查询分区信息列表", notes = "分页查询分区信息列表")
    public String send(){
        String content="Data:"+new Date();
        amqpTemplate.convertAndSend("test1",content);
        return content;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/multiple/send")
    @ResponseBody
    @ApiOperation(value = "分页查询分区信息列表", notes = "分页查询分区信息列表")
    public Object multipleSend(){

        StringBuffer buffer= new StringBuffer();
        for(int i=0;i<10;i++){
            long time = System.currentTimeMillis();
            amqpTemplate.convertAndSend("test1",String.format("发送第%s次，时间%s",i,time));
            buffer.append(time+"<br>");
        }
        return buffer;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/send1")
    @ResponseBody
    public Object send1(){

        //接受规则都在RabbitMqTopicConfig中
        String context = "my topic 1";
        System.out.println("发送者说 : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.message", context);
        return context;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/send2")
    @ResponseBody
    public Object send2(){

        String context = "my topic 2";
        System.out.println("发送者说 : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.messages", context);
        return context;
    }
 }
