package org.spring.rank.www.springframworkrank.test03.taoshuai;

import org.spring.rank.www.springframworkrank.test03.service.UserInterface;
import org.spring.rank.www.springframworkrank.test03.spring.TaoShuaiApplicationContext;

public class Test {

    public static void main(String[] args) {


        TaoShuaiApplicationContext context = new TaoShuaiApplicationContext(AppConfig.class);

        UserInterface userInterface = (UserInterface) context.getBean("userService");

        userInterface.test();
    }
}
