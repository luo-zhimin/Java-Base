/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.modifer;

public class Modifier {

    public static void main(String[] args) {
        /*
        * 修饰符
        *   java 提供四种访问控制修饰符号，用于控制方法和属性(成员变量)的访问权限（范围）:
        * 1) 公开级别:用 public 修饰,对外公开
        * 2) 受保护级别:用 protected 修饰,对子类和同一个包中的类公开
        * 3) 默认级别:没有修饰符号,向同一个包的类公开.
        * 4) 私有级别:用 private 修饰,只有类本身可以访问,不对外公开.
        */
        M m = new M();
        m.m1();
        A a = new A();
        a.sayM();
    }
}
