package com.example;
/*
*
* 力扣整数反转--已通过
*
* */
public class Rank01 {

    public static void main(String[] args) {


        int i = -2147483641;

        int reverse = reverse(i);

        System.out.println(reverse);

    }



    public static int reverse(int r){

        int x = -2147483648;
        int y = 2147483647;

        if (r >= 0){
            long reversal = reversal(r);
            if (reversal >= x && reversal <= y){
                return (int)reversal;
            }else {
                return 0;
            }

        }else {
            int abs = Math.abs(r);
            long reverse = reversal(abs);
            long re = reverse * -1;
            if (re >= x && re <= y){
                return (int)re;
            }else {
                return  0;
            }
        }
    }

    public static long reversal(int x) {

        if (x == 0)
            return x;
        int num = retNum(x);
        int w = retW(num);
        int[] ints = new int[num];
        for (int i=0;i<num;i++){
            ints[i] = x%10;
            x = (int)Math.floor(x/10);

        }

        long array = array(ints);

        return array;
    }

    public static long array(int [] array){
        long num =0;
        long w = retW(array.length);
        for (int i =0;i<array.length;i++){

             num = array[i]*w + num;
             w = w/10;
        }
        return num;
    }

    public static int retNum(int num){
        return String.valueOf(num).length();
    }

    public static int retW(int w){
        int num =1;
        for (int i=1;i<w;i++){
            num=num*10;
        }
        return num;
    }
}
