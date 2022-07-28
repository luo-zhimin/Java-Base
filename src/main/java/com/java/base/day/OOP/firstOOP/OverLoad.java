/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP;

import java.util.Arrays;

public class OverLoad {
    public static void main(String[] args) {
        /*
         * 方法重载
         *   java 中允许同一个类中，多个同名方法的存在，但要求 形参列表不一致！
         * 好处
         *   1) 减轻了起名的麻烦
         *   2) 减轻了记名的麻烦
         * 注意事项和细节
         *  方法名：必须相同
         *  形参列表：必须不同（形参类型或者个数或顺序不同，至少有一样不同，参数名无要求）
         *  返回类型：无要求
         * 可变参数
         *  java 允许将同一个类中多个同名同功能但参数个数不同的方法，封装成一个方法(int... xx) es6语法 是个array
         * 注意事项和细节
         *  可变参数的实参可以多个或者0个
         *  可变参数的实参可以为数组
         *  可变参数的本质就是数组
         *  可变参数可以和普通类型的参数一起放在形参列表，但必须保证可变参数在最后
         *  一个形参列表中只能出现一个可变参数
         */

        MyCalculator calculator = new MyCalculator();
        System.out.println(calculator.calculate(1, 1));
        System.out.println(calculator.calculate(1, 1.1));
        System.out.println(calculator.calculate(1.1, 1));
        System.out.println(calculator.calculate(1, 1, 1));
        System.out.println("===");
        calculator.ride("1");
        calculator.ride(2);
        calculator.ride(3,4);
        System.out.println("===");
        System.out.println(calculator.sum(1, 2, 3));
    }
}

class MyCalculator {
    //int
    public int calculate(int a, int b) {
        System.out.println("int->" + (a + b));
        return (a + b);
    }

    //int double
    public double calculate(int a, double b) {
        System.out.println("int/double->" + (a + b));
        return (a + b);
    }

    //double int
    public double calculate(double a, int b) {
        System.out.println("double/int->" + (a + b));
        return (a + b);
    }

    //int *3
    public double calculate(int a, int b, int c) {
        System.out.println("int*3->" + (a + b + c));
        return (a + b + c);
    }

    public void ride(int a){
        System.out.println(Math.pow(a, 2));
    }

    public void ride(int a,int b){
        System.out.println((Math.pow(a, 2)+Math.pow(b,2)));
    }

    public void ride(String a){
        System.out.println(Math.pow(Double.parseDouble(a), 2));
    }

    public int sum(int ... params){
        System.out.println("length"+params.length);
        if (params.length>0){
            return Arrays.stream(params).sum();
        }
        return 0;
    }

    public int sum(int[] ... params){
        System.out.println("length"+params.length);
        System.out.println(Arrays.toString(params));
        return 0;
    }
    //可变参数可以和普通类型的参数一起放在形参列表，但必须保证可变参数在最后
    public void test(String s,int ... x){
        System.out.println(s+"/"+ Arrays.toString(x));
    }
}