package com.shuaitao.rabbitmq.Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName RabbitMQUtils
 * @Description mq工具类
 * @Author ts
 * @Date 2022/12/27 14:18
 * @Version 1.0
 */
public class RabbitMQUtils {

    /**
     * 获取RabbitMQ的管道
     */
    public static Channel getChannel() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("120.79.46.139");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }
}
