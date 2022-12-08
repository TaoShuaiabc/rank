package com.example.rank.lambda;

import java.util.Arrays;

/**
 * @ClassName LambdaTest01
 * @Description 使用lambda表达式创建线程
 * @Author ts
 * @Date 2022/12/8 15:00
 * @Version 1.0
 */
public class LambdaTest02 {


    public static void main(String[] args) {
        Person[] persons = new Person[4];
        persons[0] = new Person("袁隆平", 234);
        persons[1] = new Person("邓稼先", 30);
        persons[2] = new Person("清华大学", 2387);
        persons[3] = new Person("北京大学", 89);
        //将Persons放进Arrays.sort()方法当中，两个对象之间对比，进行升序排序
        Arrays.sort(persons, (one, two) -> one.getAge() - two.getAge());
        //forEach（）循环遍历整个persons数组，并打印
        for(Person p:persons){
            System.out.println(p.toString());
        }
    }




}
