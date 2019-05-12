//package com.example.mq.service;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author: GuanBin
// * @date: Created in 下午2:22 2018/8/14
// */
//@Component
//@RabbitListener(queues = "topic.messages")//topic.messages在RabbitMqTopicConfig配置中是接受只要是topic开头的即可
//public class RabbitMqServiceMessages {
//
//    @RabbitHandler
//    public void receiveOne(String msg) {
//        System.out.println("RabbitMqServiceMessages:" + msg);
//    }
//
//}
