package org.spring.rank.www.springframworkrank.test03.service;

import org.spring.rank.www.springframworkrank.test03.spring.BeanPostProcessor;
import org.spring.rank.www.springframworkrank.test03.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class TaoShuaiPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        Object proxyObject = null;
        if ("userService".equals(beanName)) {
            proxyObject = Proxy.newProxyInstance(TaoShuaiPostProcess.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("after切面逻辑");

                    return method.invoke(bean, args);

                }
            });
            return proxyObject;

        }
        return proxyObject;
    }
}
