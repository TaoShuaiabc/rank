package com.design.threaddemo.test7;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test04 {
    //是否分配女朋友
    static boolean flag = false;
    static boolean money = false;
    //锁对象
    static Object key = new Object();


    /**
     * 场景：
     * 安排jack和10个程序员去加班，jack要求有女朋友才肯加班
     * 总结：
     *   在本场景中，jack在罢工（即持有锁的情况下调用sleep方法进行睡眠，会导致其他线程阻塞）,并且，如果老板分配女朋友时也需要拿同一个锁的情况下，jack就一直见不到女朋友
     * 改进方法：
     *   可以使用wait()进行改进，详情参考test2
     *   在本案例中，就可以避免像test1中导致其他线程阻塞的情况
     * 痛点：
     *   notify()只会随机叫醒一个
     * 再次改进：
     *   使用notifyAll(),可以唤醒所有  参考test3
     * 痛点：
     *    如果此时唤醒所有线程，但是只有部分线程等待的结果为true,例如老板此时给rose准备了钱，但是没有给jack准备女朋友，此时如果全部唤醒
     *    就会导致jack直接回家了
     * 再次改进： if改成while  参考test4
     *
     *
     */
    public static void main(String[] args) throws InterruptedException {


        new Thread(() -> {
            synchronized (key) {
                //如果没有女朋友
                while (!flag){
                    log.info("jack发现没有女朋友");
                    try {
                        log.info("jack开始休息");
                        key.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("jack循环等待中");
                }
                log.info("jack被老板叫醒，证明此时跳出了while,也就是分配了女朋友，jack开始工作");
            }


        },"jack").start();


        new Thread(() -> {
            synchronized (key) {
                //如果有钱
                while (!money){
                    log.info("rose发现没有钱");
                    try {
                        log.info("rose开始休息");
                        key.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("rose被老板叫醒，证明此时跳出了while,也就是分配了女朋友，jack开始工作");


            }


        },"rose").start();



        Thread.sleep(1000);
        new Thread(()->{
            synchronized (key) {
                log.info("老板分配了一个女朋友给jack");
                flag = true;
                log.info("老板发了工资给rose");
                money = true;
                //只会随机叫醒一个
                //key.notify();
                //会同时唤醒jack和rose
                key.notifyAll();
            }

        }).start();


    }
}
