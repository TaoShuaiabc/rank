package com.example.rank.thread.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName test
 * @Description
 * @Author ts
 * @Date 2023/6/28 14:37
 * @Version 1.0
 */
public class Test {


    public static void main(String[] args) {

        List list = new ArrayList();

        Producer producer = new Producer(list);

        Consumer consumer = new Consumer(list);

       Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                producer.producerMather();
            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.consumerMather();
            }
        });
        producerThread.setName("生产者线程：");
        consumerThread.setName("消费者线程：");
        producerThread.start();
        consumerThread.start();
    }
}
