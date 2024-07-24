package com.design.threaddemo.test1;

public class VisibilityTest {



    private volatile boolean flag = true;
    private int count = 0;

    public  void refresh(){
        flag = false;
        System.out.println(Thread.currentThread().getName()+"修改变量flag="+flag);
    }

    public  void load(){
        System.out.println(Thread.currentThread().getName()+"开始执行");
        while (flag){
            count++;
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"结束循环"+count);
    }
}
