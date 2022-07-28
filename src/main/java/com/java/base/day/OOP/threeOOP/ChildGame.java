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
 * @create 2022/6/5 13:47
 */
public class ChildGame {
    public static void main(String[] args) {
        /*
         * 类变量/静态变量
         * 定义：
         *  类变量也叫静态变量/静态属性，是该类所有对象共享的变量，任何一个该类的对象去访问时，取到的值都是相同的值，
         * 同样任何一个该类的对象去修改它时，修改的都是同一个变量
         * static变量是对象共享 不管static在哪里  jdk1.8之后在堆里面 class实例的尾部 1.8之前在方法区
         *  static变量是同一个类的所有对象共享
         *  static类变量，在类加载的时候就生成了
         * 定义语法：
         *  访问修饰符 static 数据类型 变量名
         *  static 访问修饰符 数据类型 变量名
         * 访问：
         *  类名.变量名
         *  对象名.类变量名-【静态变量的访问修饰符的访问权限和范围 和 普通属性一样】
         * 细节
         * 类变量与实例变量（普通属性）区别：
         *    类变量是该类的所有对象共享的，而实例变量是每个对象独享的。
         *  加上static称为类变量或静态变量，否则称为实例变量/普通变量/非静态变量
         *  类变量可以通过 类名.类变量名 或者 对象名.类变量名 来访问，但java设计者推荐我们使用 类名.类变量名方式访问。【前提是 满足访问修饰符的访问权限和范围】
         *  实例变量不能通过类名.类变量名 方式访问。
         *  类变量是在类加载时就初始化了，也就是说，即使你没有创建对象，只要类加载了就可以使用类变量了
         *  类变量的生命周期是随类的加载开始，随着类消亡而销毁。
         */
        //共享
        new Child("王昭君").join();
        new Child("西施").join();
        new Child("貂蝉").join();
        System.out.println("Child count="+Child.count);
    }
}
class Child{
    /**
     * 普通属性/普通成员变量/非静态属性/非静态成员变量/实例变量
     */
    private String name;
    /**
     * 类变量的访问 必须遵守 相关的访问权限
     */
    protected static int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Child(String name) {
        this.name = name;
    }

    public Child() {}

    protected void join(){
        count++;
        System.out.println(this.name+"加入了游戏"+" 我是第"+count);
    }
}