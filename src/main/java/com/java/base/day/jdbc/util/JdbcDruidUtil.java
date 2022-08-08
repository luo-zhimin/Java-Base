/*
 * Copyright (c) luoZhiMin 2022.8.7.6.52.50
 */

package com.java.base.day.jdbc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * druid
 * @Author : 志敏.罗
 * @create 2022/8/7 18:52
 */
public class JdbcDruidUtil {

    private static final DataSource dataSource;

    //static init
    static {
        try {
            Properties properties = new Properties();
            properties.load(Files.newInputStream(Paths.get("src/main/resources/druid.properties")));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static Connection getDruidConnection(){
        return dataSource.getConnection();
    }

    /**
     * 连接池关闭 是把连接放回缓冲池中 不是真的关闭
     */
    @SneakyThrows
    public static void closeDruidConnection(Connection connection,
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
//        System.out.println("druid close....");
    }
}
