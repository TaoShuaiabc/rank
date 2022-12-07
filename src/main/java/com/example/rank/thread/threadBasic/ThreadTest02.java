package com.example.rank.thread.threadBasic;

/**
 * @ClassName Synchronized
 * @Description  this锁测试
 * @Author ts
 * @Date 2022/12/6 17:47
 * @Version 1.0
 */
class Synchronized {

    public static void main(String[] args) {

        A a = new A();
        for (int i = 0; i <100 ; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <100 ; j++) {
                        a.test();
                    }
                }
            });


            thread.start();
        }


    }


}
class A {

    public static int count = 1000;

    public void test() {
        synchronized (this){
            if (count > 0) {
                count--;
                System.out.println(count);
            }
        }
    }


}