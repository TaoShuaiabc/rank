package com.design.threaddemo.test5;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * cas
 */
public class AccountCas implements Account{

    private AtomicInteger balance;

    public AccountCas(AtomicInteger balance) {
        this.balance = balance;
    }

    @Override
    public void withdrawMoney(Integer money) {

        while (true){
            int i = balance.get();
            int j = i-money;
            if (balance.compareAndSet(i,j)){
                break;
            }

        }

    }

    @Override
    public Integer query() {
        return balance.get();
    }
}
