package com.design.threaddemo.test7;

/**
 * 本案例用于测试Thread.sleep()是否会释放cpu资源
 * 先创建一个子线程一直打印，然后让主线程睡眠，查看主线程睡眠期间，子线程是否正常打印，如果打印则证明Thread.sleep()会释放cpu资源
 * 结论：
 *      Thread.sleep()会释放cpu资源
 */
public class Test5 {


    public static void main(String[] args) throws InterruptedException {


        new Thread(()->{
            while(true){
                System.out.println("子线程打印！");
            }
        }).start();


        Thread.sleep(5000);
    }

}
