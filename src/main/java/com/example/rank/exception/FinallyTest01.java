package com.example.rank.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName FinallyTest01
 * @Description
 * @Author ts
 * @Date 2023/1/10 10:46
 * @Version 1.0
 */
public class FinallyTest01 {

    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("E:\\360MoveData\\Users\\ASUS\\Desktop\\开发文件\\开发工具\\rabbitMQ安装包\\笔记\\尚硅谷_消息中间件RabbitMQ_课件.pdf");

            String s =null;
            s.toString();


            System.out.println("1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            System.out.println("2");
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
