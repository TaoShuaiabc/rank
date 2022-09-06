package com.example.rank;

/**
 * @ClassName C
 * @Description
 * @Author ts
 * @Date 2022/7/28 9:28
 * @Version 1.0
 */
public class C implements B,A{

    /**
    用于测试实现多个接口，接口中存在相同方法名该如何实现。
     目前结论是，实现时那个接口在前面，就优先实现那个接口的同名方法
    */
    @Override
    public void test() {

    }
}
