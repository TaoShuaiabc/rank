package com.example.rank.lambda;

/**
 * @ClassName LambdaTest01
 * @Description 使用lambda表达式创建线程
 * @Author ts
 * @Date 2022/12/8 15:00
 * @Version 1.0
 */
public class LambdaTest01 {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();

    }



}
