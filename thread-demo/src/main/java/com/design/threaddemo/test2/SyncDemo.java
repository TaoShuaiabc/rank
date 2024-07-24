package com.design.threaddemo.test2;

public class SyncDemo {

    public synchronized void test01(){

            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }


    }
}
