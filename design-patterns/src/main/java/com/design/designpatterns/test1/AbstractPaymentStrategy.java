package com.design.designpatterns.test1;

public abstract class AbstractPaymentStrategy implements PaymentStrategy{


    //模板方法
    public final void commonPay(double amount){

        //必须方法
        check(amount);
        //扩展抽象方法
        pay(amount);

    }

    private void check(double amount) {
        if (amount == 0){
            throw new IllegalArgumentException("无效参数");
        }
    }
}
