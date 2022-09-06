package com.example;


/*
*
*  回文数
* 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。

* **/
public class Rank03 {


    public static void main(String[] args) {
        Integer i = 1221;
        System.out.println(rank(i));
    }

    public static Boolean rank(Integer x){

        String strinte = String.valueOf(x);

        //1.将string转成char数组
        char[] chars = strinte.toCharArray();
        System.out.println(chars);
        //2.倒转挨个取出并拼接
        String test = null;
        for (int i  =chars.length-1;i>=0;i--){
            if (i == chars.length-1){
                test = String.valueOf(chars[i]);
            }else {
                test+=chars[i];
            }

        }
        //3.与原数据比较
        System.out.println(test);
        if (strinte.equals(test)){
            return true;
        }
        return false;
    }

}
