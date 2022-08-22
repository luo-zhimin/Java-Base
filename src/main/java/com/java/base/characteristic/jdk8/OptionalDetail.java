/*
 * Copyright (c) luoZhiMin 2022.8.16.11.33.28
 */

package com.java.base.characteristic.jdk8;

import com.java.base.characteristic.jdk8.entry.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Optional
 * @Author : 志敏.罗
 * @create 2022/8/16 23:33
 */
public class OptionalDetail {

    /*
        Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常
        Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
        Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
        Optional 类的引入很好的解决空指针异常。
     */

    /*
        创建对象
        Optional.of(T t) 创建一个Optional实例 t非空
        Optional.empty() 创建一个空的Optional实例
        Optional.ofNullable(T t) t可以为空

        Optional.isPresent - 判断值是否存在
        Optional.orElse - 如果值存在，返回它，否则返回默认值
        Optional.get - 获取值，值需要存在
        Optional.orElseGet - 如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果
        Optional.orElseThrow - 如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常
     */

    @Test
    void test_01(){
        User user = new User();
        //创建一个Optional实例 t非空
        Optional<User> optionalUser = Optional.of(user);
        System.out.println(optionalUser);
        System.out.println();
        //创建一个空的Optional实例
        Optional<Object> empty = Optional.empty();
        System.out.println(empty);
        //Optional.ofNullable(T t) t可以为空
        System.out.println();
        Optional<Object> objectOptional = Optional.ofNullable(null);
        System.out.println(objectOptional);
        System.out.println();
        //.......
        boolean present = Optional.of(new User()).isPresent();
        System.out.println(present);

        String get = Optional.of(new User()).map(User::getAddress).orElseGet(String::new);
        System.out.println("get "+get);
    }

    @Test
    void test_02(){
        User user = new User();
        String userName = getUserName(user);
        System.out.println(userName);
    }

    private String getUserName(User user){
//        return user.getName();
        return Optional.ofNullable(user.getName()).orElse("");
    }

    @Test
    void test_03(){
        String hello ="hello";
        //of要求对象不可以为空
        Optional<String> optional = Optional.of(hello);
        System.out.println(optional.get());
        System.out.println();
        //ofNullable(T t)封装数据赋值给Optional 不要求参数非空
        Optional<String> stringOptional = Optional.ofNullable(hello);
        //如果非空 则返回t1
        String hi = stringOptional.orElse("hi");
        System.out.println(hi);
    }
}


