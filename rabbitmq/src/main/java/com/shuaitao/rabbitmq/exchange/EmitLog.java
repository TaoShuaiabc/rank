package com.shuaitao.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import java.util.Scanner;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_NAME;

/**
 * @ClassName EmitLog
 * @Description 交换机模式  生产者
 * @Author ts
 * @Date 2023/1/3 13:46
 * @Version 1.0
 */
public class EmitLog {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()){
            String message = sc.nextLine();
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes("UTF-8"));
            System.out.println("发送消息："+message);
        }

    }
}
