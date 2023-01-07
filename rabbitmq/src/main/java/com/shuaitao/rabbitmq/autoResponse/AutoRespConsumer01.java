package com.shuaitao.rabbitmq.autoResponse;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;
import com.shuaitao.rabbitmq.constant.QueueConstant;

/**
 * @ClassName AutoRespConsumer01
 * @Description  测试手动应答消费者01
 *  1.经过测试发现，如果设置自动应答为false,并且没有手动应答，则消息会一直堆积在队列中
 *  2.经过测试发现，当队列中有消息堆积，且没有消费者时，此时一启动消费者，将会自动消费掉消息
 *  3.经过测试发现，当有多个消费者时，且设置手动应答，但此时A消息正在被消费者A消费时，由于消费者A挂了，所以没有手动应答，当到检测连接或者信道被关闭时，此消息会分配到给其他消费者重新消费
 * @Author ts
 * @Date 2022/12/28 10:22
 * @Version 1.0
 */
public class AutoRespConsumer01 {


    public static void main(String[] args) throws Exception {


        Channel channel = RabbitMQUtils.getChannel();
        //设置不公平分发
        //channel.basicQos(1);
        //设置预取值
        channel.basicQos(2);
        //接收消息
        DeliverCallback deliverCallback = (consumerTag, message)->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new String(message.getBody()));
            //手动应答方法，param1是消息标记，param2是选择是否批量应答（即是否批量应答当前信道中的所有消息，包括还未消费的消息）
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        //消息取消消费的回调
        CancelCallback cancelCallback = (consumerTag) ->{
            System.out.println("取消消费");
        };
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费者未成功消费的回调
         */
        channel.basicConsume(QueueConstant.QUEUE_NAME,false,deliverCallback,cancelCallback);
    }


}
