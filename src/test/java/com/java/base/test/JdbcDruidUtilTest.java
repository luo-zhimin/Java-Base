/*
 * Copyright (c) luoZhiMin 2022.8.7.6.59.42
 */

package com.java.base.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.java.base.day.jdbc.util.JdbcDruidUtil.closeDruidConnection;
import static com.java.base.day.jdbc.util.JdbcDruidUtil.getDruidConnection;


/**
 * Created by IntelliJ IDEA.
 * druid util test
 * @Author : 志敏.罗
 * @create 2022/8/7 18:59
 */
public class JdbcDruidUtilTest {

    @Test
    @SneakyThrows
    void druidSelect(){
        Connection connect = getDruidConnection();
        System.out.println("运行类型 = "+connect.getClass());//class com.alibaba.druid.pool.DruidPooledConnection
        String select = "select * from news";
        PreparedStatement preparedStatement = connect.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String content = resultSet.getString(2);
            String sendTime = resultSet.getString(2);
            System.out.println(id+"\t"+content+"\t"+sendTime);
        }
        //close
        closeDruidConnection(connect,resultSet,preparedStatement);
    }

    @Test
    @SneakyThrows
    void druidDml(){
        Connection connect = getDruidConnection();
        String update = "update news set content=? where id=?";
        PreparedStatement preparedStatement = connect.prepareStatement(update);
        preparedStatement.setString(1,"druid新闻");
        preparedStatement.setInt(2,2);
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows>0?"successful":"fail");
        closeDruidConnection(connect,null,preparedStatement);
    }
}
