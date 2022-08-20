/*
 * Copyright (c) luoZhiMin 2022.8.20.10.17.13
 */

package com.java.base;

import com.java.base.characteristic.jdk8.entry.User;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Stream API 终止操作
 * @Author : 志敏.罗
 * @create 2022/8/20 22:17
 */
public class StreamEndDetail {

    /**
     *  匹配与查找(allMatch()--anyMatch()--noneMatch()--findFirst()--findAny()-count()....)
     */
    @Test
    void test_01(){
        List<User> users = User.getUsers();
        //allMatch() 匹配所有的元素
        //所有的住宅都在上海
        boolean allMatch = users.stream().allMatch(user -> user.getAddress().equals("上海"));
        System.out.println(allMatch);
        System.out.println();
        // --anyMatch() 匹配任意一个的元素
        boolean anyMatch = users.stream().anyMatch(user -> user.getAddress().equals("上海"));
        System.out.println(anyMatch);
        System.out.println();
        // --noneMatch() 没有
        boolean noneMatch = users.stream().noneMatch(user -> user.getAddress().equals("加速"));
        System.out.println(noneMatch);
        System.out.println();
        // --findFirst() 查找第一个元素
        Optional<User> optionalUser = users.stream().findFirst();
        System.out.println(optionalUser);
        System.out.println();
        // --findAny() 返回任意一个
        for (int i = 0; i < 5; i++) {
            Optional<User> userOptional = users.parallelStream().findAny();
            System.out.println(userOptional);
        }
        System.out.println();
        // -count()-min()-foreach()
        long count = users.stream()
//                .map()
//                .filter()
                .count();
        System.out.println(count);
        System.out.println();
        Optional<Integer> max = users.stream().map(User::getId).max(Long::compare);
        System.out.println(max);
    }

    /**
     * 归约
     */
    @Test
    void test_02(){
        //reduce
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = integers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        System.out.println();
        //所有的id和
        List<User> users = User.getUsers();
        Optional<Integer> reduce = users.stream().map(User::getId).reduce(Integer::sum);
        System.out.println(reduce);
        System.out.println();
        Optional<String> stringConcat = users.stream().map(User::getAddress).reduce(String::concat);
        System.out.println(stringConcat);
    }

    /**
     * 收集
     */
    @Test
    void test_03(){
        //查找id>1的用户 list set map
        List<User> users = User.getUsers();
        List<User> userList = users.stream().filter(user -> user.getId() > 1).collect(Collectors.toList());
        System.out.println(userList);
        System.out.println();
        Set<User> userSet = users.stream().filter(user -> user.getId() > 1).collect(Collectors.toSet());
        System.out.println(userSet);
        System.out.println();
        ConcurrentMap<Integer, String> concurrentMap = users.stream().filter(user -> user.getId() > 1)
                .collect(Collectors.toConcurrentMap(User::getId, User::getName));
        System.out.println(concurrentMap);
        System.out.println();
        Map<Integer, List<User>> userMap = users.stream().filter(user -> user.getId() > 1).collect(Collectors.groupingBy(User::getId));
        System.out.println(userMap);
    }
}
