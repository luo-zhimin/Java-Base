/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.threeOOP;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : luozhimin
 * @create 2022/6/5 15:29
 */
public class StaticMethods {
    public static void main(String[] args) {
        /*
        * 类方法
        * 类方法也叫静态方法
        *   形式
        *       访问修饰符 static 数据返回类型 方法名(){}
        *       static 访问修饰符 数据返回类型 方法名(){}
        *   使用
        *       类名.类方法名 or 对象名.类方法名 【需要满足修饰符】
        *   细节
        *       1）类方法和普通方法都是随着类的加载而加载，将结构信息存储在方法区：
        *          类方法中无this的参数
        *          普通方法中隐含着this的参数
        *       2）类方法可以通过类名调用，也可以通过对象名调用
        *       3）普通方法和对象有关，需要通过对象名调用，比如对象名.方法名（参数），不能通过类名调用
        *       4）类方法中不允许使用和对象有关的关键字，比如this和super。普通方法（成员方法）可以
        *       5）类方法（静态方法）中只能访问 静态变量 或静态方法 。
        *       6）普通成员方法，既可以访问 非静态成员，也可以访问静态成员。
        *       小结∶静态方法，只能访问静态的成员，非静态的方法，可以访问静态成员和非静态成员（必须遵守访问权限）
        */
        new Student("Tom").payMoney(100);
        new Student("Jerry").payMoney(200);
        Student.showMoney();
    }
}
class Student{

    private String name;

    private static double money = 0;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static protected void say(){
        System.out.println("static 访问修饰符..");
    }

    public void payMoney(double fee){
        money += fee;
    }

    /**
     * 静态方法可以访问静态属性/变量
     */
    public static void showMoney(){
        say();
        System.out.println("总学费:"+money+"元");
    }
}
