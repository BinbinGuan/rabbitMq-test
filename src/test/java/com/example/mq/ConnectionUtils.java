package com.example.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: GuanBin
 * @date: Created in 下午10:35 2019/5/29
 */
public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //服务地址
        factory.setHost("127.0.0.1");

        //端口
        factory.setPort(5672);

        //vhost
        factory.setVirtualHost("/test");

        //用户名
        factory.setUsername("guanbin");

        //密码
        factory.setPassword("Passw0rd");

        Connection connection = factory.newConnection();
        return connection;
    }
}
