package com.example.rank.exception;

/**
 * @ClassName ExceptionTest01
 * @Description
 * @Author ts
 * @Date 2023/1/9 11:39
 * @Version 1.0
 */
public class ExceptionTest01 {


    /**
     *  Exception这个类分为RuntimeException子类 和其他子类，RuntimeException及其子类属于运行时异常，其他子类属于编译时异常
     */
    public static void main(String[] args){
        dosum();
    }


    public static void dosum(){

        try{
            dosum02();
        }catch (Exception e){
            System.out.println(e);
        }


        System.out.println("2");

    }

    public static void dosum02(){
        System.out.println(10/0);

    }
}
