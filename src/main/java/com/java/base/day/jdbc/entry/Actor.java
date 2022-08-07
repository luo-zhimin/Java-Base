/*
 * Copyright (c) luoZhiMin 2022.8.7.9.11.19
 */

package com.java.base.day.jdbc.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/7 21:11
 */
@Data
@ToString
@NoArgsConstructor //反射需要
@AllArgsConstructor
public class Actor {
    private Long id;
    private String name;
    private String sex;
    private Date bornDate;
    private String phone;
}
