/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socketProject.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * 用户信息
 * @Author : 志敏.罗
 * @create 2022/7/17 22:10
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -7218940667320646057L;

    /**
     * 用户Id/用户姓名
     */
    private String userId;

    /**
     * 密码
     */
    private String password;
}
