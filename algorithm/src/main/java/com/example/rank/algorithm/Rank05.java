package com.example.rank.algorithm;


/**
 * @ClassName Rank04  编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 * @Description TODO
 * @Author ts
 * @Date 2021/10/21 14:41
 * @Version 1.0
 */
public class Rank05 {


    public static void main(String[] args) {


        //String[] str= {"a"};
        //String[] str= {"flower","flow","flight"};
        //String[] str= {"dog","racecar","car"};
        //String[] str= {"aaa","aa","aaa"};
        //String[] str= {""};
        String[] str = {"cir","car"};
        String s = longestCommonPrefix(str);

        System.out.println(s);

    }

    public static String longestCommonPrefix(String[] strs) {
        int j =0;

        if (strs.length==1){
            return strs[0].toString();
        }

        for (int i =0;i<strs.length-1;i++){
            int i1 = test1(strs[i], strs[i + 1]);

            if (i1==0){
                return "";
            }

            if (i ==0 ){
                j = i1;
            }else {
                if (i1<j){
                     j = i1;
                }
            }
        }

      return strs[0].substring(0,j);
    }


    public static int test1(String str1, String str2){


        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int j =0;
        if (chars1.length < chars2.length){
            for (int i =0;i<chars1.length;i++){
                if (chars1[i] == chars2[i]){
                    j++;
                    continue;
                }
                break;
            }
        }else {
            for (int i =0;i<chars2.length;i++){
                if (chars1[i] == chars2[i]){
                    j++;
                    continue;
                }
                break;
            }
        }
        return j;
    }


    /*
     1.先找出数组中最长的字符串
     2.
     */
}