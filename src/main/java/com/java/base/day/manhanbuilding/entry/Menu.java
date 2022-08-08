/*
 * Copyright (c) luoZhiMin 2022.8.8.2.30.2
 */

package com.java.base.day.manhanbuilding.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * 菜谱
 * @Author : 志敏.罗
 * @create 2022/8/8 14:30
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Long id;

    private String name;

    private String type;

    private Double price;
}
