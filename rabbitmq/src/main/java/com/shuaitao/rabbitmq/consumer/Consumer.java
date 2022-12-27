package com.shuaitao.rabbitmq.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Consumer
 * @Description 消息消费者
 * @Author ts
 * @Date 2022/12/27 11:24
 * @Version 1.0
 */
public class Consumer {

    private final static String QUEUE_NAME = "hello";


    public static void main(String[] args) throws IOException, TimeoutException {
        new Consumer().ConsumerMassage();
    }

    public void ConsumerMassage() throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("120.79.46.139");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //接收消息
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println(new String(message.getBody()));
        };

        //消息取消消费的回调
        CancelCallback cancelCallback = (consumerTag) ->{
            System.out.println("取消消费");
        };

        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }



}
