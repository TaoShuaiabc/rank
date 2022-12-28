package com.shuaitao.rabbitmq.consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;
import com.shuaitao.rabbitmq.constant.QueueConstant;

/**
 * @ClassName Consumer
 * @Description 消息消费者
 * @Author ts
 * @Date 2022/12/27 11:24
 * @Version 1.0
 */
public class Consumer {


    public static void main(String[] args) throws Exception {
        for (int i =0;i<=3;i++){
            new Thread(()->{
                try {
                    new Consumer().ConsumerMassage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }

    public void ConsumerMassage() throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        //接收消息
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println(Thread.currentThread().getName()+"==="+new String(message.getBody()));
        };

        //消息取消消费的回调
        CancelCallback cancelCallback = (consumerTag) ->{
            System.out.println("取消消费");
        };
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费者未成功消费的回调
         */
        channel.basicConsume(QueueConstant.QUEUE_NAME,true,deliverCallback,cancelCallback);
    }



}
