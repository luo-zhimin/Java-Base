/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.poly;

import java.util.Objects;

public class PolymorphicDetail {

    public static void main(String[] args) {
        /*
        * 多态
        *   方法或对象具有多种形态。是面向对象的第三大特征，多态是建立在封装和继承基础之上的 重写和重载就体现多态
        * 对象的多态 (核心，困难，重点)
        *   一个对象的编译类型和运行类型可以不一致
        *   编译类型在定义对象时，就确定了，不能改变
        *   运行类型是可以变化的
        *   编译类型看定义的 = 号的左边，运行类型看=号的右边
        *   多态的前提是：两个对象(类)存在继承关系
        * 多态向上转型:
        *   父类的引用指向了子类的对象
        * 语法：
        *   父类类型引用名 = new 子类类型();
        * (1)可以调用父类中的所有成员(需遵守访问权限)
        * (2)但是不能调用子类的特有的成员
        * (3)因为在编译阶段，能调用哪些成员,是由编译类型来决定的
        * (4)最终运行效果看子类(运行类型)的具体实现, 即调用方法时，按照从子类(运行类型)开始查找方法，然后调用，规则我前面我们讲的方法调用规则一致
        * 多态向下转型
        *   一个已经向上转型的子类对象可以使用强制类型转换的格式，将父类引用类型转为子类引用各类型
        * 语法：
        *   子类类型 引用名 =（子类类型）父类引用
        * (1)只能强转父类的引用，不能强转父类的对象
        * (2)要求父类的引用必须指向的是当前目标类型的对象
        * (3)当向下转型后可以调用子类型中所有的成员
        *
        * 属性没有重写之说！属性的值看编译类型
        * instanceOf 比较操作符，用于判断对象的运行类型是否为 XX 类型或 XX 类型的子类型
        *
        * Java 重要特性 java 的动态绑定机制
        *   当调用对象方法时，该方法会和该对象的内存地址/运行类型绑定
        *   当调用对象属性时，没有动态绑定机制，哪里声明哪里使用
        */
        Master master = new Master("tom");
        System.out.println("向上转型");
        //animal 编译类型 cat运行类型
        //向上转型
        Animal animal = new Cat("小花猫");
        animal.cry();
        animal.eat();
        //因为在编译阶段，能调用哪些成员,是由编译类型来决定的
//        animal.sleep();
        Food food = new Fish("金龙鱼");
        master.feed(animal,food);
        System.out.println("向下转型");
        //只能强转父类的引用，不能强转父类的对象  Cat cat = (Cat) new Animal("加菲猫");
        Cat cat =(Cat) animal;
        cat.sleep();//cat
        cat.eat();//animal

        Animal an = new Dog("animal");
        Dog dog = (Dog) an;
        dog.show();
        System.out.println(dog instanceof Animal);
    }
}

/**
 * 多态数组
 */
class PolyArray{

    public static void main(String[] args) {
        //1个person 2个学生 2个老师
        Person[] persons = new Person[5];
        persons[0] = new Person("jack",22);
        persons[1] = new Student("tom",18,88);
        persons[2] = new Student("jerry",18,99);
        persons[3] = new Teacher("cat",30,2000);
        persons[4] = new Teacher("mouse",22,3000);

        for (Person person : persons) {
            //动态绑定
            System.out.println(person.say());
            if (person instanceof Teacher) {
                System.out.println("...........person instanceof Teacher........");
                Teacher teacher = (Teacher) person;
                teacher.teach();
            } else if (person instanceof Student) {
                System.out.println("...........person instanceof Student........");
                Student student = (Student) person;
                student.study();
            }
        }
    }
}
class Person{
    private String name;
    private int age;

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

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String say(){
        return this.name+"\t"+this.age;
    }

    private void eat(){
        System.out.println(this.getName()+" 在吃饭..");
    }
}
class Student extends Person{

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.score, score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    private double score;

    public Student(String name, int age, double score) {
        super(name, age);
        this.score = score;
    }

    @Override
    public String say() {
        return super.say()+"\t"+this.score;
    }

    public void study(){
        System.out.println(this.getName()+" student study...");
    }

    private void sleep(){
        System.out.println(this.getName()+" student sleep..");
    }
}
class Teacher extends Person{
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private double salary;

    public Teacher(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public String say() {
        return "teacher "+super.say()+"\t"+this.salary;
    }

    public void teach(){
        System.out.println(this.getName()+" teacher teach..");
    }
}