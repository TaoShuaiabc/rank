package org.spring.rank.www.springframworkrank.test03.spring;

public interface BeanPostProcessor {

     default Object postProcessBeforeInitialization(Object bean, String beanName){
         return bean;
     };
     default Object postProcessAfterInitialization(Object bean, String beanName){
         return bean;
     };
}
