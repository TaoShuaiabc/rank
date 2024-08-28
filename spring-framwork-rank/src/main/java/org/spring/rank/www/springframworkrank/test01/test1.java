package org.spring.rank.www.springframworkrank.test01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test1 {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = (UserService) context.getBean("userService");
    }
}
