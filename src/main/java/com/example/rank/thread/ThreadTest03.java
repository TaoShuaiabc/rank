package com.example.rank.thread;

/**
 * @ClassName ThreadTest03
 * @Description 守护线程测试
 * @Author ts
 * @Date 2022/12/7 9:40
 * @Version 1.0
 */
public class ThreadTest03 {

    public static void main(String[] args) {

        ThreadTest threadTest = new ThreadTest();
        //启动线程之前，将该线程设置成守护线程
        threadTest.setDaemon(true);
        threadTest.start();


        for (int i = 0; i <100 ; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程："+Thread.currentThread().getName());
        }

    }

}




class ThreadTest extends Thread{

    @Override
    public void run() {
        //虽然是死循环，但由于设置成了守护线程，所以当线程结束，该线程也会结束
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("守护线程："+Thread.currentThread().getName());
        }
    }
}