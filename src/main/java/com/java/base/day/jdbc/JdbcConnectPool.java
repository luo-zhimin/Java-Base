/*
 * Copyright (c) luoZhiMin 2022.8.6.11.44.16
 */

package com.java.base.day.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Properties;

import static com.java.base.day.jdbc.util.JdbcUtil.closeConnect;
import static com.java.base.day.jdbc.util.JdbcUtil.getConnect;

/**
 * Created by IntelliJ IDEA.
 * connect pool 5种
 * @Author : 志敏.罗
 * @create 2022/8/6 23:44
 */
@SuppressWarnings({"all"})
public class JdbcConnectPool {


    /*
       传统连接问题分析
            1.传统的JDBC数据库连接使用DriverManager来获取，每次向数据库建立连接的时候都要将Connection加载到内存中，再验证IP地址，
            用户名和密码（0.05s~1s时间）。需要数据库连接的时候，就向数据库要求一个，频繁的进行数据库连接操作将占用很多的系统资源，容易造成服务器崩溃。
            2.每一次数据库连接，使用完后都得断开，如果程序出现异常而未能关闭，将导致数据库内存泄漏，最终将导致重启数据库。
            3.传统获取连接的方式，不能控制创建的连接数量，如连接过多，也可能导致内存泄漏，MySQL崩溃。
            4.解决传统开发中的数据库连接问题，可以采用数据库连接池技术

        数据库连接池基本介绍
            1.预先在缓冲池中放入一定数量的连接，当需要建立数据连接时，只需从"缓冲池"中取出一个，使用完毕后再放回去
            2.数据库连接池负责分配，管理和释放数据库连接，它允许应用程序重复使用一个现有的数据库连接，而不是重新建立一个
            3.当应用程序向连接池请求的连接数超过最大连接时，这些请求将被加入到等待队列中

        数据库连接池种类
            1.JDBC的数据库连接池使用javax.sql.DataSource来表示，DataSource只是一个接口，该接口通常由第三方提供实现【提供.jar】
            2.C3P0数据库连接池，速度相对较慢，稳定性不错（hibernate，spring）
            3.DBCP数据库连接池，速度相对c3p0较快，但不稳定
            4.Proxool数据库连接池，有监控连接池状态的功能，稳定性较c3p0差一点
            5.BoneCP数据库连接池，速度快
            6.Druid（德鲁伊）是阿里提供的数据库连接池，集DBCP、C3P0、Proxool 优点于一身的数据库连接池
     */


    /**
     * 正常代码连接  5k次
     */
    @Test
    @SneakyThrows
    void normalConnect(){
        long start = System.currentTimeMillis();
        System.out.println("开始连接...");
        for (int i = 0; i < 5000; i++) {
            Connection connect = getConnect();
            //去使用
            //如果不关闭连接就会导致 too many connections
            closeConnect(connect,null,null);
        }
        long end = System.currentTimeMillis();
        System.out.println("5k次耗时 "+(end-start)); //36260
    }


    //C3P0连接池使用

    /**
     * 方式一 相关参数 在程序中指定 user url password...
     */
    @Test
    @SneakyThrows
    void c3p0_01(){
        //创建一个一个数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //通过配置文件 mysql.properties 获取相关信息
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("src/main/resources/mysql.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //给数据源 ComboPooledDataSource 设置相关参数
        //连接管理是由 ComboPooledDataSource 来管理的
        comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("user"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        //初始化连接数量
        comboPooledDataSource.setInitialPoolSize(10);
        //设置最大连接数量
        comboPooledDataSource.setMaxPoolSize(50);
//        comboPooledDataSource.setBreakAfterAcquireFailure(false);
        //获取连接 这个方法就是从DataSource 接口实现的
        //测试连接池的速率 5k 要确保你的网络连接
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
//            System.out.println("连接ok");
            comboPooledDataSource.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0 连接mysql 耗时 "+(end-start));
    }


    /**
     * 配置文件完成
     */
    @Test
    @SneakyThrows
    void c3p0_02(){
        //使用配置文件  c3p0-config.xml 里面定义的 named-config name
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("dataBase");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
//            System.out.println("连接ok~~");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0 配置文件 连接mysql 耗时 "+(end-start));//463ms
    }
}
