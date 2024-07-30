package com.design.threaddemo.test4;


import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoiTest {


    private static final Logger log = LoggerFactory.getLogger(JoiTest.class);

    public static void main(String[] args) {
        A a = new A();

        log.info(Integer.toHexString(a.hashCode()));

        String s = new String();
        log.info(ClassLayout.parseInstance(a).toPrintable());
        log.info(ClassLayout.parseInstance(s).toPrintable());
    }
}
