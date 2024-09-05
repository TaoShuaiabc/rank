package org.spring.rank.www.springframworkrank.test03.taoshuai;

import org.spring.rank.www.springframworkrank.test03.service.UserService;
import org.spring.rank.www.springframworkrank.test03.spring.TaoShuaiApplicationContext;

public class Test {

    public static void main(String[] args) {


        TaoShuaiApplicationContext context = new TaoShuaiApplicationContext(AppConfig.class);

        UserService userService = (UserService) context.getBean("userService");

        userService.test();
    }
}
