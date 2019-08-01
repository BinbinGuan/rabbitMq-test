package com.example.java8test.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * @author: GuanBin
 * @date: Created in 下午10:32 2019/5/29
 */
public class SendMq {

    public static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        int count=1;

        //限制发送给小费者，不得超过一条消息
        channel.basicQos(count);


        for (int i=0;i<=50;i++){
            String msg = "Hello msg" + i;
            System.out.println(msg);
            //发送消息
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes(Charset.defaultCharset()));

            Thread.sleep(i*5);

        }

        //关闭channel
        channel.close();


        //关闭连接
        connection.close();

    }
}
