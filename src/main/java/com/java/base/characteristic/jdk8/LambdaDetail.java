/*
 * Copyright (c) luoZhiMin 2022.8.16.9.39.50
 */

package com.java.base.characteristic.jdk8;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Created by IntelliJ IDEA.
 * lambda java8 新特性
 * @Author : 志敏.罗
 * @create 2022/8/15 22:29
 */
@SuppressWarnings("all")
public class LambdaDetail {

    /*
        Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性
        Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）
        使用 Lambda 表达式可以使代码变的更加简洁紧凑
     */

    @Test
    void test_01(){

        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("normal----我爱北京天安门");
            }
        };
        runnable.run();
        System.out.println("**************");

        Runnable runnable2 = () -> System.out.println("lambda----我爱北京天安门");
        runnable2.run();
    }

    @Test
    void test_02(){

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        System.out.println("normal --- "+comparator.compare(11,21));
        System.out.println("**************");
        Comparator<Integer> comparator1 = (o1,o2)->Integer.compare(o1,o2);
        System.out.println("lambda --- "+comparator1.compare(21,11));
        System.out.println("**************");
        Comparator<Integer> comparator2 = Integer::compareTo;
        System.out.println("lambda 方法引用 --- "+comparator2.compare(0,11));
    }

    /*
        解释
            (o1,o2)->Integer.compare(o1,o2);
         格式
            ->:lambda操作符 或者 箭头操作符号
            左边：lambda形参列表（其实就是接口中的抽象）
            右边：lambda体（其实就是抽象方法的方法体）
         lambda表达式使用(6种)
            语法格式一 无参-无返回值
            语法格式二 含参-无返回值
            语法格式三 数据类型可以省略 因为可以由编译器推断得出 称为"类型推断"
            语法格式四 lambda 若只需要一个参数时 参数的小括号可以省略
            语法格式五 lambda 需要俩个或者俩个以上的参数时 多条执行语句 并且可以有返回值
            语法格式六 lambda 体只有一条语句时 return 与 大括号 都可以省略
         lambda表达式的本质
            作为接口 实例对象存在
     */
    @Test
    void lambda_grammar(){
        //语法格式一 无参-无返回值
        Runnable runnable2 = () -> System.out.println("lambda----无参-无返回值");
        runnable2.run();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //语法格式二 含参-无返回值
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("accept");

        Consumer<String> consumer1 = (String s)-> System.out.println(s);
        consumer1.accept("lambda----含参-无返回值");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //语法格式三 数据类型可以省略 因为可以由编译器推断得出 称为"类型推断"
        Consumer<String> consumer2 = (s)-> System.out.println(s);
        consumer2.accept("lambda----类型推断-（编译器推断得出）");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //语法格式四 lambda 若只需要一个参数时 参数的小括号可以省略
        Consumer<String> consumer3 = s-> System.out.println(s);
        consumer3.accept("lambda----语法格式四-一个参数时 - 小括号可以省略 ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //语法格式五 lambda 需要俩个或者俩个以上的参数时 多条执行语句 并且可以有返回值
        Comparator<Integer> comparator1 = (o1,o2)->{
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(comparator1.compare(12,21));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //语法格式六 lambda 体只有一条语句时 return 与 大括号 都可以省略
        Comparator<Integer> comparator2 = (o1,o2)-> o1.compareTo(o2);
        System.out.println(comparator2.compare(24,21));
    }
}