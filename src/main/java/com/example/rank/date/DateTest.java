package com.example.rank.date;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateTest
 * @Description
 * @Author ts
 * @Date 2023/1/4 9:44
 * @Version 1.0
 */
public class DateTest {
    public static void main(String[] args) {

        Date newTime = new Date();
        System.out.println(newTime);
        SimpleDateFormat sif = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sif.format(newTime);
        System.out.println(format);

        BigDecimal bigDecimal = new BigDecimal(100);
    }
}
