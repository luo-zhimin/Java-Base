/*
 * Copyright (c) luoZhiMin 2022.8.8.2.26.43
 */

package com.java.base.day.manhanbuilding.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * 雇佣表
 * @Author : 志敏.罗
 * @create 2022/8/8 14:26
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Long id;

    private Long empId;

    private String password;

    private String name;

    private String job;
}
