package com.example;

import org.omg.CORBA.PUBLIC_MEMBER;
import sun.dc.pr.PRError;

import java.util.Arrays;

public class Rank {

    public static void main(String[] args) {

    }

    //冒泡排序
    public static int[] mpRank(int [] rankM){


        int third = 0;

        for (int j = 1;j<rankM.length;j++){

            for (int i =rankM.length-1;i>-1;i--){

                if (i == j-1){
                    break;
                }

                if (rankM[i] < rankM[i-1]){
                    third = rankM[i-1];
                    rankM[i-1]  = rankM[i];
                    rankM[i] = third;
                }

            }
        }


        return rankM;
    }

    //交换排序
    public static int[] jhRank(int [] rankM){

        int third = 0;

        for (int i =0;i<rankM.length;i++){

            if (i == rankM.length -1){
                break;
            }

            for (int j =i+1;j<rankM.length;j++){
                if (rankM[i] > rankM [j]){
                    third = rankM[j];
                    rankM[j] = rankM[i];
                    rankM[i] = third;
                }
            }

        }

        return rankM;
    }

    //选择排序(未完成)

    //插入排序(不是很规范)
    public static int[] crRank(int itpNum){

        //原数据
        int [] intrinsic = {22,33,55};

        int [] newArray = new int[intrinsic.length+1];

        for (int i =0;i<intrinsic.length;i++){

            if (itpNum <= intrinsic[0]){
                for (int j =0;j<intrinsic.length;j++){
                    newArray[0] = itpNum;
                    newArray[j+1] = intrinsic[j];
                }
                return newArray;
            }
            if (itpNum >= intrinsic[intrinsic.length-1]){
                newArray[newArray.length-1] = itpNum;
                for (int j =0;j<intrinsic.length;j++){
                    newArray[j] = intrinsic[j];
                }
                return newArray;
            }
            if (itpNum > intrinsic[i] && itpNum < intrinsic[i+1]){
                for (int j =0;j<newArray.length;j++){
                    if (j == i+1) {
                        newArray[i+1] = itpNum;
                    }
                    if (j < i+1){
                        newArray[j] = intrinsic[j];
                    }
                    if (j > i+1){
                        newArray[j] = intrinsic[j-1];
                    }
                }
                return newArray;
            }
        }
        return null;

    }

    //归并排序(未完成)
    public static int[] gbRank(int [] ar1,int [] ar2){


       return null;


    }



}
