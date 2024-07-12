package com.design.designpatterns.test1;

import org.springframework.stereotype.Component;

@Component
public class AliPay extends AbstractPaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("支付宝支付");
    }
}
