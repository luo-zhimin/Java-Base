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
 * @create 2022/6/5 21:32
 */
public class CodeBlock {
    public static void main(String[] args) {
        /*
         * 代码快
         *   代码化块又称为初始化块，属于类中的成员【即 是类的一部分】，类似于方法，将逻辑语句封装在方法体中，通过{}包围起来。
         *   但和方法不同，没有方法名，没有返回，没有参数，只有方法体，而且不用通过对象或类显式调用，而是加载类时，或创建对象时隐式调用。
         * 基本语法
         *   修饰符{代码}
         * 注意
         *   1）修饰符可选，要写的话，也只能写static
         *   2）代码块分为两类，使用static 修饰的叫静态代码块，，没有static修饰的，叫普通代码块/非静态代码块。
         *   3）逻辑语句可以为任何逻辑语句（输入、输出、方法调用、循环、判断等）
         *   4）;号可以写上，也可以省略。
         *   5）相当于另外一种形式的构造器（对构造器的补充机制），可以做初始化的操作
         *   6）场景如果多个构造器中都有重复的语句，可以抽取到初始化块中，提高代码的重用性
         *   7) 这样当我们不管调用哪个构造器，创建对象，都会先调用代码块的内容
         *   8) 代码块调用的顺序优先于构造器
         * 细节
         *   1) static代码块也叫静态代码块，作用就是对类进行初始化，而且它随着类的加载而执行，并且只会执行一次。如果是普通代码块，每创建一个对象，就执行。
         *   2)类什么时候被加载【重要背!】
         *       1.创建对象实例时（new）
         *       2.创建子类对象实例，父类也会被加载
         *       3.使用类的静态成员时（静态属性，静态方法)
         *   3）普通的代码块，在创建对象实例时，会被隐式的调用。被创建一次，就会调用一次。如果只是使用类的静态成员时，普通代码块并不会执行。
         *   小结∶
         *       1.static代码块是类加载时，执行，只会执行一次
         *       2.普通代码块是在创建对象时调用的，创建一次，调用一次
         *       3.类加载的3种情况，需要记住
         *  4) 创建一个对象时，在一个类 调用顺序是(!!!!!)
         *      1.调用静态代码块和静态属性初始化（注意∶静态代码块和静态属性初始化调用的优先级一样，如果有多个静态代码块和多个静态变量初始化，则按他们定义的顺序调用）
         *      2.调用普通代码块和普通属性的初始化（注意∶普通代码块和普通属性初始化调用的优先级一样，如果有多个普通代码块和多个普通属性初始化，则按定义顺序调用）
         *      3.调用构造方法。
         *  5）构造器的最前面其实隐含了super() 和调用普通代码块，静态相关的代码块，属性初始化，在类加载时，就执行完毕因此是优先于 构造器和普通代码块执行的
         *  6）创建一个子类对象时（继承关系），他们的静态代码块，静态属性初始化，普通代码块，普通属性初始化，构造方法的调用顺序如下∶
         *      1.父类的静态代码块和静态属性（优先级一样，按定义顺序执行）
         *      2.子类的静态代码块和静态属性（优先级一样，按定义顺序执行）
         *      3.父类的普通代码块和普通属性初始化（优先级一样，按定义顺序执行）
         *      4.父类的构造方法
         *      5.子类的普通代码块和普通属性初始化（优先级一样，按定义顺序执行）
         *      6.子类的构造方法//面试题
         *   7）静态代码块只能直接调用静态成员（静态属性和静态方法），普通代码块可以调用任意成员
         */
        new Movie("你好，喜洋洋");
        System.out.println("===");
        new Movie("复仇者联盟", 100);
        System.out.println("====static===");
        Movie.count++;
        Movie.count();
        System.out.println("static extends....");
        new Person();
        Person.showPersonName();
    }
}

class Movie {
    protected static int count = 0;
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //代码块
    {
        System.out.println("movie start....");
        System.out.println("pay money..");
        System.out.println("movie end...");
    }

    //静态代码块
    static {
        System.out.println("Movie静态代码块执行");
    }

    static {
        System.out.println("Movie~~~~静态代码块执行~~~");
    }

    /**
     * 构造器 =》重载
     *
     * @param name  name
     * @param price price
     */
    public Movie(String name, double price) {
        System.out.println("movie...name " + name + " ..price.. " + price);
        this.name = name;
        this.price = price;
    }

    public Movie(String name) {
        System.out.println("movie...name.. " + name);
        this.name = name;
    }

    public Movie() {
        System.out.println("movie 无参构造");
    }

    public static void count(){
        System.out.println("count="+count++);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
class Person extends Movie{

    protected static String name = initName();

    static {
        System.out.println("Person static static init ");
    }

    {
        System.out.println("person code block");
    }
    public Person() {
        System.out.println("person 无参构造{}");
    }

    protected static void showPersonName(){
        System.out.println("name: "+name);
    }

    private static String initName(){
        System.out.println("person  initName ");
        return "static property person";
    }
}