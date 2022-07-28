/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.poly;

public class Master {
    public Master(String name) {
        this.name = name;
    }

    public Master(){}

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void feed(Cat cat,Fish fish){
        System.out.println("主人"+this.name+"给"+cat.getName()+"买"+fish.getName()+"吃");
    }

    /*
    * animal 编译类型 可以接收animal的子类型
    */
    public void feed(Animal animal,Food food){
        System.out.println("主人"+this.name+"给"+animal.getName()+"买"+food.getName()+"吃");
    }
}
