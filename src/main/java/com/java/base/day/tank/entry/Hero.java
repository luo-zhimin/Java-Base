/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.tank.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * MyTank
 * @Author : 志敏.罗
 * @create 2022/6/28 13:57
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Hero extends Tank {
    public Hero(int x, int y, String direction) {
        super(x, y, direction);
        setType(0);
    }
}
