package org.kafka.www.kafkademo.test02;




import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class SpringBootProductController {

    private final static String TOPIC_NAME = "test900";


    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a msg");
    }
}
