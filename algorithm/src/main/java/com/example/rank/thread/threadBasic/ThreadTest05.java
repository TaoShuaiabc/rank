package com.example.rank.thread.threadBasic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName ThreadTest05
 * @Description
 * @Author ts
 * @Date 2022/12/7 10:22
 * @Version 1.0
 */
public class ThreadTest05 {

    public static void main(String[] args) {
        //创建一个未来式任务
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(5000);
                return "结果";
            }
        });

        //可以如此创建线程对象的原因是Thread构造函数形参是Runnable类型,而FutureTask是实现了Runnable的接口
        Thread thread = new Thread(futureTask);
        thread.start();

        //当前是main线程执行到这里，获取任务线程的返回值，会阻塞
        try {
            Object o = futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
