package com.example.rank.reflect;

/**
 * @ClassName ReflectClassTest02
 * @Description 研究一下 Class.forName()发生了什么？
 * @Author ts
 * @Date 2023/6/29 13:30
 * @Version 1.0
 */
public class ReflectClassTest02 {


    public static void main(String[] args) throws Exception{

        //会执行MyClass的静态代码块
        //Class.forName()方法的执行会进行类加载
        Class.forName("com.example.rank.reflect.MyClass");
    }

}

class MyClass{


    //静态代码块在类加载的时候执行，并且只执行一次！
    static {

        System.out.println("MyClass!");
    }
}