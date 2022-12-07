package com.example.rank.thread;

/**
 * @ClassName ThreadFoundTest
 * @Description 线程创建测试
 * @Author ts
 * @Date 2022/12/6 16:40
 * @Version 1.0
 */
public class ThreadTest01 {

    public static void main(String[] args) {


        //设置线程结束开关
        boolean run = false;

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <100 ; j++) {
                        System.out.println(Thread.currentThread().getName()+"开始卖票");
                    }


                    if (false){
                        //return可以安全结束线程
                        return;
                    }
                }
            });

            //启动线程
            thread.start();
        }

    }
}
