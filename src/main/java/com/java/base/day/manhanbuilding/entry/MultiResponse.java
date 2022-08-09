/*
 * Copyright (c) luoZhiMin 2022.8.9.10.52.50
 */

package com.java.base.day.manhanbuilding.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * 多表查询接收
 * @Author : 志敏.罗
 * @create 2022/8/9 10:52
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MultiResponse {

    private Long id;

    private Long menuId;

    private String name;

    private Integer nums;

    private Double money;

    private Long diningTableId;

    private String state;
}
