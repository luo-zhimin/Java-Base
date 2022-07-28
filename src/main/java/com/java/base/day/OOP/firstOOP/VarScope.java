/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP;

/**
 * @author luozhimin
 */
public class VarScope {

    public static void main(String[] args) {
        /*
         * 作用域
         *   在java编程中，主要是变量就是属性(成员变量)和局部变量
         *   我们说的局部变量一般是指在成员方法中定义的变量
         *   java中作用域的分类
         *       全局变量：也就是属性，作用域为整个类主体
         *       局部变量：也就是除了属性之外的其他变量，作用域为定义它的代码块中！
         *   全局变量可以不赋值，直接使用，因为有默认值，局部变量必须赋值后，才能使用，因为没有默认值
         *   注意事项和细节：
         *      属性和变量名可以重名，访问时遵循就近原则
         *      在同一个作用域中，两个局部变量，不可以重名
         *      属性生命周期较长，伴随着对象的创建而创建，伴随着对象的销毁而销毁。 局部变量，生命周期较短，
         *          伴随着它的代码块的执行而创建， 伴随着代码块的结束而销毁。即在一次方法调用过程中
         *      作用范围不同
         *          全局变量/属性：可以被本类使用，或者其他类使用(通过对象进行调用)
         *          局部变量：只能在本类中对应的方法中使用
         *      修饰符不同
         *         全局变量/属性可以加修饰符
         *         局部变量不可以加修饰符
         */
        Scope scope = new Scope();
        scope.speck();
        System.out.println(scope.age+"---"+scope.start);
    }
}

class Scope {

    //全局
    byte age = 10;
    String start;
    int a = 1;

    public void speck() {
        //局部变量--就近原则
        int a = 10;
        String name = "jack";
        System.out.println(name + "-" + a+"\t"+age);
    }
}
