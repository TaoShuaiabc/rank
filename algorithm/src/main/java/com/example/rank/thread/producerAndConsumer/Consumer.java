package com.example.rank.thread.producerAndConsumer;

import java.util.List;

/**
 * @ClassName Consumer
 * @Description 消费者
 * @Author ts
 * @Date 2023/6/28 14:28
 * @Version 1.0
 */
public class Consumer {


    private List list;

    public Consumer(List list) {
        this.list = list;
    }

    /**
     * 生产方法
     */
    public  void consumerMather(){

        synchronized(list){
            while (true){
                if (list.size()==0){
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else {
                    list.forEach(item->{
                        System.out.println(Thread.currentThread().getName()+"消费消息："+item);
                    });
                    list.clear();
                    list.notify();
                }
            }
        }


    }

}
