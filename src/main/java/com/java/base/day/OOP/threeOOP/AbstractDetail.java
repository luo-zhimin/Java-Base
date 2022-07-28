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
 * @create 2022/6/6 22:51
 */
public class AbstractDetail {
    public static void main(String[] args) {
        /*
         * 抽象类
         *   当父类的某些方法，需要声明，但是又不确定如何实现时，可以将其声明为抽象方法，那么这个类就是抽象类
         *      抽象方法就是没有实现的方法
         *      所谓没有实现就是指，没有方法体
         *      当一个类中存在抽象方法时，需要将该类声明为abstract类
         *      一般来说，抽象类会被继承，有其子类来实现抽象方法
         *  当父类的一些方法不能确定时，可以用abstract关键字来修饰该方法，这个方法就是抽象方法，用abstract 来修饰该类就是抽象
         * 介绍：
         *    1）用abstract 关键字来修饰一个类时，这个类就叫抽象类
         *          访问修饰符 abstract 类名{}
         *   2）用abstract 关键字来修饰一个方法时，这个方法就是抽象方法
         *          访问修饰符 abstract 返回类型 方法名（参数列表）;//没有方法体
         *   3）抽象类的价值更多作用是在于设计，是设计者设计好后，让子类继承并实现抽象类
         *   4）抽象类在框架和设计模式使用较多
         * 细节：
         *  1）抽象类不能被实例化
         *  2）抽象类不一定要包含abstract方法。也就是说，抽象类可以没有abstract方法
         *  3) 一旦类包含了abstract方法，则这个类必须声明为abstract
         *  4）abstract 只能修饰类和方法，不能修饰属性和其它的
         *  5）抽象类可以有任意成员【抽象类本质还是类】，比如∶非抽象方法、构造器、静态属性等等
         *  6）抽象方法不能有主体，即不能实现
         *  7）如果一个类继承了抽象类，则它必须实现抽象类的所有抽象方法，除非它自己也声明为abstract类。
         *  8）抽象方法不能使用private、final 和 static来修饰，因为这些关键字都是和重写相违背的。
         */

        new Cat().eat();
        System.out.println(new Bone(1, "大黄", 100, 100).work());
    }
}

/**
 * 1.abstract 不可以被实例化
 * 访问修饰符 abstract 类名{} 抽象类
 */
abstract class Animal{
    private String name;
    private int age;

    public Animal() {}
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
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

//    protected void eat(){
//        System.out.println("Animal eat...");
//    }

    /**
     * animal eat but don know eat anything
     * 访问修饰符 abstract 返回类型 方法名（参数列表）  抽象方法  不可以有方法体
     * 一旦类包含了abstract方法，则这个类必须声明为abstract
     */
    protected abstract void eat();

    public static String cry(){return "animal static string";}
}
class Cat extends Animal{

    /**
     * animal eat but don know eat anything
     */
    @Override
    protected void eat() {
        System.out.println("cat abstract eat");
    }

}

/**
 * abstract [ˈæbstrækt]
 * 抽象类不一定要包含abstract方法。也就是说，抽象类可以没有abstract方法
 * 抽象类可以有任意成员
 * 抽象类不能被实例化
 */
abstract class Dog{

    final static int NUMBER = 1;

    private int id;
    private String name;
    private double salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Dog(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void show(){
        System.out.println("my name is Dog(){}");
    }

    /**
     * who is work
     * @return name working
     */
     public abstract String work();
}
class Bone extends Dog{

    private double prise;

    public double getPrise() {
        return prise;
    }

    public Bone(int id, String name, double salary, double prise) {
        super(id, name, salary);
        this.prise = prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    /**
     * who is work
     * @return name working
     */
    @Override
    public String work() {
        return "骨头在被"+super.getName()+"吃，价格是"+this.prise+"元";
    }
}