/*
 * Copyright (c) luoZhiMin 2022.8.20.10.17.13
 */

package com.java.base;

import com.java.base.characteristic.jdk8.entry.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
}
