/*
 * Copyright (c) luoZhiMin 2022.8.16.6.22.51
 */

package com.java.base.characteristic.jdk8;

import com.java.base.characteristic.jdk8.entry.User;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by IntelliJ IDEA.
 * 方法引用
 * @Author : 志敏.罗
 * @create 2022/8/16 18:22
 */
public class MethodQuote {

    /*
        1.使用情景：当要传递给lambda 体的操作，已经有实现的方法了，可以使用方法引用
        2.本质就是lambda表达式，而lambda表达式作为函数接口的实例，所以方法引用，也是函数式接口的实例
            方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码
        3.使用的格式： 类(或对象) :: 方法名
        4.有三种实现情况
            (1) 对象::非静态方法
            (2) 类::静态方法
            (3) 类::非静态方法
        5.方法使用的要求：要求接口中的抽象方法的形参列表和返回值类型 与 方法引用的方法的形参列表和返回值类型相同！情况(1 or 2)
     */


    //consumer supplier
    @Test
    void methodQuote_01(){
        //情况一 对象 实例方法 -> 对象::非静态方法
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("lambda贝斯");
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        consumer = System.out::println;
        consumer.accept("方法引用 贝斯");

        System.out.println();
        User user = new User(6,"刘亦菲","北京");
        Supplier<String> supplier = ()->user.getName();
        System.out.println("lambda "+supplier.get());
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        supplier = user::getName;
        System.out.println("方法引用 "+ supplier.get());
    }


    //comparator function
    @Test
    void methodQuote_02(){
        //类::静态方法
        Comparator<Integer> comparator =(t1,t2)->Integer.compare(t1,t2);
        System.out.println("lambda "+comparator.compare(12,21));
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        comparator = Integer::compare;
        System.out.println("方法引用 "+comparator.compare(12,3));

        System.out.println();
        Function<Double,Long> function = d->Math.round(d);
        System.out.println("lambda "+function.apply(2.0));
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        function = Math::round;
        System.out.println("方法引用 "+function.apply(12.5));
    }


    @Test
    void methodQuote_03(){
        //情况三 类::非静态方法
        Comparator<String> comparator = (s1,s2)->s1.compareTo(s2);
        System.out.println("lambda "+ comparator.compare("abc","abd"));
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        comparator = String::compareTo;
        System.out.println("方法引用 "+ comparator.compare("abc","abb"));

        System.out.println();
        BiPredicate<String,String> biPredicate = (s1,s2)->s1.equals(s2);
        System.out.println("lambda "+biPredicate.test("a", "c"));
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        biPredicate = String::equals;
        System.out.println("方法引用 "+ biPredicate.test("a","a"));
    }
}
