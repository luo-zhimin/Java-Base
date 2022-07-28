/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.extend.over;

import java.util.Arrays;

public class OverRideDetail {
    public static void main(String[] args) {
        /*
        * 方法重写/覆盖
        *   方法重写(覆盖)就是子类有一个方法，和父类的某个方法的名称，反回类型，参数一样，
        * 那么我们就说子类的这个方法覆盖了父类的方法
        * 细节
        * 子类方法的返回类型和父类方法返回类型一样，或者是父类返回类型的子类
        */
        Animal animal = new Animal("小狗",5);
        System.out.println("====");
        Dog dog = new Dog("小狗",5,"紫色",1);
        System.out.println("....dog override...");
        dog.read();
    }
}
class Animal{

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

    public void cry(){
        System.out.println("animal cry");
    }

    public void eat(){
        System.out.println("animal eat");
    }

    Object read(){
        System.out.println("animal read book");
        return "animal";
    }

    public String say(){
        return this.name + "\t" + this.age;
    }

    public Animal(){
        System.out.println("supper animal");
    }

    public Animal(String name, int age) {
        setName(name);
        setAge(age);
        System.out.println("animal\t"+say());
    }
}
class Dog extends  Animal{

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String color;

    private long id;

    public void eat(){
        System.out.println("override dog eat");
    }

    public void cry(int ...params){
        System.out.println("cry overload"+ Arrays.toString(params));
    }

    protected String read(){
        super.read();
        System.out.println("dog red bone book");
        return "dog eat bone";
    }

    public String say(){
        return this.id + "\t" + super.say() + "\t" + this.color;
    }

    public Dog(String name, int age, String color, long id) {
        super(name, age);
        this.color = color;
        this.id = id;
        System.out.println("dog\t"+this.say());
    }

    public Dog(){
        super.eat();
        this.cry(1,2,3);
        System.out.println("this dog");
    }

}