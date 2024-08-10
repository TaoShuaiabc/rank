package com.design.threaddemo.test6;

public class SynchronizedTest {


    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {


        int N = 10;
        Thread[] threads = new Thread[N];


        for (int i = 0; i < N; i++) {
            threads[i] = new Thread(()->{
                synchronized (lock){
                    System.out.println("线程名称"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }


        synchronized (lock){
            for (Thread thread : threads) {
                thread.start();
                Thread.sleep(200);
            }
        }

        for (Thread thread : threads) {
            thread.join();
        }


    }
}
