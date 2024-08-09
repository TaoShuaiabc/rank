package com.design.threaddemo.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 无锁
 */
public class test {

    public static void main(String[] args) throws InterruptedException {

        //AccountUnlock account = new AccountUnlock(10000);
        //AccountSync account = new AccountSync(10000);
        AccountCas account = new AccountCas(new AtomicInteger(10000));
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 500; i++) {

            threads.add(new Thread(() -> {
                account.withdrawMoney(20);
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(account.query());

    }


}
