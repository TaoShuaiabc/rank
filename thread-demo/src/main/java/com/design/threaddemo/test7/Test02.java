package com.design.threaddemo.test7;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test02 {
    //是否分配女朋友
    static boolean flag = false;
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
     */
    public static void main(String[] args) throws InterruptedException {


        new Thread(() -> {
            synchronized (key) {
                //如果有女朋友
                if (flag) {
                    log.info("jack开始工作");
                } else {
                    try {
                        log.info("jack发现没有女朋友");
                        log.info("jack开始罢工5秒钟");
                        //如果没有分配女朋友，则jack去休息
                        key.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //睡眠五秒钟后，判断是否分配
                    log.info("jack睡醒了");
                    if (flag) {
                        log.info("发现老板分配了女朋友");
                        log.info("jack开始工作");
                    }else {
                        log.info("jack直接回家");
                    }
                }


            }


        },"jack").start();

        new Thread(()->{
            synchronized (key) {
                log.info("老板分配了一个女朋友给jack");
                flag = true;
                key.notify();
            }

        }).start();

        //创建10个程序员
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                synchronized (key) {
                    log.info(Thread.currentThread().getName()+"程序员开始加班！");
                }
            },i+"").start();
        }


    }
}
