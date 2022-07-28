/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.encapsulation;

public class Encapsulation {

    public static void main(String[] args) {
        /*
         * 封装
         *   就是把抽象出的数据[属性]和对数据的操作方法封装在一起，数据被保护在内部，
         * 程序的其他部分只有通过被授权的方法，才能对数据进行操作
         */
        Person person = new Person();
        person.setAge(130);
        person.setName("收拾收拾宿舍是");
        person.setSalary(100);
        person.sayPerson();
    }
}

class Person {
    public String name;
    public int age;
    public double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() > 0 && name.length() < 6) {
            this.name = name;
        }else {
            System.out.println("超出限制");
            this.name="宝宝";
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("年龄控制在1-120之内");
            this.age = 18;
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Person(){}

    public Person(String name, int age, double salary) {
        setName(name);
        setAge(age);
        setSalary(salary);
    }

    public void sayPerson(){
        System.out.println(this.name+"\t"+this.age+"岁\t"+this.salary+"k");
    }
}