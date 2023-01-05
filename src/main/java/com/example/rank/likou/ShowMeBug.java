package com.example.rank.likou;

import java.util.Arrays;

/**
 * @ClassName ShowMeBug
 * @Description  输入一个二维数组[{1,2,3},{4,5,6},{7,8,9}],输出一个二维数组[[1, 4, 7], [2, 5, 8], [3, 6, 9]]
 * @Author ts
 * @Date 2022/12/29 17:35
 * @Version 1.0
 */
public class ShowMeBug {

    /**
     *
     max[0][0] max[1][0]
     max[0][1] max[1][1]
     max[0][2] max[1][2]
     */

    public static void main(String[] args) {

        int[][] max = {{1,2,3},{4,5,6},{7,8,9}};

        int[][] ins  = new int[max[0].length][max.length];

        for (int i=0;i<max.length;i++){
            for (int j=0;j<max[i].length;j++){
                ins[j][i] = max[i][j];
            }
        }

        System.out.println(Arrays.deepToString(ins));
    }
}
