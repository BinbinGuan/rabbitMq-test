package com.example.java8test.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: GuanBin
 * @date: Created in 下午11:00 2019/5/29
 */
public class RecieveMq {

    public static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body);
                System.out.println("[1] is done" + msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                    //手动回应
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };



        //自动应答
        boolean autoAck=false;

        //监听队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);


    }
}
