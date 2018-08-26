package com.example.mq.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: GuanBin
 * @date: Created in 下午2:22 2018/8/14
 */
@Component
@RabbitListener(queues = "topic.message")//只接受是topic.message的路由routing key
public class RabbitMqServiceMessage {

    @RabbitHandler
    public void receiveOne(String msg) {
        System.out.println(" RabbitMqServiceMessage:" + msg);
    }

}
