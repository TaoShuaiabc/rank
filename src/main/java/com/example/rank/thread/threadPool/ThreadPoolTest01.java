package com.example.rank.thread.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolTest01
 * @Description  创建线程池
 * @Author ts
 * @Date 2022/12/7 11:33
 * @Version 1.0
 */
public class ThreadPoolTest01 {


    public static void main(String[] args) {
        //查看计算机有多少核
       // System.out.println(Runtime.getRuntime().availableProcessors());

        //创建一个未指定线程工厂的线程池(默认线程工厂)
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(10, true)
        );

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":线程池任务");
                }
            }
        });
    }
}
