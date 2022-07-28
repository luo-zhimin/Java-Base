/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.extend.supper;

public class TSupper {

    public static void main(String[] args) {
        /*
         * supper 与 this
         * supper
         *   super 代表父类的引用，用于访问父类的属性、方法、构造器
         *   访问父类的属性，但不能访问夫类的private属性  supper.属性
         *   访问父类的方法，但不能访问夫类的private方法  supper.方法名
         *   访问父类的构造器，supper(参数列表)，只能放在构造器的第一句，只能出现一句
         * 便利
         *   调用父类的构造器的好处(分工明确，父类的属性由父类初始化，子类的属性由子类初始化)
         *   当子类和父类的成员(属性和方法)重名时，为了访问父类的成员，必须通过supper，如果没有重名，使用supper，this，直接访问是一样的效果！
         *   super 的访问不限于直接父类，如果爷爷类和本类中有同名的成员，也可以使用 super 去访问爷爷类的成员；
         *   如果多个基类(上级类)中都有同名的成员，使用 super 访问遵循就近原则。A->B->C
         */
        B b = new B();
    }
}

class A {
    public int a = 100;
    int b = 200;
    protected int c = 300;
    private int d = 400;

    public A(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        System.out.println("supper 含参构造~");
    }

    public A() {
        System.out.println("supper 的无参构造～");
    }
}
class B extends A{
    public void ok(){
        System.out.println(super.a+"\t"+super.b+"\t"+super.c);
    }

    public B(int a, int b, int c, int d) {
        super(a, b, c, d);
        System.out.println("this 含参构造～");
        ok();
    }

    public B(){
        this(1,1,1,1);
        System.out.println("this 的无参构造～");
    }
}