package com.java.base.day.reflection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/21 22:49
 */
//@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
@SuppressWarnings({"all"})
public class Cat extends Animal implements IA, IB {

    private static String name = "招财猫";

    public int age = 10;

    protected String job = "招财";

    double salary;

    public static final String address = "上海";

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Cat(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public void hi() {
        System.out.println("hi " + this.name);
    }

    void hi2(String name, String address) {
    }

    private static String hi3(int age, double salary) {
        return "hi3 " + age +"\t"+ salary;
    }

    protected void hi4() {
    }

    public void cry(String name){
        System.out.println(name+" cry");
    }
}

class Animal {
    public String hobby;

    public void an() {
    }

    public Animal(String hobby) {
        this.hobby = hobby;
    }

    public Animal() {
    }
}

interface IA {
}

interface IB {
}
