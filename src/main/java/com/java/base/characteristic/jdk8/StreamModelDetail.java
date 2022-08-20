/*
 * Copyright (c) luoZhiMin 2022.8.16.11.28.14
 */

package com.java.base.characteristic.jdk8;

import com.java.base.characteristic.jdk8.entry.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * stream API -- 中间操作
 * @Author : 志敏.罗
 * @create 2022/8/16 23:28
 */
public class StreamModelDetail {

    /*
        新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中 提供了一种
     高效且易于使用的处理数据的方式

        Stream关注的是对数据的运输 与CPU打交道
        特点
            不会存储数据
            不会改变源对象 他们会返回一个持有结果的新Stream
            操作是延迟的 等到需要结果时候才执行

        执行流程
            Stream实例化
            一系列中间操作(过滤，映射...)
            终止操作(一旦终止后，就不能再次被使用)

        中间操作
            筛选+切片(过滤-截断-跳过-筛选)
            映射(map(=>list.add())--flatMap(=>list.addAll()))
            排序(sort() -- sort(Comparator))

        终止操作
            匹配与查找(allMatch()--anyMatch()--noneMatch()--findFirst()--findAny())
     */


    //通过集合创建
    @Test
    void test_01(){
        List<User> users = User.getUsers();
        //顺序流
        Stream<User> userStream = users.stream();
        //并行流 线程 同时取
        Stream<User> parallelStream = users.parallelStream();
    }


    //通过数组
    @Test
    void test_02(){
        int[] ints ={1,2,3};
        //Arrays
        IntStream intStream = Arrays.stream(ints);
        User user = new User(6, "刘亦菲");
        User[] userArr = new User[]{user};
        Stream<User> userStream = Arrays.stream(userArr);
    }


    //of创建
    @Test
    void test_03(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    }

    //无限流
    @Test
    void test_04(){
        //迭代 iterate
        //遍历前10个偶数
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);
        //生成 generate
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }


    //-----------Stream 中间操作  筛选+切片(过滤-截断-跳过-筛选)
    @Test
    void  test_05(){
        List<User> users = User.getUsers();
        //过滤
        users.stream().filter(u -> u.getId() < 5).forEach(System.out::println);

        System.out.println();
        //截断流
        users.stream().limit(2).forEach(System.out::println);

        System.out.println();
        //跳过 前面多少个数据
        users.stream().skip(3).forEach(System.out::println);

        System.out.println();
        //筛选 hashcode equals 去重
        //添加重复数据
        users.add(new User(3,"杨超越","北京"));
        users.stream().distinct().forEach(System.out::println);
    }

    /**
     * 映射
     */
    @Test
    void  test_06(){
        //
        List<String> strings = Arrays.asList("aa", "bb", "cc", "dd");
        
        strings.stream().map(s -> s.toUpperCase(Locale.ROOT)).forEach(System.out::println);
        System.out.println();
        //获取用户长度大于3的用户
        List<User> users = User.getUsers();
        //一
        users.stream().filter(user -> user.getName().length()>=3).forEach(System.out::println);
        System.out.println();
        //二
        users.stream().map(User::getName).filter(n->n.length()>=3).forEach(System.out::println);
        System.out.println();
        //
        Stream<Stream<Character>> streamStream = strings.stream().map(this::fromStringToStream);
        streamStream.forEach(s->{
            s.forEach(System.out::println);
        });
        System.out.println();
        /*
            flatMap 接收一个函数作为参数 将流中的每个值换成另一个流，然后所有的流连接成一个流
         */
        Stream<Character> characterStream = strings.stream().flatMap(this::fromStringToStream);
        characterStream.forEach(System.out::println);

    }
    
    //转换 string -> Character -> Stream<Character>
    private Stream<Character> fromStringToStream(String s){
        ArrayList<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 排序
     */
    @Test
    void  test_07(){
        List<Integer> integers = Arrays.asList(1, 3, 2, 44, 22, 111, 32);
        //sorted
        integers.stream().sorted().forEach(System.out::println);
        System.out.println();
        List<User> users = User.getUsers();
        //com.java.base.characteristic.jdk8.entry.User cannot be cast to java.lang.Comparable 需要实现 Comparable
//        users.stream().sorted().forEach(System.out::println);
        //sorted(Comparator)
        //定制排序
        users.stream().sorted((o1,o2)->o1.getName().length()-o2.getName().length()).forEach(System.out::println);
    }
}
