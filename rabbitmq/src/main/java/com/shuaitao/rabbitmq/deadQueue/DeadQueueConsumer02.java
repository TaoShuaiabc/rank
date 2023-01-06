package com.shuaitao.rabbitmq.deadQueue;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.EnumPack.ExchangeTypeEnum;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_DEAD;

/**
 * @ClassName DeadQueueProducer
 * @Description 死信队列消费者
 * @Author ts
 * @Date 2023/1/6 15:08
 * @Version 1.0
 */
public class DeadQueueConsumer02 {

    public static void main(String[] args) throws Exception {

        //死信队列名
        String deadQueueName= "dead_queue";
        //死信队列routingKey
        String deadRoutingKey= "dead";

        Channel channel = RabbitMQUtils.getChannel();

        //设置死信交换机
        channel.exchangeDeclare(EXCHANGE_DEAD, ExchangeTypeEnum.DIRECT.getCode());

        //先设置死信队列
        channel.queueDeclare(deadQueueName, false, false, false, null);
        channel.queueBind(deadQueueName,EXCHANGE_DEAD,deadRoutingKey);

        //消费消息
        channel.basicConsume(deadQueueName, true, (consumerTag, message) -> {
            System.out.println("绑定key:"+message.getEnvelope().getRoutingKey());
            System.out.println("死信队列开始消费消息:"+new String(message.getBody()));
        }, consumerTag -> {});
    }
}
