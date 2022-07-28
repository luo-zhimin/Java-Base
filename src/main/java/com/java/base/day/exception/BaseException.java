/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.exception;

import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/11 22:50
 */
public class BaseException {

    public static void main(String[] args) {
        /*
         * 异常
         * 基本概念：
         *  Java语言中，将程序执行中发生的不正常情况称为"异常"。（开发过程中的语法错误和逻辑错误不是异常）
         * 执行过程中所发生的异常事件可分为两大类：
         *  1)：Error（错误）∶Java虚拟机无法解决的严重问题。如∶ JM系统内部错误、资源耗尽等严重情况。比如∶StackOverflowError【栈溢出】和OOM（out of memory），Error 是严重错误，程序会崩溃。
         *  2)：Exception∶其它因编程错误或偶然的外在因素导致的一般性问题，可以使用针对性的代码进行处理。
         * 例如空指针访问，试图读取不存在的文件，网络连接中断等等，Exception 分为两大类∶运行时异常【程序运行时，发生的异常】和编译时异常【编程时，编译器检查出的异常】
         * 1.异常分为两大类，运行时异常和编译时异常.
         * 2.运行时异常， 编译器检查不出来。一般是指编程时的逻辑错误， 是程序员应该避免其出现的异常。java.lang.RuntimeException类及它的子类都是运行时异常
         * 3.对于运行时异常，可以不作处理，因为这类异常很普遍，若全处理可能会对程序的可读性和运行效率产生影响
         * 4.编译时异常，是编译器要求必须处置的异常。
         *
         * 运行时异常 RunTimeException
         *  常见的运行时异常
         *      1) NullPointerException 空指针异常
         *          当应用程序试图在需要对象的地方使用 null 时，抛出该异常
         *      2) ArithmeticException 数学运算异常
         *          当出现异常的运算条件时，抛出此异常。例如，一个整数“除以零”时，抛出此类的一个实例
         *      3) ArrayIndexOutOfBoundsException 数组下标越界异常
         *          用非法索引访问数组时抛出的异常。如果索引为负或大于等于数组大小，则该索引为非法索引
         *      4) ClassCastException 类型转换异常
         *          当试图将对象强制转换为不是实例的子类时，抛出该异常
         *      5) NumberFormatException 数字格式不正确异常
         *          当应用程序试图将字符串转换成一种数值类型，但该字符串不能转换为适当格式时，抛出该异常 => 使用异常我们 可以确保输入是满足条件数字
         *
         * 编译异常：
         *  编译异常是指在编译期间，就必须处理的异常，否则代码不能通过编译
         * 常见的编译异常：
         *  SQLException//操作数据库时，查询表可能发生异常IOException //操作文件时，发生的异常
         *  FileNotFoundException //当操作一个不存在的文件时，发生异常
         *  ClassNotFoundException //加载类，而该类不存在时，异常
         *  EOFException // 操作文件，到文件未尾，发生异常
         *  illegalArgumentException //参数异常
         */
        //调用null对象 NullPointerException
        String name = null;
        String number = "ss";
        int num1 = 10;
        String[] strings ={"1"};
        //ArithmeticException by zero
        int num2 = 1;
        int result = 0;
        try {
            result = num1 / num2;
            System.out.println(name.trim());
            //ArrayIndexOutOfBoundsException 非法索引 max array.length
            System.out.println(strings[1]);
            //NumberFormatException string to number but string need have number
            System.out.println(Integer.valueOf(number));
            A a = new B();//向上转型 编译是A 运行是B ==B
            B b = (B) a;//向下转型
            C c = (C) a;//ClassCastException
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println(result);
        }

        //编译异常
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/luozhimin/Desktop/File/log");
            fileInputStream.read();
        }catch (Exception exception){
            System.out.println(exception);
        }
    }
}
class A{}
class B extends A{}
class C extends A{}
