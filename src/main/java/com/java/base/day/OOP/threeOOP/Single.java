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
 * @create 2022/6/5 23:55
 */
public class Single {
    public static void main(String[] args) {
        /*
        * 单例设计模式
        * 单例（单个的实例）
        *   1.所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对某个类只能存在一个对象实例，并且该类只提供一个取得其对象实例的方法
        *   2.单例模式有两种方式∶
        *       1）饿汉式 类加载创建 可能造成资源浪费 所以通常是重量级的对象
        *       2）懒汉式 只有用户使用才会去 调用判断 如果 没有就去创建 只会创建一个对象 同一个对象
        * 实现
        *   1）构造器私有化 =》防止直接 new
        *   2）类的内部创建对象
        *   3）向外暴露一个静态的公共方法
        * 小结：
        *   1.二者最主要的区别在于创建对象的时机不同∶饿汉式是在类加载就创建了对象实例，而懒汉式是在使用时才创建。
        *   2.饿汉式不存在线程安全问题，懒汉式存在线程安全问题
        *   3.饿汉式存在浪费资源的可能。因为如果程序员一个对象实例都没有使用，那么饿汉式创建的对象就浪费了，懒汉式是使用时才创建，就不存在这个问题。
        *   4.在我们javaSE标准类中，java.lang.Runtime就是经典的单例模式。
        */
        //饿汉式 -- 类加载创建
//        System.out.println(GildFriend.getGildFriend());
        System.out.println(GildFriend.getFriend());
        System.out.println(GildFriend.getFriend());

        AA aa  = new BB("BB");
        BB bb = (BB) aa;
        System.out.println(bb.getName());
    }
}
class GildFriend{
    private String name;
    private int age;

    protected static int n = 1;

    /**
     * 2.类内部私有化对象 饿汉式
     */
//    private static GildFriend gildFriend = new GildFriend("小红",22);

    //init
    private static GildFriend gildFriend;

    /**
     * 懒汉式
     * @return girlFriend
     */
    public static GildFriend getFriend(){
        //如果没有就去创建
        if (gildFriend==null){
            gildFriend = new GildFriend("小米", 5);
        }
        return gildFriend;
    }

    public static GildFriend getGildFriend(){
        return gildFriend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){return true;}
        if (obj instanceof GildFriend){
            GildFriend gildFired = (GildFriend) obj;
            return this.age == gildFired.age && this.name.equals(gildFired.name);
        }
        return false;
    }

    /**
     * 1.构造器私有化
     */
    private GildFriend() {}

    private GildFriend(String name, int age) {
        System.out.println("构造器加载");
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "GildFriend{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
class AA{
    private String name;

    AA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class BB extends AA{

    BB(String name) {
        super(name);
    }

    public void showB(){
        System.out.println("I am is a ShowB methods");
    }
}