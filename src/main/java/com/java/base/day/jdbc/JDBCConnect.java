/*
 * Copyright (c) luoZhiMin 2022.8.5.5.45.31
 */

package com.java.base.day.jdbc;

import com.mysql.jdbc.Driver;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * jdbc 数据库连接方式 5 种
 * @Author : 志敏.罗
 * @create 2022/8/5 17:45
 */
@SuppressWarnings({"all"})
public class JDBCConnect {

    /*
        获取数据库连接 5 种方式
     */

    /**
     * 第一种
     *  获取Driver实现类对象
     */
    @Test
     void firstConnect() throws SQLException {
        Driver driver = new Driver();
        sayDriver(driver);
    }

    /**
     * 反射
     * 动态加载 - 减少了依赖性
     */
    @Test
    @SneakyThrows
    void secondConnect(){
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        //获取实例对象
        Driver driver = (Driver) aClass.newInstance();
        sayDriver(driver);
    }


    /**
     * DriverMange replace Driver
     */
    @Test
    @SneakyThrows
    void thirdConnect(){
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        //获取实例对象
        Driver driver = (Driver) aClass.newInstance();
        String url = "jdbc:mysql://localhost:3306/javaBase";
        String user = "root";
        String password = "2020.0.l";
        //注册驱动
        DriverManager.registerDriver(driver);
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("DriverMange "+connection);
    }

    /**
     * 自动完成注册驱动 简化代码 -->反射
     * 用的最多的
     */
    @Test
    @SneakyThrows
    void fourthConnect(){
        //反射自动注册
        //在加载Driver类的时 完成注册

        /*
            com.mysql.cj.jdbc.Driver
            源码：
                1.静态代码块 类加载时候 进行加载一次
                2.因此注册Driver工作已经完成  java.sql.DriverManager.registerDriver(new Driver());
            // Register ourselves with the DriverManager
            static {
                try {
                    java.sql.DriverManager.registerDriver(new Driver());
                } catch (SQLException E) {
                    throw new RuntimeException("Can't register driver!");
                }
            }
         */
        //在mysql jar包 5.1.6以上可以不显示写 反射获取 源码自动获取
//        /mysql-connector-java-8.0.29.jar!/META-INF/services/java.sql.Driver 里面有其对应的加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //可以加载配置文件 loadProperties
        String url = "jdbc:mysql://localhost:3306/javaBase";
        String user = "root";
        String password = "2020.0.l";
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("fourth "+connection);
    }

    /**
     * 第五种
     * 在第四种上面直接文件加载 灵活
     */
    @Test
    @SneakyThrows
    void fifthConnect(){
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("src/main/resources/mysql.properties")));
//        properties.list(System.out);
        //加载驱动
        Class.forName(properties.getProperty("driver"));

        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        System.out.println("file read connect "+connection);
    }


    private void sayDriver(Driver driver) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/javaBase";
        //用户名+密码 放入properties对象中
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","2020.0.l");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }
}
@SuppressWarnings({"all"})
class JDBCExercise{
    @Test
    @SneakyThrows
    void fifthConnect(){
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("src/main/resources/mysql.properties")));
//        properties.list(System.out);
        //加载驱动
        Class.forName(properties.getProperty("driver"));

        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        //创建 statement 执行sql语句
        Statement statement = connection.createStatement();
        String insertNews = "insert into news values (4, '热点新闻',now()),(5, '热带新闻',now()),(6, '热点新闻一',now()),(7, '热点新闻二',now()),(8, '热点新闻三',now())";
        String updateNews = "update news set content='上海新闻热单发送' where id=2";
        String deleteNews = "delete from news where id=3";
        int i = statement.executeUpdate(insertNews);
        int ui = statement.executeUpdate(updateNews);
        int di = statement.executeUpdate(deleteNews);
        System.out.println(i > 0 && ui > 0 && di > 0 ? "successful" : "fail");

        //close
        statement.close();
        connection.close();
    }
}
