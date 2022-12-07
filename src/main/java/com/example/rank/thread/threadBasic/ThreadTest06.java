package com.example.rank.thread.threadBasic;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ThreadTest06
 * @Description 模拟生产者和消费者  练习wait和notify方法
 * @Author ts
 * @Date 2022/12/7 11:09
 * @Version 1.0
 */
public class ThreadTest06 {

    public static void main(String[] args) {


        List<String> strings = new ArrayList<>();
        Thread proThread = new Thread(new Producer(strings));
        Thread conThread = new Thread(new Consumer(strings));
        proThread.start();
        conThread.start();

    }


}

/**
 * 生产者
 *@author ts
 *@param
 *@return
 */
class Producer implements Runnable{

    private List list;

    public Producer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list){
            while (true) {
                if (list.size() < 1){
                    System.out.println("生产1");
                    list.add("元素1");
                    list.notifyAll();
                }
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class Consumer implements Runnable{

    private List list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list){
            while (true) {
                if (list.size() > 0){
                    System.out.println("消费1");
                    list.remove(0);
                    list.notifyAll();
                }
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}