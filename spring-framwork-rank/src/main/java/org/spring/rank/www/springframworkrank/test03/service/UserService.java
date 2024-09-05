package org.spring.rank.www.springframworkrank.test03.service;


import org.spring.rank.www.springframworkrank.test03.spring.Autowired;
import org.spring.rank.www.springframworkrank.test03.spring.Component;
import org.spring.rank.www.springframworkrank.test03.spring.Scope;

@Component("userService")
@Scope("singleton")
public class UserService implements UserInterface {

    @Autowired
    private OrderService orderService;

    @Override
    public void test(){
        System.out.println("test方法");
    }


}
