/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP;

public class ConstructorDetail {

    public static void main(String[] args) {
        /*
         * 构造器/构造方法
         *   构造方法又叫构造器(constructor)，是类的一种特殊的方法，它的主要作用是完成对新对象的初始化。它有几个特点：
         *       1) 方法名和类名相同
         *       2) 没有返回值
         *       3) 在创建对象时，系统会自动的调用该类的构造器完成对象的初始化。
         *   注意事项和细节
         *      一个类可以定义多个不同的构造器，即构造器重载
         *      构造器是完成对象的初始化，并不是创建对象
         *      如果没有定义构造器，系统会默认给一个无参构造器(默认构造器)
         *      一旦定义了自己的构造器，默认构造器就会被覆盖，就不能在使用无参构造了，除非重写(定义) xx(){}
         */

        ConstructorPerson person = new ConstructorPerson("Jack",10);
        ConstructorPerson person2 = new ConstructorPerson("Jack");
    }
}

class ConstructorPerson {
    String name;
    int age;

    public ConstructorPerson(String personName,int personAge){
        System.out.println("有参构造调用～完成属性的初始化");
        name = personName;
        age = personAge;
        say();
    }

    public ConstructorPerson(String personName){
        System.out.println("有参构造调用～完成属性的初始化-name");
        name = personName;
        say();
    }

    public void say(){
        System.out.println(name+"-"+age);
    }
}
