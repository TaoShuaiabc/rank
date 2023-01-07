package com.shuaitao.rabbitmq.exchange.Topic;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import static com.shuaitao.rabbitmq.EnumPack.ExchangeTypeEnum.TOPIC;
import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_TOPIC_NAME;

/**
 * @ClassName ReceiveLogsTopic01
 * @Description  Topic模式-消费者01
 * @Author ts
 * @Date 2023/1/5 15:02
 * @Version 1.0
 */
public class ReceiveLogsTopic01 {
    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_TOPIC_NAME,TOPIC.getCode());

        //声明队列
        channel.queueDeclare("Q1",false, false, false, null);

        //绑定队列和交换机
        channel.queueBind("Q1",EXCHANGE_TOPIC_NAME,"*.orange.*");

        //消费消息
        channel.basicConsume("Q1",true,(consumerTag, message) -> {
            System.out.println("绑定key:"+message.getEnvelope().getRoutingKey());
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {});
    }
}
