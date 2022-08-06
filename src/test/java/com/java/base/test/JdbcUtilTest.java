/*
 * Copyright (c) luoZhiMin 2022.8.6.9.25.53
 */

package com.java.base.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.java.base.day.jdbc.util.JdbcUtil.closeConnect;
import static com.java.base.day.jdbc.util.JdbcUtil.getConnect;

/**
 * Created by IntelliJ IDEA.
 * 测试 jdbc util方法
 * @Author : 志敏.罗
 * @create 2022/8/6 21:25
 */
public class JdbcUtilTest {

    @Test
    @SneakyThrows
    void utilSelectTest(){
        //获取连接
        Connection connect = getConnect();
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
        closeConnect(connect,resultSet,preparedStatement);
    }

    /**
     * update insert delete
     * ->use update
     */
    @Test
    @SneakyThrows
    void utilDmlTest(){
        Connection connect = getConnect();
        String update = "update news set content=? where id=?";
        PreparedStatement preparedStatement = connect.prepareStatement(update);
        preparedStatement.setString(1,"军事新闻");
        preparedStatement.setInt(2,2);
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows>0?"successful":"fail");
        closeConnect(connect,null,preparedStatement);
    }
}
