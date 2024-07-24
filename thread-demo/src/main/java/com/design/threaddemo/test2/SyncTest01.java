package com.design.threaddemo.test2;

public class SyncTest01 {

    public static void main(String[] args) {

        SyncDemo syncDemo1 = new SyncDemo();
        SyncDemo syncDemo2 = new SyncDemo();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syncDemo1.test01();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                syncDemo2.test01();
            }
        });

        thread1.start();
        thread2.start();
    }
}
