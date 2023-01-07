package com.shuaitao.rabbitmq.releaseAck;

import com.rabbitmq.client.Channel;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;
import com.shuaitao.rabbitmq.constant.QueueConstant;

/**
 * @ClassName batchReleaseAck
 * @Description 批量消息确认-生产者
 * @Author ts
 * @Date 2022/12/29 10:55
 * @Version 1.0
 */
public class BatchReleaseAck {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();
       //批量确认消息大小
        int batchSize = 100;
        //未确认消息个数
        int outstandingMessageCount = 0;
        channel.queueDeclare(QueueConstant.QUEUE_NAME,true,false,false,null);
        //开启发布确认
        channel.confirmSelect();

        for (int i =0;i< QueueConstant.MESSAGE_COUNT;i++){
            //发布消息
            channel.basicPublish("",QueueConstant.QUEUE_NAME,null,String.valueOf(i).getBytes());
            outstandingMessageCount++;
            if (outstandingMessageCount==batchSize){
                //消息确认机制，如果返回false或者超时未返回，生产者可以消息重发
                boolean b = channel.waitForConfirms();
                outstandingMessageCount=0;
                if (b){
                    System.out.println("消息发送成功");
                }
            }
            //为了确保还有剩余没有确认消息 再次确认
            if (outstandingMessageCount>0){
                channel.waitForConfirms();
            }

        }

    }
}
