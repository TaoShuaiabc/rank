package com.example.rank.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName StreamTest01
 * @Description
 * @Author ts
 * @Date 2022/12/14 10:00
 * @Version 1.0
 */
public class StreamTest01 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        //匹配第一个
        Optional<Integer> first = list.stream().filter(x -> x > 7).findFirst();
        //System.out.println(first.get());
        //匹配任意一个,适合并行流
        Optional<Integer> any = list.parallelStream().filter(x -> x > 6).findAny();
        //System.out.println(any.get());
        //是否存在大于6的
        boolean b = list.stream().anyMatch(x -> x > 6);
        System.out.println(b);

    }
}
