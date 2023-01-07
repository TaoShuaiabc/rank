package com.shuaitao.rabbitmq.workQueues;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;
import com.shuaitao.rabbitmq.constant.QueueConstant;

import java.util.Scanner;

/**
 * @ClassName WorkQueueProducer
 * @Description 工作队列生产者
 * @Author ts
 * @Date 2022/12/27 14:25
 * @Version 1.0
 */
public class WorkQueueProducer {


    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();
        channel.queueDeclare(QueueConstant.QUEUE_NAME,false,false,false,null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",QueueConstant.QUEUE_NAME,null,message.getBytes());
            System.out.println("消息发送完成:" + message);
        }

    }

}
