/*
 * Copyright (c) luoZhiMin 2022.8.8.2.28.37
 */

package com.java.base.day.manhanbuilding.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * 餐桌
 * @Author : 志敏.罗
 * @create 2022/8/8 14:28
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiningTable {

    private Long id;

    private String state;

    private String orderName;

    private String orderTel;
}
