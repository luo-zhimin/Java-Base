/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.poly;

public class Animal {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Animal(String name) {
        this.name = name;
        System.out.println(name+" supper animal..");
    }

    public void cry(){
        System.out.println("animal cry..");
    }

    public void eat(){
        System.out.println("animal eat..");
    }
}
class Cat extends Animal{

    public Cat(String name){
        super(name);
        System.out.println("cat");
    }

    @Override
    public void cry() {
        super.cry();
        System.out.println("cat cry..");
    }

    public void sleep(){
        System.out.println("cat sleep...");
    }
}
class Dog extends Animal{

    public Dog(String name){
        super(name);
        System.out.println("dog..");
    }

    @Override
    public void cry() {
        super.cry();
        System.out.println("cat cry");
    }

    public void show(){
        System.out.println("show dog "+super.getName());
    }
}