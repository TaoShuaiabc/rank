package com.example.rank.enumTest;

/**
 * @author ASUS
 */

public enum TestEnum {


    SUCCEED("成功","200"),
    FAIL("失败","500");

    String name;

    String code;

    TestEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }



}
