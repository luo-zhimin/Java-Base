/*
 * Copyright (c) luoZhiMin 2022.8.11.0.28.0
 */

package com.java.base.day.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * regex 基本语法
 * @Author : 志敏.罗
 * @create 2022/8/11 00:28
 */
public class BasicGrammar {

    /*
        元字符（Metacharacter） 分类
        1. 限定符 2.选择匹配符 3.分组组合和反向引用符 4.特殊字符 5.字符匹配符 6.定位符
     */

    /**
     * 转义号\\
     * 需要用到转义符号的字符有以下∶.* + () $ / \ ? [ ] ^ { }
     */
    @Test
    void grammar01(){
//        String content = "abc$(abc(123(";
        String content = "abc$(a.bc(1.23.(";
        //匹配(
        String regex = "\\.\\w{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }


    /**
     * 字符匹配符号
     *
     * [] 可接收的字符列表 [efgh] e、f、g、h中的任意1个字符
     * [^] 不接收的字符列表 [^abc] 除a、b、c之外的任意1个字符,包括数字和特殊符号
     * - 连字符 A-Z 任意单个大写字母
     * . 匹配除\n以外的任何字符 a..b 以a开头，b结尾，中间包括2个任意字
     * \\d 匹配单个数字字符，相当于 [0-9]  \\d{3}(\\d)? 包含3个或4个数字的字符串
     * \\D 匹配单个非数字字符，相当于[^0-9]  \\D(\\d)* 以单个非数字字符开头，后接任意个数
     * \\w 匹配单个数字 大小写字母字符 相当于[0-9a-zA-Z] \\d{3}\\w{4} 以3个数字字符开头的长度为7的数字字
     * \\W 单个非数字、大小写字母 相当于[^0-9a-zA-Z] \\W+\\d{2} 以至少1个非数字字母字符开头，2个数字字符结尾的字符串
     * \\s 匹配如何空白字符(空格 制表符等)
     * \\S 匹配如何非空白字符
     */
    @Test
    void grammar02(){
        String content = "a11c8ab c_ABCy @.";
//        String regex = "[a-z]";//匹配a-z任意一个字符
//        String regex = "[A-Z]";//匹配A-Z任意一个字符
//        String regex = "abc";//默认区分大小写
//        String regex = "(?i)abc";//abc 大小写不敏感 a(?i)bc bc 不区分大小写
//        String regex = "[A-z]";//匹配A-z任意一个字符
//        String regex = "[0-9]";//匹配0-9任意一个字符
//        String regex = "[^a-z]";//匹配不在a-z任意一个字符
//        String regex = "[^0-9]";//匹配不在a-z任意一个字符
//        String regex = "[abcd]";//匹配在 abcd 中任意一个字符
//        String regex = "\\d";//匹配在 0-9 任意一个字符
//        String regex = "\\D";//匹配在不在0-9 任意一个字符
//        String regex = "\\w";//匹配 大小写英文字母 数字 下划线
//        String regex = "\\W";//匹配 非数字 字母 下划线 (特殊字符@！....)
//        String regex = "\\s";//匹配空白字符
//        String regex = "\\S";//匹配非空白字符
        String regex = ".";//匹配非空白字符


//        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE); //Pattern.CASE_INSENSITIVE 不区分大小写
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 ： "+matcher.group(0));
        }
    }


    /**
     * 选择匹配符
     *  匹配某个字符串的时候是选择性的，即∶既可以匹配这个，又可以匹配那个
     * ｜ 匹配“|”之前或之后的表达式 ab|cd  ab或者cd
     */
    @Test
    void grammar03(){

        String content = "dasdadsad 特殊 测试 加油";
        String regex ="sa|测|加";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 ： "+matcher.group(0));
        }
    }


    /**
     * 限定符
     *  用于指定其前面的字符和组合项连续出现多少次
     *  * 指定字符重复0次或n次（无要求）零到多 (abc)* 仅包含任意个abc的字符串，等效于\w*
     *  + 指定字符重复1次或n次（至少一次）m+(abc)* 以至少1个m开头，后接abc的字符指定字符
     *  ? 指定字符重复1次或n次（最多一次 0到1）m+abc 以至少1个m开头，后接ab或abc的字符指定字符
     *  {n} 只能输入n个字符 由abcd中字母组成的任意长度为3的字abc、dbc
     *  {n,} 指定至少n个匹配 [abcd]{3,} 由abcd中字母组成的任意长度不小于3的字符串
     *  {n,m} 指定至少n个但不多于m个 [abcd]{3,5} 由abcd中字母组成的任意长度不小于3，不大于5的字符串
     */
    @Test
    void grammar04(){
        String content = "a211111aaaaaahello";
//        String regex ="a{3}";//匹配 aaa
//        String regex ="1{4}";//匹配 1111
//        String regex ="\\d{2}";//匹配 俩位的任意的数字
        // Java匹配 默认是贪婪匹配 即尽可能匹配多的
//        String regex ="a{3,4}";//匹配 aaa 或者 aaaa
//        String regex ="1{4,5}";//匹配 4个1 或者 5个1
//        String regex ="\\d{2,5}";//匹配 2位数｜3位数｜4｜5
        //+
//        String regex ="1+";//匹配 多个1个1或者多个1
//        String regex ="\\d+";//匹配 多个1个数字或者多个数字
        //*
//        String regex ="1*";//匹配 0个1 或者多个1
        //?
        String regex ="a2?";//匹配 a的后面有一个2 或者没有2
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 ： "+matcher.group(0));
        }
    }
}
