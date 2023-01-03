package com.shuaitao.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_NAME;

/**
 * @ClassName ReceiveLogs01
 * @Description  交换机模式  消费者01
 * @Author ts
 * @Date 2023/1/3 13:45
 * @Version 1.0
 */
public class ReceiveLogs01 {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        //获取一个临时队列
        String queueName = channel.queueDeclare().getQueue();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //将临时队列与交换机进行绑定
        channel.queueBind(queueName,EXCHANGE_NAME,"");

        //接收消息
        channel.basicConsume(queueName, true, (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {});
    }
}
