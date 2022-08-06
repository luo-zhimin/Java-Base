/*
 * Copyright (c) luoZhiMin 2022.8.5.10.16.23
 */

package com.java.base.day.jdbc.util;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/5 22:16
 */
public class JdbcUtil {

    /**
     * @return 一个数据库的连接
     */
    @SneakyThrows
    public static Connection getConnect() {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("src/main/resources/mysql.properties")));
        //加载驱动
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(properties.getProperty("url"), properties);
    }
}
