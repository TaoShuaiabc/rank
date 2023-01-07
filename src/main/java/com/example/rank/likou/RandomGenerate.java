package com.example.rank.likou;

import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName RandomGenerate
 * @Description  生成5个不同的范围在[0-101]随机数
 * @Author ts
 * @Date 2023/1/5 9:49
 * @Version 1.0
 */
public class RandomGenerate {


    public static void main(String[] args) {
        RandomGenerate randomGenerate = new RandomGenerate();
        System.out.println(Arrays.toString(randomGenerate.generateRandom()));
    }

    public int[] generateRandom(){

        int[] arr = new int[5];
        for (int i =0;i<arr.length;i++){
            arr[i] = -1;
        }
        //生成随机数类
        Random random = new Random();
        int index = 0;
        while (index<5){
            int number = random.nextInt(100);
            if (!randomCheck(arr,number)){
                arr[index++] = number;
            }
        }
        return arr;
    }

    /**
     * 校验数组是否已经包含该随机数
     *@author ts
     *@param arr 数组
     *@param number 随机数
     *@return  true/false
     */
    public static boolean randomCheck(int[] arr,int number){


        ////给数组排序  这种方法有问题，排序会导致出问题
        //Arrays.sort(arr);

        ////使用二分查找
        //return  Arrays.binarySearch(arr, number) > -1;

        for (int i=0;i<arr.length;i++){
            if (arr[i] == number){
                return true;
            }
        }
        return false;

    }

}
