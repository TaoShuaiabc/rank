package com.example.rank.algorithm;


import java.util.*;

/**
 * @ClassName Rank04  罗马数字转整数
 * @Description TODO
 * @Author ts
 * @Date 2021/10/21 14:41
 * @Version 1.0
 */
public class Rank04 {


    public static void main(String[] args) {


        String test = "III";

        System.out.println(romanToInt(test));

    }


    public static int romanToInt(String s) {

        Map<String, Integer> map = new HashMap<String, Integer>(13);
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 != chars.length) {
                if (map.get(String.valueOf(chars[i]) + chars[i + 1]) != null) {
                    sum += map.get(String.valueOf(chars[i]) + chars[i + 1]);
                    i++;
                } else {
                    sum += map.get(String.valueOf(chars[i]));
                }
            }else {
                sum += map.get(String.valueOf(chars[i]));
            }
        }
        return sum;

}


}