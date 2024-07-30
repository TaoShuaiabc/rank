package org.kafka.www.kafkademo.test01;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MsgProducer {

    private final static String TOPIC_NAME = "test999";

    public static void main(String[] args) throws ExecutionException, InterruptedException {



        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.219.88:9092,192.168.219.88:9093,192.168.219.88:9094");

        props.put(ProducerConfig.ACKS_CONFIG,"1");

        props.put(ProducerConfig.RETRIES_CONFIG,3);

        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,300);


        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);

        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

        props.put(ProducerConfig.LINGER_MS_CONFIG,10);

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());


        Producer<String, String> producer = new KafkaProducer<>(props);

        int masNum =5;

        final CountDownLatch countDownLatch = new CountDownLatch(masNum);
        for (int i = 0; i <=masNum; i++) {

            Order order = new Order(34534, 345 + 100, 1000.00);
            //指定发送分区
            /*ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC_NAME,
                    0,String.valueOf(order.getId()), JSON.toJSONString(order));*/

            //不指定发送分区
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC_NAME,
                    String.valueOf(order.getId()), JSON.toJSONString(order));

            //同步方式发送
            //RecordMetadata metadata = producer.send(producerRecord).get();


            producer.send(producerRecord, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        System.out.println("发送消息失败{}"+ Arrays.toString(exception.getStackTrace()));
                    }
                    if (metadata != null) {
                        System.out.println(("异步方式发送消息结果：" + "topic‐" + metadata.topic() + "|partition‐"
                                + metadata.partition() + "|offset‐" + metadata.offset()));
                    }
                    countDownLatch.countDown();
                }
            });

            /*System.out.println("同步方式发送消息结果：" + "topic‐" + metadata.topic() + "|partition‐"
                 + metadata.partition() + "|offset‐" + metadata.offset());*/


        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.close();
    }
}
