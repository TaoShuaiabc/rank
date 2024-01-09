package com.example.rank.algorithm;

/**
 * @ClassName Rank02
 * @Description TODO 寻找数组中心索引  --->一次性通过，但效率不咋地 /捂脸
 * @Author TS
 * @Date 2021/1/28 14:24
 * @Version 1.0
 */
public class Rank02 {


    public static void main(String[] args) {

       // int [] nums = {1,7,3,6,5,6};
        int [] nums = {1,2,3,4,5};
        int i = centreIndex(nums);
        System.out.println(i);
    }


    public static int centreIndex(int[] nums){
        if (!(nums.length <= 10000 && nums.length >= 0)){
            return -1;
        }
        if (nums.length == 0){
            return -1;
        }
        if (nums.length == 1){
            return 0;
        }
        for (int i =0;i<nums.length;i++){

            int left = left(nums, i);
            int right = right(nums, i);
            if (left == right){
                return i;
            }
        }
        return -1;
    }


    public static int left(int [] nums,int index){
        int sum =0;
        for (int i =0;i<index;i++){
            sum+=nums[i];
        }
        return sum;

    }


    public static int right(int [] nums,int index){
        int sum =0;
        for (int i =index+1;i<nums.length;i++){
            sum+=nums[i];
        }
        return sum;
    }


    /* 标准答案


 class Solution {
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}

    * */
}
