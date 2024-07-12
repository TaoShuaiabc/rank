package com.design.designpatterns.test1;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {



    @Resource
    private PaymentStrategyFactory paymentStrategyFactory;


    @GetMapping("/pay")
    private void pay(){
        int payType = 1;
        double amount = 0;
        AbstractPaymentStrategy abstractPaymentStrategy = paymentStrategyFactory.getPaymentStrategy(payType);
        abstractPaymentStrategy.commonPay(amount);
    }

}
