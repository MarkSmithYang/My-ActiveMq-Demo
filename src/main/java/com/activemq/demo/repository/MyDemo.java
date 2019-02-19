package com.activemq.demo.repository;

/**
 * @author biaoyang
 * @Description:
 * @date 2018/8/31 003113:36
 */
//@FunctionalInterface
public interface MyDemo {

    void method();

    default void get(String a) {
        System.err.println("传进来的参数是:" + a);
    }

    public static void name() {
        //怎么那么神奇呢
    }
}
