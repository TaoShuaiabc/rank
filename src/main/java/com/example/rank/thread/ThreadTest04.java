package com.example.rank.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadTest04
 * @Description 定时任务
 * @Author ts
 * @Date 2022/12/7 9:57
 * @Version 1.0
 */
class ThreadTest04{

    public static void main(String[] args) {


        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName());
            }
        },0,1,TimeUnit.SECONDS);

    }


}
class Test implements Runnable{

    @Override
    public void run() {
        System.out.printf("执行定时任务："+Thread.currentThread().getName());
    }
}


