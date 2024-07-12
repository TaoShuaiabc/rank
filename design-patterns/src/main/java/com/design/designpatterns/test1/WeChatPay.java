package com.design.designpatterns.test1;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;



@Component
public class WeChatPay extends AbstractPaymentStrategy{

    @Resource
    private AliPay aliPay;

    @Override
    public void pay(double amount) {
        System.out.println("wx支付");
        aliPay.pay(123.32);
    }
}
