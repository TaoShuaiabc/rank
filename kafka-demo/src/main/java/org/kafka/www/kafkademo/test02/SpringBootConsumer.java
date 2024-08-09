package org.kafka.www.kafkademo.test02;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class SpringBootConsumer {

    @KafkaListener(topics = "test675",groupId = "theShuai")
    public void theShuaiGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {

        String value = record.value();
        System.out.println(value);
        System.out.println(record);
        ack.acknowledge();
    }
}
