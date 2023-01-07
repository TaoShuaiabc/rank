package com.shuaitao.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_DIRECT_NAME;

/**
 * @ClassName ReceiveLogsDirect01
 * @Description exchange的direct模式的消费者01
 * @Author ts
 * @Date 2023/1/4 14:01
 * @Version 1.0
 */
public class ReceiveLogsDirect01 {


    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_DIRECT_NAME,"direct");

        //声明队列
        channel.queueDeclare("disk", false, false, false, null);

        //将队列和和交换机进行绑定并设置绑定key
        channel.queueBind("disk",EXCHANGE_DIRECT_NAME,"error");

        //消费消息
        channel.basicConsume("disk", true, (consumerTag, message) -> {
            System.out.println("绑定key:"+message.getEnvelope().getRoutingKey());
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {});
    }
}
