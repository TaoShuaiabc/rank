package com.design.designpatterns.test1;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PaymentStrategyFactory implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public AbstractPaymentStrategy getPaymentStrategy(int payType) {
        return switch (payType) {
            case 1 -> applicationContext.getBean(WeChatPay.class);
            case 2 -> applicationContext.getBean(AliPay.class);
            case 3 -> applicationContext.getBean(UnionPay.class);
            default -> throw new IllegalArgumentException("无效的支付类型");
        };

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
