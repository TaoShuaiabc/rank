package com.shuaitao.rabbitmq.exchange.Topic;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import java.util.Scanner;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_TOPIC_NAME;

/**
 * @ClassName EmitLogTopic
 * @Description Topic模式-生产者
 * @Author ts
 * @Date 2023/1/5 15:02
 * @Version 1.0
 */
public class EmitLogTopic {


    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String message = sc.nextLine();
            channel.basicPublish(EXCHANGE_TOPIC_NAME,"lazy.orange.male.rabbit",null,message.getBytes("UTF-8"));
            System.out.println("发送消息："+message);
        }

    }
}
