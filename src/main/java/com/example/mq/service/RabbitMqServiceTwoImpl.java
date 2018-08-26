package com.example.mq.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: GuanBin
 * @date: Created in 下午2:22 2018/8/14
 */
@Component
@RabbitListener(queues = "test1")
public class RabbitMqServiceTwoImpl {

    @RabbitHandler
    public void receiveTwo(String msg) {
        System.out.println("Test1 receive two:" + msg);
    }
}
