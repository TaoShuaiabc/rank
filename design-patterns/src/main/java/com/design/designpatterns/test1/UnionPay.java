package com.design.designpatterns.test1;

import org.springframework.stereotype.Component;

@Component
public class UnionPay extends AbstractPaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("银联支付");
    }
}
