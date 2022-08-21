/*
 * Copyright (c) luoZhiMin 2022.8.16.11.32.51
 */

package com.java.base.characteristic.jdk8;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by IntelliJ IDEA.
 * java8 Date Time API
 * @Author : 志敏.罗
 * @create 2022/8/16 23:32
 */
public class TimeDetail {

    /*
        Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
            Local(本地) − 简化了日期时间的处理，没有时区的问题
            Zoned(时区) − 通过制定的时区处理日期时间

     */

    /**
     * 本地
     */
    @Test
    void localDateTime_test(){
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前时间："+localDateTime);
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println("日期："+localDate);
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println("时间："+localTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-hh HH:mm:ss");
        String currentTime = localDateTime.format(formatter);
        System.out.println("转换后时间："+ currentTime);
        //获取指定时间日期
        LocalTime time = LocalTime.of(22, 18,22);
        System.out.println("获取指定时间："+time);
        LocalDate date = LocalDate.of(2022, 8, 21).withDayOfMonth(10);
        //withDayOfMonth 当前月的几号
        System.out.println("获取指定日期："+date);
    }


    /**
     * 时区
     */
    @Test
    void zoned_test(){
        //2022-08-21T22:24:31.542+08:00[Asia/Shanghai]
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("获取当前时间日期："+zonedDateTime);
        ZoneId zoneId = ZoneId.of("GMT-8");
        System.out.println("utc："+zoneId);
        //获取当前时区
        ZoneId systemDefault = ZoneId.systemDefault();
        System.out.println("获取当前时区："+systemDefault);
    }
}
