package org.kafka.www.kafkademo.test01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 消费者
 */
public class MsgConsumer {

    private final static String TOPIC_NAME = "test111";
    private final static String CONSUMER_GROUP_NAME = "testGroup";


    public static void main(String[] args) {


        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.219.88:9092,192.168.219.88:9093,192.168.219.88:9094");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);

        //是否自动提交offset，默认就是true,一般设置成false
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);

        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");


        //props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);

        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10 * 1000);

        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);

        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 30 * 1000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        //consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME, 0)));

        while (true){
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                 System.out.printf("收到消息：partition = %d,offset = %d, key = %s, value = %s%n", record.partition(),
                         record.offset(), record.key(), record.value());
                 }

            if (records.count()>0){
                consumer.commitSync();
            }

        }


    }
}
