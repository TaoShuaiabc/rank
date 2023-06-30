package com.example.rank.reflect;

/**
 * @ClassName Student
 * @Description
 * @Author ts
 * @Date 2023/6/30 10:53
 * @Version 1.0
 */
public class Student {

    private String name;

    private String password;

    public Student(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String login(String name, String password){
        if (name.equals("admin") && password.equals("123")){
            return "登录成功！";
        }
        return "登录失败！";
    }
}
