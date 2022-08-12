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
}
