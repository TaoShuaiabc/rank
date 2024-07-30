package com.design.threaddemo.test3;

public class LockTest {

    int k = 0;
    TaoShuaiLock taoShuaiLock = new TaoShuaiLock();

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        lockTest.start();
    }


    public void start() throws InterruptedException {


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test01();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test01();
            }
        });

        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();

        System.out.println(k);
    }

    public void test01() {

        taoShuaiLock.lock();

            for (int i = 0; i <= 9999; i++) {
                k = k + 1;
            }
        taoShuaiLock.unlock();

    }

}
