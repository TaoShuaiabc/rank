package com.design.threaddemo.test5;

/*
 * 加锁
 */
public class AccountSync implements Account{

    private Integer balance;

    public AccountSync(Integer balance) {
        this.balance = balance;
    }

    @Override
    public void withdrawMoney(Integer Money) {
        synchronized (this){
            this.balance -= Money;
        }
    }

    @Override
    public Integer query() {
        return balance;
    }
}
