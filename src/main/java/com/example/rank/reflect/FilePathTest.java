package com.example.rank.reflect;

import java.io.FileNotFoundException;
import java.util.ResourceBundle;

/**
 * @ClassName FilePathTest
 * @Description
 * @Author ts
 * @Date 2023/6/29 13:44
 * @Version 1.0
 */
public class FilePathTest {

    public static void main(String[] args) throws FileNotFoundException {
       /* String path = Thread.currentThread()
                .getContextClassLoader()
                .getResource("111.properties")
                .getPath();
        System.out.println(path);*/
        ResourceBundle bundle = ResourceBundle.getBundle("111");

        //FileReader fileReader = new FileReader("src/main/java/com/example/rank/reflect/111.properties");
    }
}
