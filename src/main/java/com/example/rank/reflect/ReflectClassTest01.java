package com.example.rank.reflect;

/**
 * @ClassName ClassTest
 * @Description Class类，即字节码文件类测试练习
 * @Author ts
 * @Date 2023/6/29 9:28
 * @Version 1.0
 */
public class ReflectClassTest01 {


    public static void main(String[] args) throws Exception{

        //获取字节码文件的三种方式，以下案例以获取String类的字节码为例
        //第一种
        Class stringClass = Class.forName("java.lang.String");

        //第二种
        String s = "taoshuai";
        Class aClass = s.getClass();

        //第三种
        Class string = String.class;
        /**
          总结：
            1.任何类型的字节码文件在内存中（方法区中）只有一份，即同一个类的一百个对象的Class引用指向的内存地址相同
         */
    }
}
