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
 * @create 2022/6/7 21:57
 */
public class TemplateAbstractDetail {

    public static void main(String[] args) {
        /*
         * 抽象类-模版设计模式
         *  抽象类体现的就是一种模板模式的设计，抽象类作为多个子类的通用模板，子类在抽象类的基础上进行扩展、改造，但子类总体上会保留抽象类的行为方式
         *  1）当功能内部一部分实现是确定，一部分实现是不确定的。这时可以把不确定的部分暴露出去，让子类去实现。
         *  2）编写一个抽象父类，父类提供了多个子类的通用方法，并把一个或多个方法留给其子类实现，就是一种模板模式.
         */
        //继承 多态运行类型 动态绑定机制 OOP
        new Worker().computedTimes();
    }
}
abstract class Template{

    /**
     * 工作-抽象
     * 每个人完成不同的工作
     */
    public abstract void job();

    /**
     * 计算时间 耗时时间
     */
    public void computedTimes(){
        long start = System.currentTimeMillis();
        job();//动态绑定
        long end = System.currentTimeMillis();
        System.out.println("耗时 "+(end-start));
    }
}
class Worker extends Template{

    /**
     * 工作-抽象
     * 每个人完成不同的工作
     */
    @Override
    public void job() {
        int sum =0;
        for (int i = 0; i < 1000000; i++) {
            sum+=i;
        }
    }
}