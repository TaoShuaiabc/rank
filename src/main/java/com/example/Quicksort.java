package com.example;

import java.util.Arrays;

public class Quicksort {

    public static int partition(int [] arr,int low,int high){
        //指定左指针i和右指针j
        int i = low;
        int j = high;

        //将第一个值作为基准值
        int x = arr[low];

        //使用循环实现分区操作
        while (i<j){


            //1.从左向右移动j，找到第一个小于基准值的值
            while (arr[j] >= x && i<j){
                j--;
            }
            //2.找到右侧第一个小于基准值的值加入到左边的(坑)的位置，左指针向中间移动一个位置i++
            if (i<j){
                arr[i] = arr[j];
                i++;
            }

            //3.从右向左移动i，找到第一个大于基准值的值
            while (arr[i] < x && i<j){
                i++;
            }

            //4.将左侧找到的大于等于基准值的值加入到右边的坑中，右指针向中间移动一个位置
            if (i<j){
                arr[j] = arr[i];
                j--;
            }
        }
        //使用基准值填坑
        arr[i] = x;

        return i;
    }


    public static void quickSort(int [] arr,int low,int high){
        if (low<high){
            //分区操作，将一个数组分成两个区，返回分区界限索引
            int index = partition(arr, low, high);

            //对左分区进行快排
            quickSort(arr,low,index-1);

            //对右分区进行快排
            quickSort(arr,index+1,high);
        }
    }

    public static void quickSort(int [] arr){
        int low = 0;
        int high= arr.length-1;
        quickSort(arr,low,high);

    }

    public static void main(String[] args) {
        //给出无序数组
        int arr[] = {72,6,57,88,60,42,83,73,48,85};

        System.out.println(Arrays.toString(arr));

        quickSort(arr);

        System.out.println(Arrays.toString(arr));
    }



}
