package com.example.rank.integer;

/**
 * @ClassName IntegerTest01
 * @Description 八种包装类
 * @Author ts
 * @Date 2022/12/28 14:38
 * @Version 1.0
 */
public class IntegerTest01 {

    /***

     byte            java.lang.Byte(父类Number)
     short           java.lang.Short(父类Number)
     int             java.lang.Integer(父类Number)
     long            java.lang.Long(父类Number)
     float           java.lang.Float(父类Number)
     double          java.lang.Double(父类Number)
     boolean         java.lang.Boolean(父类Object)
     char            java.lang.Character(父类Object)
     */

    public static void main(String[] args) {


        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;

        String s ="陶帅";

        System.out.println(s.hashCode());

        /**
         * 为什么会出现以下情况？
         *   因为jvm中保存了一个-128到127的整数型常量池。所以由于==比较的是内存地址，127是直接从常量池中取的，
         *   内存地址相同，所以是true，但是128是存在两个不同的对象中，对象的内存地址不同，所以是false
         */
        System.out.println(a==b); //true
        System.out.println(c==d); //false
    }
}
