package com.example.rank.thread.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName TimerTest01
 * @Description  定时器timer类测试
 * @Author ts
 * @Date 2023/6/28 10:48
 * @Version 1.0
 */
public class TimerTest01 {

    public static void main(String[] args) throws Exception{
        Timer timer = new Timer();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse("2023-06-28 11:09:30");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("11111");
            }
        },parse,1000 * 10);
    }
}
