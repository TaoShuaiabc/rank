package org.spring.rank.www.springframworkrank.test04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Test01 {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println(context.getMessage("test", null, new Locale("zn")));

    }
}
