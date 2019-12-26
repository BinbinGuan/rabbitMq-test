package com.example.mq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author: GuanBin
 * @date: Created in 上午10:19 2019/11/29
 */
@Configuration
@EnableCaching
@Component
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(getConnectionFactory());
        return rabbitTemplate;
    }

    public  org.springframework.amqp.rabbit.connection.ConnectionFactory getConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.",5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }
}
