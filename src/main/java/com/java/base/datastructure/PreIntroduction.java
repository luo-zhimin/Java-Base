/*
 * Copyright (c) luoZhiMin 2022.8.24.10.5.40
 */

package com.java.base.datastructure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * 前置介绍
 * @Author : 镜像
 * @create 2022/8/24 22:05
 */
public class PreIntroduction {


    /*
        现在要判断 str1 是否含有 str2, 如果存在，就返回第一次出现的位置, 如果没有，则返回-1
        要求用最快的速度来完成匹配

        暴力匹配 [简单，但是效率低]
        KMP 算法《部分匹配表》老师推荐是否有算法基础

        但是我感觉正则表达式也可以 全词匹配 取start 开始的第一个出现位置


        数据结构包括：线性结构和非线性结构

        1.线性结构作为最常用的数据结构，其特点是数据元素之间存在一对一的线性关系
        2.线性结构有两种不同的存储结构，即顺序存储结构(数组)和链式存储结构(链表)。顺序存储的线性表称为顺序表，顺序表中的存储元素是连续的
        3.链式存储的线性表称为链表，链表中的存储元素不一定是连续的，元素节点中存放数据元素以及相邻元素的地址信息
        4.线性结构常见的有：数组(array)、队列(queue)、链表(linkedList)和栈(stack)

        非线性结构包括：二维数组，多维数组，广义表，树结构(tree)，图结构(graph)
     */

    public static void main(String[] args) {
        String content = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String hava = "尚硅谷你尚 硅你";
        boolean matches = content.matches(hava);
        System.out.println(matches);
        if (!matches){
            System.out.println(-1);
            return;
        }

        Pattern compile = Pattern.compile(hava);
        Matcher matcher = compile.matcher(content);
        if (matcher.find()) {
            System.out.println(matcher.start());
        }
        System.out.println();
    }
}
