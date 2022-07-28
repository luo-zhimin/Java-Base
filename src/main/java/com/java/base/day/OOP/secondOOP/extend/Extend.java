/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.extend;

public class Extend {
    public static void main(String[] args) {
        /*
         * 继承
         *   继承可以解决代码复用,让我们的编程更加靠近人类思维.当多个类存在相同的属性(变量)和方法时,可以从这些类中 抽象出父类,在父类中定义这些相同的属性和方法，
         * 所有的子类不需要重新定义这些属性和方法，只需要通过 extends 来 声明继承父类即可
         *   继承给编程带来的便利
         *      1) 代码的复用性提高了
         *      2) 代码的扩展性和维护性提高了
         *  细节
         * 1) 子类继承了所有的属性和方法，非私有的属性和方法可以在子类直接访问, 但是私有属性和方法不能在子类直接访问，要通过父类提供公共的方法去访问
         * 2) 子类必须调用父类的构造器， 完成父类的初始化
         * 3) 当创建子类对象时，不管使用子类的哪个构造器，默认情况下总会去调用父类的无参构造器，如果父类没有提供无 参构造器，则必须在子类的构造器中用 super 去指定使用父类的哪个构造器完成对父类的初始化工作
         * 否则，编译不会通过
         * 4) 如果希望指定去调用父类的某个构造器，则显式的调用一下 : super(参数列表)
         * 5) super 在使用时，必须放在构造器第一行(super 只能在构造器中使用)
         * 6) super() 和 this() 都只能放在构造器第一行，因此这两个方法不能共存在一个构造器
         * 7) java 所有类都是 Object 类的子类, Object 是所有类的基类.
         * 8) 父类构造器的调用不限于直接父类！将一直往上追溯直到 Object 类(顶级父类)
         * 9) 子类最多只能继承一个父类(指直接继承)，即 java 中是单继承机制
         * 10) 不能滥用继承，子类和父类之间必须满足 is-a 的逻辑关系
         * 继承的本质
         * (1) 首先看子类是否有该属性
         * (2) 如果子类有这个属性，并且可以访问，则返回信息
         * (3) 如果子类没有这个属性，就看父类有没有这个属性(如果父类有该属性，并且可以访问，就返回信息..)
         * (4) 如果父类没有就按照(3)的规则，继续找上级父类，直到 Object...
         */
//        Person person = new Person("西游记",10,111);
        Student student = new Student();
//        student.teaching();
    }
}

class Student extends Person{

    public Student(String name, int age, double score, int garde) {
        super(name, age, score);
        this.garde = garde;
    }

    private int garde;

    public Student(){
//        super(); 无参构造
        //有参构造
//        super("洗手间",1,1);
        this("student",18,100,1);
        System.out.println("student....");
    }

    public void teaching(){
        System.out.println(getName()+"人们开始学习了");
    }

    public int getGarde() {
        return garde;
    }

    public void setGarde(int garde) {
        this.garde = garde;
    }
}

class Person  {
    private String name;
    private int age;

    private double score;

    public Person() {
        System.out.println("person...");
    }

    public Person(String name, int age, double score) {
        setName(name);
        setAge(age);
        setScore(score);
        System.out.println("person.."+name+"\t"+age+"\t"+score);
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}