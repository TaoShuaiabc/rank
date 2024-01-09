package com.example.rank.synchronizedtest;

/**
 * @ClassName test
 * @Description
 * @Author ts
 * @Date 2022/12/17 10:20
 * @Version 1.0
 */
public class test {

    public void test01(){

        synchronized(this){
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void test02(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
