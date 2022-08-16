/*
 * Copyright (c) luoZhiMin 2022.8.16.6.24.29
 */

package com.java.base.characteristic.jdk8;

import com.java.base.characteristic.jdk8.entry.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by IntelliJ IDEA.
 * 构造器引用/数组引用
 * @Author : 志敏.罗
 * @create 2022/8/16 18:24
 */
public class ConstructorQuote {

    /*
        构造器引用
            和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致，
          抽象方法的返回值类型即为构造器所属的类的类型

        数组引用
            可以把数组引用看作一个特殊的类，则和构造器引用一样
     */


    //构造器引用
    @Test
    void constructor_quote_01(){
        Supplier<User> supplier = ()->new User();
        System.out.println("lambda "+supplier.get());
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        supplier = User::new;
        System.out.println("构造器引用 "+supplier.get());

        System.out.println();

        Function<Integer,User> function = id->new User(id);
        System.out.println("lambda "+function.apply(5));
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        function = User::new;
        System.out.println("构造器引用 "+function.apply(1));

        System.out.println();
        BiFunction<Integer,String,User> biFunction = (id,name)->new User(id,name);
        System.out.println("lambda "+biFunction.apply(1, "刘亦菲"));
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        biFunction = User::new;
        System.out.println("构造器引用 "+biFunction.apply(1, "刘亦菲"));
    }


    //数组引用
    @Test
    void constructor_quote_02(){
        Function<Integer,String[]> function = (length)->new String[length];
        System.out.println("lambda "+Arrays.toString(function.apply(5)));
        System.out.println("～～～～～～～～～～～～～～～～～～～～～～～～～～～～～");
        function = String[]::new;
        System.out.println("数组引用 "+Arrays.toString(function.apply(5)));
    }
}
