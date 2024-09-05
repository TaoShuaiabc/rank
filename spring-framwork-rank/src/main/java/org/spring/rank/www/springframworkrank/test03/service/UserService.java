package org.spring.rank.www.springframworkrank.test03.service;


import org.spring.rank.www.springframworkrank.test03.spring.Component;
import org.spring.rank.www.springframworkrank.test03.spring.Scope;

@Component("userService")
@Scope("singleton")
public class UserService {

    public void test(){
        System.out.println("userService");
    }
}
