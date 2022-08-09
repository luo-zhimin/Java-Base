/*
 * Copyright (c) luoZhiMin 2022.8.8.2.33.27
 */

package com.java.base.day.manhanbuilding.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * 流水
 * @Author : 志敏.罗
 * @create 2022/8/8 14:33
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    private Long id;

    private String billId;

    private Long menuId;

    /**
     * 份数
     */
    private String nums;

    private Double money;

    /**
     * 餐桌
     */
    private Long diningTableId;

    /**
     * 订单日期
     */
    private String billDate;

    /**
     * 状态 '未结账' , '已经结账'
     */
    private String state;
}
