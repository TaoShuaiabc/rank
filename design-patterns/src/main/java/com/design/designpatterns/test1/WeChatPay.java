package com.design.designpatterns.test1;

import org.springframework.stereotype.Component;

@Component
public class WeChatPay extends AbstractPaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("wx支付");
    }
}
