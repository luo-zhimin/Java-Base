/*
 * Copyright (c) luoZhiMin 2022.8.8.11.54.46
 */

package com.java.base.day.jdbc.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/8 11:54
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String name;
    private String buy_people;
    private Integer number;
}
