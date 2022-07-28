/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP;

public class ThisDetail {
    public static void main(String[] args) {
        /*
         * this
         *  简单来说哪个对象调用，this就是代表哪个对象
         * 注意事项和细节
         *  1) this 关键字可以用来访问本类的属性、方法、构造器
         *  2) this 用于区分当前类的属性和局部变量
         *  3) 访问成员方法的语法：this.方法名(参数列表);
         *  4) 访问构造器语法：this(参数列表); 注意只能在构造器中使用(即只能在构造器中访问另外一个构造器, 必须放在第一条语句)
         *  5) this 不能在类定义的外部使用，只能在类定义的方法中使用。
         */
        Dog dog = new Dog("dog", 10);
        System.out.println(dog.name);
        System.out.println(dog.age);
        System.out.println(dog.compareTo(dog));
        System.out.println("Object="+dog.hashCode());
    }
}

class Dog {
    String name;
    int age;
    Dog(String name,int age){
        //name = name; 就近原则
        this.name = name;
        this.age = age;
        System.out.println("this="+this.hashCode());
    }

    public boolean compareTo(Dog dog){
        return this.name.equals(dog.name) && this.age == dog.age;
    }
}