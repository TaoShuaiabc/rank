package com.shuaitao.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;
import com.shuaitao.rabbitmq.constant.QueueConstant;

/**
 * @ClassName Producer
 * @Description  消息生产者
 * @Author ts
 * @Date 2022/12/27 11:18
 * @Version 1.0
 */
public class Producer {


    public static void main(String[] args) throws Exception {

        new Producer().producerMassage();
    }

    /**
     *  生产消息方法
     */
    public void producerMassage() throws Exception {

        Channel channel = RabbitMQUtils.getChannel();
        /**
         * 生成一个队列
         * 1.队列名称
         * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * 3.该队列是否只供一个消费者进行消费 是否进行共享 true 可以多个消费者消费
         * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 true 自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QueueConstant.QUEUE_NAME,false,false,false,null);
        String message="hello world";

        /**
         * 发送一个消息
         * 1.发送到那个交换机
         * 2.路由的 key 是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        channel.basicPublish("",QueueConstant.QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");
    }

}
