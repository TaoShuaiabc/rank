package com.shuaitao.rabbitmq.constant;

/**
 * @ClassName QueueConstant
 * @Description
 * @Author ts
 * @Date 2022/12/27 14:26
 * @Version 1.0
 */
public class QueueConstant {

    public final static String QUEUE_NAME = "hello";

    public final static Integer MESSAGE_COUNT = 1000;

    public static final String EXCHANGE_NAME = "logs";

    public static final String EXCHANGE_DIRECT_NAME = "direct_logs";

    public static final String EXCHANGE_TOPIC_NAME = "topic_logs";
}
