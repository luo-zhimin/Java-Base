/*
 * Copyright (c) luoZhiMin 2022.8.16.4.9.3
 */

package com.java.base.characteristic.jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by IntelliJ IDEA.
 * function 函数式接口
 * @Author : 志敏.罗
 * @create 2022/8/16 16:09
 */
public class FunctionDetail {
    /*
        只包含一个抽象方法的接口就叫函数式接口
        我们可以在一个接口上使用@FunctionalInterface，这样就可以检查他是否是一个函数式接口 同时javadoc 也会包含一条声明

        java内置四大核心函数式接口
            Consumer<T> 消费型接口       void accept(T t)
            Supplier<T> 供给型接口       T get()
            Function<T,R> 函数型接口     R apply(T t)
            Predicate<T> 断定型接口      boolean test(T t)
     */
    public static void main(String[] args) {
        MyInterface myInterface = () -> System.out.println("eat");
        myInterface.eat();
    }


    @Test
    void function_01(){
        //consumer
        happyTime(1, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学习太累了，饿了 需要吃饭 消费为"+aDouble);
            }
        });

        happyTime(400,money-> System.out.println("(lambda)学习太累了，饿了 需要吃饭 消费为"+money));

        System.out.println("*********************");
        //Predicate
        List<String> strings = Arrays.asList("北京","天津","南京","东京");
        List<String> filters = filterString(strings, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filters);

        List<String> filters2 = filterString(strings,s->s.contains("京"));
        System.out.println("(lambda) "+filters2);
    }

    private void happyTime(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

    private List<String> filterString(List<String> strings,Predicate<String> predicate){
        List<String> filterList = new ArrayList<>();
        strings.forEach(s->{
            if (predicate.test(s)){
                filterList.add(s);
            }
        });
        return filterList;
    }
}
/**
 * 自定义函数式接口
 */
@FunctionalInterface
interface MyInterface{

    void eat();

//    void look(); 使用FunctionalInterface 只可以有一个抽象方法
}