/*
 * Copyright (c) luoZhiMin 2022.8.13.9.44.41
 */

package com.java.base.day.regex;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * String class regex
 * @Author : 志敏.罗
 * @create 2022/8/13 21:44
 */
public class StringRegex {

    /*
        替换  判断  分割
     */

    public static void main(String[] args) {

        String content = "2000 年 5 月，JDK1.3、JDK1.4 和 J2SE1.3 相继发布，几周后其"
                + "获得了 Apple 公司 Mac OS X 的工业标准的支持。2001 年 9 月 24 日，J2EE1.3 发"
                + "布。2002 年 2 月 26 日，J2SE1.4 发布。自此 Java 的计算能力有了大幅提升";

        //使用正则表达式方式，将 JDK1.3 和 JDK1.4 替换成 JDK
        //替换
        content = content.replaceAll("(JDK1\\.3|JDK1\\.4)","JDK");
        System.out.println(content);

        System.out.println();
        //验证
        //验证手机号 必须是138 139 开头
        content="13980809876";
        boolean matches = content.matches("^13(?:8|9)\\d{8}");
        System.out.println(matches ? "成功":"失败");

        System.out.println();
        //分隔
        //要求按照 # 或者 - 或者 ~ 或者 数字 来分割
        content = "hello#abc-jack12smith~北京";
        String[] split = content.split("(#|-|~|\\d+)");
        System.out.println(Arrays.toString(split));
    }

}
