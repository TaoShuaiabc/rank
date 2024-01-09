package com.example.rank.equals;

public class EqualsTest01 {


    public static void main(String[] args) {




        Student student1 = new Student();
        student1.setName("张三");
        Student student2 = new Student();
        student2.setName("张三");



        System.out.println(student1 == student2);
        System.out.println(student1.equals(student2));
    }
}
