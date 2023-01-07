package com.shuaitao.rabbitmq.releaseAck;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;
import com.shuaitao.rabbitmq.Utils.RabbitMQUtils;
import com.shuaitao.rabbitmq.constant.QueueConstant;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static com.shuaitao.rabbitmq.constant.QueueConstant.MESSAGE_COUNT;

/**
 * @ClassName PublishMessageAsync
 * @Description  异步确认发布
 * @Author ts
 * @Date 2022/12/30 10:53
 * @Version 1.0
 */
public class PublishMessageAsync {


    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtils.getChannel();

        channel.queueDeclare(QueueConstant.QUEUE_NAME,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();

        /**
         * 创建一个线程安全的map容器，
         * key是消息的唯一序号
         * value是具体的消息
         * 用于将序号和消息关联,通过回调函数,可以筛选那些消息被确认，那些消息没有确认
         */
        ConcurrentSkipListMap<Long, Object> longObjectConcurrentSkipListMap = new ConcurrentSkipListMap<>();

        //确认消息
        ConfirmCallback ackCallback = (deliveryTag, multiple)->{
            //判断是否开启批量确认
            if (multiple){
                //返回的是当前所有已确认的消息
                ConcurrentNavigableMap<Long, Object> longObjectConcurrentNavigableMap = longObjectConcurrentSkipListMap.headMap(deliveryTag, true);
                //把当前所有已确认的消息进行删除，那么剩下的就是未确认的消息
                longObjectConcurrentNavigableMap.clear();
            }else {
                //单个删除已确认的消息
                longObjectConcurrentSkipListMap.remove(deliveryTag);
            }
            System.out.println(deliveryTag+"消息已被确认");
        };
        //未确认消息
        ConfirmCallback nackCallback = (deliveryTag, multiple)->{
            System.out.println(deliveryTag+"消息未确认"+"==具体消息是："+longObjectConcurrentSkipListMap.get(deliveryTag));
        };
        //异步回调监听器
        channel.addConfirmListener(ackCallback,nackCallback);

        for (int i =0;i<MESSAGE_COUNT;i++){
            String message = "消息"+i;
            channel.basicPublish("",QueueConstant.QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            //将消息序号和消息存储进map
            longObjectConcurrentSkipListMap.put(channel.getNextPublishSeqNo(),message);
            System.out.println("发布消息："+message+"成功");
        }

    }
}
