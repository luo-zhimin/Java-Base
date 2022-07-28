/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP;

import java.util.Scanner;

public class ObjectDetail {

    public static void main(String[] args) {
        /*
         * 类与对象
         *  对象是引用类型-地址
         * 1) 类是抽象的，概念的，代表一类事物,比如人类,猫类.., 即它是数据类型.
         * 2) 对象是具体的，实际的，代表一个具体事物, 即 是实例.
         * 3) 类是对象的模板，对象是类的一个个体，对应一个实例
         * 属性/成员变量/字段
         *  从概念或叫法上看： 成员变量 = 属性 = field(字段)
         *  属性是类的一个组成部分，一般是基本数据类型,也可是引用类型(对象，数组)
         * 注意事项和细节说明
         * 1) 属性的定义语法同变量，示例：访问修饰符 属性类型 属性名; 这里老师简单的介绍访问修饰符： 控制属性的访问范围 有四种访问修饰符 public, protected, 默认, private ,后面我会详细介绍
         * 2) 属性的定义类型可以为任意类型，包含基本类型或引用类型
         * 3) 属性如果不赋值，有默认值，规则和数组一致。具体说: int 0，short 0, byte 0, long 0, float 0.0,double 0.0，char \u0000， boolean false，String null
         *  Java 内存的结构分析
         * 1) 栈： 一般存放基本数据类型(局部变量)
         * 2) 堆： 存放对象(Cat cat , 数组等)
         * 3) 方法区：常量池(常量，比如字符串)， 类加载信息
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入名字：");
        Cat cat = new Cat();
        cat.name = ("小花");
        cat.age = (12);
        cat.color = ("红色");

        Cat cat1 = new Cat();
        cat1.name = ("小红");
        cat1.age = (100);
        cat1.color = ("花色");
        String name = scanner.next();
        if (name.equals(cat.name) || name.equals(cat1.name)) System.out.println("找到了");
        else System.out.println("没找到...");
        System.out.println("first" + "\t" + cat.name + "\t" + cat.color + "\t" + cat.age);
        System.out.println("second" + "\t" + cat1.name + "\t" + cat1.color + "\t" + cat1.age);
        Person person = new Person();
        //default value
        System.out.println("person" + "\t" + person.name + "\t" + person.age + "\t" + person.score+ "\t" + person.isPass+ "\t" + person.gender);
    }
}

class Cat {
    String name;
    String color;
    int age;
}
class Person{
    int age;
    String name;
    double score;
    boolean isPass;
    char gender;
}
