package com.shuaitao.rabbitmq.releaseAck;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;
import com.shuaitao.rabbitmq.constant.QueueConstant;

/**
 * @ClassName SingleReleaseAck
 * @Description 单个发布确认-生产者
 * @Author ts
 * @Date 2022/12/29 10:38
 * @Version 1.0
 */
public class SingleReleaseAck {


    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();
        channel.queueDeclare(QueueConstant.QUEUE_NAME,true,false,false,null);
        //开启发布确认
        channel.confirmSelect();

        for (int i =0;i< QueueConstant.MESSAGE_COUNT;i++){
            //发布消息
            channel.basicPublish("",QueueConstant.QUEUE_NAME,null,String.valueOf(i).getBytes());

            //消息确认机制，如果返回false或者超时未返回，生产者可以消息重发
            boolean b = channel.waitForConfirms();
            if (b){
                System.out.println("消息发送成功");
            }
        }

    }
}
