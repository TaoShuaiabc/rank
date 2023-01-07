package com.shuaitao.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_DIRECT_NAME;

/**
 * @ClassName ReceiveLogsDirect02
 * @Description  exchange的direct模式的消费者02
 * @Author ts
 * @Date 2023/1/4 14:02
 * @Version 1.0
 */
public class ReceiveLogsDirect02 {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_DIRECT_NAME,"direct");

        //声明队列
        channel.queueDeclare("console", false, false, false, null);

        //将队列和和交换机进行绑定并设置绑定key
        channel.queueBind("console",EXCHANGE_DIRECT_NAME,"info");
        channel.queueBind("console",EXCHANGE_DIRECT_NAME,"warning");

        //消费消息
        channel.basicConsume("console", true, (consumerTag, message) -> {
            System.out.println("绑定key:"+message.getEnvelope().getRoutingKey());
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {});
    }

}
