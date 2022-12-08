package com.example.rank.lambda;

/**
 * @ClassName Person
 * @Description
 * @Author ts
 * @Date 2022/12/8 15:33
 * @Version 1.0
 */
public class Person {
    private int age;
    private String name;
    public Person(String name,int age) {
        this.name = name;
        this.age = age;
    }
    public Person() {
    }
    @Override
    public String toString() {
        return  "年龄：" + age +",姓名：" + name ;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

