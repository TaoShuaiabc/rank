package com.example.rank.reflect;

import java.lang.reflect.Method;

/**
 * @ClassName ReflectClassTest03
 * @Description  练习反射调用方法
 * @Author ts
 * @Date 2023/6/30 10:56
 * @Version 1.0
 */
public class ReflectClassTest03 {

    public static void main(String[] args) throws Exception{


        //获取字节码
        Class<?> aClass = Class.forName("com.example.rank.reflect.Student");

        //创建对象
        Object o = aClass.newInstance();
        //获取方法对象
        Method login = aClass.getMethod("login", String.class, String.class);
        //调用方法
        Object admin = login.invoke(o, "admin", "123");

        System.out.println(admin);
    }
}
