package com.example.rank.synchronizedtest;

/**
 * @ClassName test01
 * @Description
 * @Author ts
 * @Date 2022/12/17 10:19
 * @Version 1.0
 */
public class test01 {


    public static void main(String[] args) {

        test test = new test();

       /* Thread thread01 = new Thread(() -> {
            new test().test01();
        });
        thread01.setName("thread01");
        Thread thread02 = new Thread(() -> {
            new test().test01();
        });
        thread01.setName("thread2");
        Thread thread03 = new Thread(() -> {
            new test().test01();
        });
        thread01.setName("thread03");
        Thread thread04 = new Thread(() -> {
            new test().test01();
        });
        thread01.setName("thread04");
        Thread thread05 = new Thread(() -> {
            new test().test01();
        });
        thread01.setName("thread05");*/

        /*Thread thread01 = new Thread(() -> {
            test.test01();
        });
        thread01.setName("thread01");
        Thread thread02 = new Thread(() -> {
            test.test01();
        });
        thread01.setName("thread2");
        Thread thread03 = new Thread(() -> {
            test.test01();
        });
        thread01.setName("thread03");
        Thread thread04 = new Thread(() -> {
            test.test01();
        });
        thread01.setName("thread04");
        Thread thread05 = new Thread(() -> {
            test.test01();
        });*/
        Thread thread01 = new Thread(() -> {
            test.test02();
        });
        thread01.setName("thread01");
        Thread thread02 = new Thread(() -> {
            test.test02();
        });
        thread01.setName("thread2");
        Thread thread03 = new Thread(() -> {
            test.test02();
        });
        thread01.setName("thread03");
        Thread thread04 = new Thread(() -> {
            test.test02();
        });
        thread01.setName("thread04");
        Thread thread05 = new Thread(() -> {
            test.test02();
        });
        thread01.setName("thread05");
        thread01.start();
        thread02.start();
        thread03.start();
        thread04.start();
        thread05.start();
    }
}
