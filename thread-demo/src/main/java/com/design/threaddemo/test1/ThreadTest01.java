package com.design.threaddemo.test1;

public class ThreadTest01 {

    //用于测试可见性
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(i);
                }
            }
        });
        thread1.start();

        //Thread.sleep(1000);
        System.out.println("主线程结束");
        /*VisibilityTest visibilityTest = new VisibilityTest();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                visibilityTest.load();
            }
        });
        thread1.start();

        Thread.sleep(1000);

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                visibilityTest.refresh();
            }
        });
        thread2.start();*/
    }



}
