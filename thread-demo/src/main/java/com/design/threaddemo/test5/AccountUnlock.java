package com.design.threaddemo.test5;

public class AccountUnlock implements Account{

    private Integer balance;

    public AccountUnlock(Integer balance) {
        this.balance = balance;
    }
    @Override
    public void withdrawMoney(Integer Money) {
        this.balance -= Money;
    }

    @Override
    public Integer query() {
        return balance;
    }
}
