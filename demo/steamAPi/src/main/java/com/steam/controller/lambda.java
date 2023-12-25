package com.steam.controller;

import java.util.function.IntBinaryOperator;

/**
 * @author 吴荣洋
 * @program: demo
 * @description:·
 * @date 2023-12-24:12:10
 */

public class lambda {
    public static void lambda(String[] args) {
        //不关心匿名类的类名和方法名，关心方法参数、方法体
        //函数式编程方法和方法体之间用“->"连接
        new Thread(() -> System.out.println("lambok 线程")).start();

        int i = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left+right;
            }
        });

        calculateNum((left,right)->left+right);
        System.out.println(i);
    }

    public static  int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 10;
        return a+b;
    }
}
