/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.threeOOP;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : luozhimin
 * @create 2022/6/5 18:55
 */
public class MainDetail {
    public static void main(String[] args) {
        /*
         * 深入理解main方法
         *  在main()方法中，我们可以直接调用main方法所在类的静态方法或静态属性。
         *  但是，不能直接访问该类中的非静态成员，必须创建该类的一个实例对象后，才能通过这个对象去访问类中的非静态成员
         */
        System.out.println(Arrays.toString(args));
    }
}
class Main{
    private static String name ="main";
    private int n1 = 100;

    public void hello(){
        System.out.println("main 的 hello method... "+this.n1);
    }

    public static void show(){
        System.out.println("main static show "+ name);
    }
}
class Test{
    public static void main(String[] args) {
        MainDetail.main(new String[]{"1","2"});
    }
}