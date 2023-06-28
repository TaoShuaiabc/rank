package com.example.rank.thread.producerAndConsumer;

import java.util.List;

/**
 * @ClassName Producer
 * @Description  生产者
 * @Author ts
 * @Date 2023/6/28 14:28
 * @Version 1.0
 */
public class Producer {

    private List list;

    public Producer(List list) {
        this.list = list;
    }

    /**
     * 生产方法
     */
    public void producerMather(){
        synchronized(list){
        while (true){
            if (list.size()>=10){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                for (int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+"生产消息"+i);
                    list.add("消息"+i);
                }
                list.notify();
            }
        }

    }}
}
