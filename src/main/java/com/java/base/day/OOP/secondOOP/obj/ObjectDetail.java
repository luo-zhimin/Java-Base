/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.obj;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : luoZhiMin
 * @create 2022/6/2 23:35
 */
public class ObjectDetail {

    public static void main(String[] args) {
        /*
         * Object详解
         * ==和equals的对比
         *   ==是一个比较运算符
         *   1.==:既可以判断基本类型，也可以判断引用类型
         *   2.==:如果是基本类型则比较的是值是否相等
         *   3.==:如果判断是引用类型，则判断得是地址是否相等，即判定是否是同一个对象
         *   4.equals：是Object类的方法，只可以判断引用类型
         *   5.默认判断是地址是否相等，子类中往往会重写这个方法，用于判断内容是否相等，比如Integer，String
         *
         * hasCode
         * 1) 提高具有哈希结构的容器的效率！
         * 2) 两个引用，如果指向的是同一个对象，则哈希值肯定是一样的！
         * 3) 两个引用，如果指向的是不同对象，则哈希值是不一样的
         * 4) 哈希值主要根据地址号来的！， 不能完全将哈希值等价于地址。
         *
         * toString
         * 默认返回：全类名+@+哈希值的十六进制，【查看 Object 的 toString 方法】
         * 子类往往重写 toString 方法，用于返回对象的属性信息
         * 重写 toString 方法，打印对象或拼接对象时，都会自动调用该对象的 toString 形式
         * 当直接输出一个对象时，toString 方法会被默认的调用
         *
         * finalize
         * 1) 当对象被回收时，系统自动调用该对象的 finalize 方法。子类可以重写该方法，做一些释放资源的操作【演示】
         * 2) 什么时候被回收：当某个对象没有任何引用时，则 jvm 就认为这个对象是一个垃圾对象，就会使用垃圾回收机制来 销毁该对象，在销毁该对象前，会先调用 finalize 方法。
         * 3) 垃圾回收机制的调用，是由系统来决定(即有自己的 GC 算法), 也可以通过 System.gc() 主动触发垃圾回收机制
         */

        Integer a = 127;
        Integer ib = 127;
        Integer c = 200;
        double b = 258.0;
        //-128~127 integer
        System.out.println("integer=" + (a == ib));
        System.out.println("ib=" + ib.intValue() + "\t" + c.intValue());
        ObjB objB = new ObjB();
        ObjA objA = objB;
        System.out.println(objA == objB);
        ObjB o1 = new ObjB("Jack",10,'男');
        ObjB o2 = new ObjB("Jack",11,'女');
        //no to equals go to object false
        System.out.println(o1.equals(o2));
        char char1 = 'A';
        char char2 = 'a';
        System.out.println((int) char1+"\t"+(int) char2);
        System.out.println(o1.hashCode());
        System.out.println("toHexString=0x"+Integer.toHexString(o1.hashCode()));
        System.out.println(o1);
        System.out.println("==finalize==");
        Car car = new Car("奔驰");
        System.out.println("before car "+car);
        //没有引用 垃圾回收器回收对象 释放堆的空间
        car=null;
        System.gc();//主动调用gc
        System.out.println("after car.. break..");
    }
}
class Car{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
//        super.finalize();
        System.out.println("destroy car "+this.name);
    }

    @Override
    public boolean equals(Object object){
        if (this==object){
            return true;
        }
        if (object instanceof  Car){
            Car car = (Car) object;
            return car.name.equals(this.name);
        }
        return false;
    }
}
class ObjA {
}

class ObjB extends ObjA {

    private String name;
    private int age;

    public ObjB() {}

    public ObjB(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    private char sex;

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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    //重写Object equals
    @Override
    public boolean equals(Object obj){
        //判断比较的对象是否同一个
        if (this == obj){
            return true;
        }
        //类型判断
        if (obj instanceof ObjB){
            //向下转型 因为我需要得到obj的各个属性
            ObjB object = (ObjB) obj;
            return this.name.equals(object.name) && this.age == object.age && this.sex == object.sex;
        }
        //如果不是一个对象 false
        return false;
    }

    @Override
    public String toString() {
        return "ObjB{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
//        super.finalize();
        System.out.println("ObjB gc...");
    }
}