/*
 * Copyright (c) luoZhiMin 2022.8.5.10.16.23
 */

package com.java.base.day.jdbc.util;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/5 22:16
 */
public class JdbcUtil {

    private static final Properties properties;

    //static load
    static {
        System.out.println("properties static until jdbc load");
        properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("src/main/resources/mysql.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return 一个数据库的连接
     */
    @SneakyThrows
    public static Connection getConnect()
    {
        //加载驱动
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(properties.getProperty("url"), properties);
    }

    /**
     * 关闭JDBC连接
     * @param connection        连接对象
     * @param resultSet         select返回的结果集
     * @param statement 获取到的连接
     */
    @SneakyThrows
    public static void closeConnect(Connection connection,
                                    ResultSet resultSet,
                                    Statement statement)
    {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
        System.out.println("close....");
    }

}
