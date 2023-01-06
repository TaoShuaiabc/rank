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
        //设置正常队列的最大长度（这里是用于测试死信队列）
        //deadQueueMessageMap.put("x-max-length",6);

        //设置普通队列
        channel.queueDeclare(normalQueueName,false,false,false,deadQueueMessageMap);
        channel.queueBind(normalQueueName,EXCHANGE_NORMAL,normalRoutingKey);

        //消费消息
        channel.basicConsume(normalQueueName, true, (consumerTag, message) -> {
            System.out.println("绑定key:"+message.getEnvelope().getRoutingKey());
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {});

      /*  //以下为测试拒绝的消息进入死信队列 param2设置为false,标识手动应答
        channel.basicConsume(normalQueueName, false, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            if ("info4".equals(s)){
                //返回param2设置未false则表示拒绝此消息
                channel.basicReject(message.getEnvelope().getDeliveryTag(),false);
            }else {
                System.out.println("绑定key:"+message.getEnvelope().getRoutingKey());
                System.out.println(new String(message.getBody()));
                //手动确认,param2设置为false,标识不批量应答,只确定应答当前消息
                channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
            }

        }, consumerTag -> {});*/

    }
}
