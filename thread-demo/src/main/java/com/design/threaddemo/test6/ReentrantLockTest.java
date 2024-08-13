package com.design.threaddemo.test6;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {


        int N = 10;
        Thread[] threads = new Thread[N];


        for (int i = 0; i < N; i++) {
            threads[i] = new Thread(()->{
                lock.lock();
                try {
                    System.out.println("线程名称"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }finally {
                    lock.unlock();
                }
            });
        }


        lock.lock();
        try {
            for (Thread thread : threads) {
                thread.start();
                Thread.sleep(200);
            }
        }finally {
           lock.unlock();
        }

        for (Thread thread : threads) {
            thread.join();
        }


    }
}
