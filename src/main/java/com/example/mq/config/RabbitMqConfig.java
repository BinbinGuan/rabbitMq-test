package com.example.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: GuanBin
 * @date: Created in 下午7:56 2018/8/13
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue Queue1(){
        return new Queue("test1");
    }


    @Bean
    public Queue Queue2(){
        return new Queue("test2");
    }
}
