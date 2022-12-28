package com.shuaitao.rabbitmq.massagePersistence;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;
import com.shuaitao.rabbitmq.constant.QueueConstant;

/**
 * @ClassName MassagePersistenceProducer
 * @Description  消息持久化生产者
 * @Author ts
 * @Date 2022/12/28 11:04
 * @Version 1.0
 */
public class MassagePersistenceProducer {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();


        //durable设置为true,代表该队列持久化，但是如果已经存在一个非持久化的同名队列，会报错
        boolean durable = true;
        channel.queueDeclare(QueueConstant.QUEUE_NAME,durable,false,false,null);
        String message="hello world";

        //发送消息，MessageProperties.PERSISTENT_TEXT_PLAIN代表设置消息持久化，但这是简单的持久化，想要强有力的持久化参考后面的发布确认章节
        channel.basicPublish("",QueueConstant.QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

    }

}
