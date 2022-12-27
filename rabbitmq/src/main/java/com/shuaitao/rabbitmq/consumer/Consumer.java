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
        new Consumer().ConsumerMassage();
    }

    public void ConsumerMassage() throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        //接收消息
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println(new String(message.getBody()));
        };

        //消息取消消费的回调
        CancelCallback cancelCallback = (consumerTag) ->{
            System.out.println("取消消费");
        };

        channel.basicConsume(QueueConstant.QUEUE_NAME,true,deliverCallback,cancelCallback);
    }



}
