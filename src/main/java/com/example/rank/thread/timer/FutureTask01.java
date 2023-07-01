package com.example.rank.thread.timer;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FutureTask
 * @Description   实现线程的第三种方式 实现Callable接口
 * @Author ts
 * @Date 2023/6/28 14:01
 * @Version 1.0
 */
public class FutureTask01 {

    public static void main(String[] args) throws Exception{


        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {

                return 666;
            }
        });


        Thread t1 = new Thread(task);

        //启动线程
        t1.start();

        //在主线程中获取t1线程的返回值
        //main主线程在此行又被阻塞的风险，因为主线程必须等待t1线程的call执行完毕
        Object obj= task.get();


        System.out.println(obj);


    }
}
