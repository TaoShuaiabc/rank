package com.shuaitao.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;

import java.util.Scanner;

import static com.shuaitao.rabbitmq.constant.QueueConstant.EXCHANGE_DIRECT_NAME;

/**
 * @ClassName EmitLogDirect
 * @Description exchange的direct模式的生产者
 * @Author ts
 * @Date 2023/1/4 14:02
 * @Version 1.0
 */
public class EmitLogDirect {

    public static void main(String[] args) throws Exception{

        Channel channel = RabbitMQUtils.getChannel();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String message = scanner.nextLine();
            switch (message){
                case "info":channel.basicPublish(EXCHANGE_DIRECT_NAME,"info",null,message.getBytes());
                    break;
                case "error":channel.basicPublish(EXCHANGE_DIRECT_NAME,"error",null,message.getBytes());
                    break;
                case "warning":channel.basicPublish(EXCHANGE_DIRECT_NAME,"warning",null,message.getBytes());
                    break;
                case "test":channel.basicPublish(EXCHANGE_DIRECT_NAME,"",null,message.getBytes());
                    break;
            }
            System.out.println("发送消息："+message);
        }

    }
}
