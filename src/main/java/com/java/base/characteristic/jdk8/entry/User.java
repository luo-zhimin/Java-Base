/*
 * Copyright (c) luoZhiMin 2022.8.16.6.15.15
 */

package com.java.base.characteristic.jdk8.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/16 18:15
 */
@Data
@ToString
//@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    private Integer id;

    private String name;

    private String address;

    /**
     * @return 初始化用户
     */
    public static List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"胡歌","上海"));
        users.add(new User(2,"关晓彤","北京"));
        users.add(new User(3,"杨超越","北京"));
        users.add(new User(4,"古天乐","香港"));
        users.add(new User(5,"周星驰","香港"));
        return users;
    }

    public User(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
