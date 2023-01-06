package com.shuaitao.rabbitmq.deadQueue;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.EnumPack.ExchangeTypeEnum;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import java.util.HashMap;
import java.util.Map;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_DEAD;
import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_NORMAL;

/**
 * @ClassName DeadQueueProducer
 * @Description 普通消费者
 * @Author ts
 * @Date 2023/1/6 15:08
 * @Version 1.0
 */
public class DeadQueueConsumer01 {

    public static void main(String[] args) throws Exception {

        //死信队列名
        String deadQueueName= "dead_queue";
        //死信队列routingKey
        String deadRoutingKey= "dead";
        //普通队列名
        String normalQueueName= "normal_queue";
        //普通队列routingKey
        String normalRoutingKey= "normal";

        Channel channel = RabbitMQUtils.getChannel();

        //设置普通交换机
        channel.exchangeDeclare(EXCHANGE_NORMAL, ExchangeTypeEnum.DIRECT.getCode());
        //设置死信交换机
        channel.exchangeDeclare(EXCHANGE_DEAD, ExchangeTypeEnum.DIRECT.getCode());

        //先设置死信队列
        channel.queueDeclare(deadQueueName, false, false, false, null);
        channel.queueBind(deadQueueName,EXCHANGE_DEAD,deadRoutingKey);


        //存储死信队列信息的map
        Map<String, Object> deadQueueMessageMap = new HashMap<>(2);
        //正常队列设置死信交换机 参数 key 是固定值
        deadQueueMessageMap.put("x-dead-letter-exchange",EXCHANGE_DEAD);
        //正常队列设置死信 routing-key 参数 key 是固定值
        deadQueueMessageMap.put("x-dead-letter-routing-key",deadRoutingKey);

        //设置普通队列
        channel.queueDeclare(normalQueueName,false,false,false,deadQueueMessageMap);
        channel.queueBind(normalQueueName,EXCHANGE_NORMAL,normalRoutingKey);

        //消费消息
        channel.basicConsume(normalQueueName, true, (consumerTag, message) -> {
            System.out.println("绑定key:"+message.getEnvelope().getRoutingKey());
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {});

    }
}
