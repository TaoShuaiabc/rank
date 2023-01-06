package com.shuaitao.rabbitmq.deadQueue;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_NORMAL;

/**
 * @ClassName DeadQueueProducer
 * @Description  死信队列生产者
 * @Author ts
 * @Date 2023/1/6 15:08
 * @Version 1.0
 */
public class DeadQueueProducer {

    public static void main(String[] args) throws Exception {
        //普通队列routingKey
        String normalRoutingKey= "normal";
        Channel channel = RabbitMQUtils.getChannel();
        int num = 10;
        //设置消息过期时间（过期后会放进死信队列）
        /*AMQP.BasicProperties build = new AMQP.BasicProperties().builder().expiration("10000").build();*/
        for (int i=0;i<num;i++){
            System.out.println("发送消息："+i);
            //channel.basicPublish(EXCHANGE_NORMAL,normalRoutingKey,build,("info"+i).getBytes());
            channel.basicPublish(EXCHANGE_NORMAL,normalRoutingKey,null,("info"+i).getBytes());
        }

    }
}
