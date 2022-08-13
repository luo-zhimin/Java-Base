/*
 * Copyright (c) luoZhiMin 2022.8.12.5.22.28
 */

package com.java.base.day.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * regex 练习
 * @Author : 志敏.罗
 * @create 2022/8/12 17:22
 */
public class RegexExercise {

    /*
        要求
            1. 汉字
            2. 邮政编码
                是1-9开头的一个六位数.比如∶123890
            3.QQ号码
                1-9开头的一个（5位数-10位数）比如12389，1345687，187698765
            4.手机号码
                要求必须以13，14，15，18开头的11位数，比如13889999999
            5.url
                https∶//www.bilibili.com/video/BV1fh411y7R8?from=search&seid=1831060912083761326
     */
    @Test
    void exercise_01(){
        //汉字
        String content ="你好呀 111";
        //邮政
        content = "1345687";
        //QQ
        content = "1358889999d";
        //手机号码
        content = "18588890999";
        String regex = "[^\\W+]+";//一种
        regex = "^[\u0391-\uffe5]+$";
        regex ="^[1-9]\\d{5}";//邮政
        regex ="^[1-9]{4,9}";//QQ
        regex ="(^1[3|4|5|8]\\d{9})";//phone

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()){
            System.out.println("符合");
            System.out.println(matcher.group());
        }else {
            System.out.println("不符合");
        }
    }


    @Test
    void exercise_url(){
        /*
            先确认url的构成
             1 前缀 https:// 协议 ^((http|https)://)
             2 域名 www.baidu.com ([\w-]+\.)+[\w-]+
             3 url 后面拼接参数 (\/[\w-?=&/%.#+]*)?
         */
//        String content ="https://www.bilibili.com/video/BV1fh411y7R8?from=search&seid=1831060912083761326";
        String content ="https://www.google.com.hk/search?q=%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F+%E5%9C%A8%E7%BA%BF%E8%B0%83%E8%AF%95&newwindow=" +
                "1&ei=nCX2YsyLH8Tq-QackKewDg&oq=%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F%E5%9C%A8%E7%BA%BF&gs_lcp" +
                "=Cgdnd3Mtd2l6EAEYCTIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEM" +
                "gUIABCABDIFCAAQgAQ6BwgAEEcQsANKBAhBGABKBAhGGABQ9gJYoQRg2SRoAXABeACAAWqIAb0BkgEDMS4xmAEAoAEByAEJwAEB&sclient=gws-wiz";
        //[]里面 -?./& 都是正常的符号 不是限定符号
        String regex = "^((http|https)://)?([\\w-]+\\.)+[\\w-]+(\\/[\\w-?=&/%.#+]*)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()){
//            System.out.println("符合");
            System.out.println(matcher.group());
        }else {
            System.out.println("不符合");
        }
    }


    /*
        分组、捕获、反向引用(应用)
        分组
            我们可以用圆括号组成一个比较复杂的匹配模式，那么一个圆括号的部分我们可以看作是一个子表达式/一个分组
         捕获
            把正则表达式中子表达式/分组匹配的内容，保存到内存中以数字编号或显式命名的组里，方便后面引用，从左向右，以分组的左括号为标志，第一个出现的分组的组号为1，第二个为2，以此类推。组0代表的是整个正则式
        反向引用
           圆括号的内容被捕获后，可以在这个括号后被使用，从而写出一个比较实用的匹配模式，这个我们称为反向引用，这种引用既可以是在正则表达式内部，也可以是在正则表达式外部， 内部反向引用 \\分组号，外部反向引用 $分组号
     */

    /**
     * 反向引用
     */
    @Test
    void exercise_02(){
        //1.要匹配两个连续的相同数字 (\\d)\\1
        //2.要匹配五个连续的相同数字 (\\d)\\1{4}
        //3.要匹配个位与千位相同，十位与百位相同的数5225，1551 (\\d)(\\d)\\2\\1

        //请在字符串中检索商品编号，形式如∶12321-3339911这样的号码，要求满足前面是一个五位数，然后一个-号，然后是一个九位数，连续的每三位要相同

        String content ="hello java tom11 smith12 jerry001 john1997 j8776 99999 88888 44444";
        content = "12321-3339911";
        String regex = "(\\d)\\1";//两个连续的相同数字
        regex = "(\\d)\\1{4}";//五个连续的相同数字
        regex ="(\\d)(\\d)\\2\\d";
        regex = "\\d{5}-(\\d)\\1{2}(\\d)\\2(\\d)\\3";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            //default group(0)
            System.out.println("find: "+matcher.group());
        }
    }

    /**
     * 结巴程序去重
     */
    @Test
    void set(){
        String content ="我....我要....学学学学....编程java!";
//        String regex="\\.";//去除 . 号
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(content);
//        content = matcher.replaceAll("");
//        System.out.println(content);
//        //去掉重复的字
//        //使用 (.)\\1+ 匹配一到多
//        //替换 $1 外部匹配
//        regex="(.)\\1+";//分组的捕获内容记录到 $1
//        pattern = Pattern.compile(regex);
//        matcher = pattern.matcher(content);
//        while (matcher.find()) {
//            System.out.println("找到："+matcher.group());
//        }
//
//        content = matcher.replaceAll("$1");
//        System.out.println("2. "+content);

        content=content.replaceAll("\\.","");
        //使用一条语句
        content = Pattern.compile("(.)\\1+").matcher(content).replaceAll("$1");
        System.out.println(content);
    }


    /**
     * 验证电子邮件格式
     */
    @Test
    void exercise_03(){
        //验证电子邮件格式
        //只能有一个@
        //2.@前面是用户名，可以是a-z A-Z 0-9_-字符
        //3.@后面是域名，并且域名只能是英文字母，比如 sohu.com或者 tsinghua.org.cn
        String email ="179867_-aa7862@qq.ww.com";// ([A-z|\d|_|-]) \w
        Pattern pattern = Pattern.compile("^([\\w|_|-])+@([a-z]+\\.)+c(om|n)$");
        Matcher matcher = pattern.matcher(email);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * 验证是不是整数或者小数
     */
    @Test
    void exercise_04(){
        String number ="123 -345 34.89 -87.9 -0.01 0.45";
        number ="0.98";
        //考虑正数 负数
        /*
            先写简单表达式
            根据实际拓展(考虑负) [-+]?(正数|负数)  (\.\d+)?小数 一次或者多次
         */
        Pattern pattern = Pattern.compile("^[-+]?([1-9](\\d)*|0?)(\\.\\d+)?$");
        Matcher matcher = pattern.matcher(number);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }


    /**
     * url 验证
     */
    @Test
    void exercise_05(){
        //https://stool.chinaz.com/tools/pagecode.aspx
        //http://www.sohu.com:8080/abc/index.html
        //https://www.yuque.com/wunian-xhpcz/slcgd6/lpdyc8
        /*
            协议
            域名
            端口
            参数
         */
        String url="https://www.google.com/search?q=java8%E4%B8%8B%E8%BD%BD&oq=&aqs=chrome.1.69i59i450l8.24458233j0j7&sourceid=chrome&ie=UTF-8";
        Pattern pattern = Pattern.compile("^((http|https)://)(\\w+\\.)+(\\w+)(:\\d+)?(/[\\w.\\-&=?%]+)*$");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
