package com.steam.controller;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:
 * @date 2023-12-24:12:10
 */

public class lambok_01 {
    public static void main(String[] args) {
        //不关心匿名类的类名和方法名，关心方法参数、方法体
        //函数式编程方法和方法体之间用“->"连接
        new Thread(() -> System.out.println("lambok 线程")).start();
    }
}
