package com.design.threaddemo.test3;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 自己实现一把锁
 */
public class TaoShuaiLock {

    private volatile  int status = 0;
    private static final Unsafe unsafe = getUnsafe();
    private static long valueOffset = 0;


    static {
        try {
            valueOffset = unsafe.objectFieldOffset(TaoShuaiLock.class.getDeclaredField("status"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public void lock(){

        while (!compareAndSet(0,1)){

        }
    }

    public void unlock(){
        status = 0;
    }

    /**
     *  CAS 比较和交换
     */
    private boolean compareAndSet(int except, int newValue) {

        return unsafe.compareAndSwapInt(this,valueOffset,except,newValue);
    }

    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);

        } catch (Exception e) {
        }
        return null;
    }
}
