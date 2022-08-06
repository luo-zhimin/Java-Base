/*
 * Copyright (c) luoZhiMin 2022.8.5.10.9.47
 */

package com.java.base.day.jdbc;

import com.java.base.day.jdbc.util.JdbcUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * jdbc select 返回的结果集
 * @Author : 志敏.罗
 * @create 2022/8/5 22:09
 */
public class JDBCResultSet {

    /*
        1.表示数据库结果集的数据表，通常通过执行查询数据库的语句生成
        2.ResultSet对象保持一个光标指向其当前的数据行。最初，光标位于第一行之前
        3.next方法将光标移动到下一行，并且由于在ResultSet对象中没有更多行时返回false，因此可以在while循环中使用循环来遍历结果集
     */
    @SneakyThrows
    public static void main(String[] args) {
        //获取连接
        Connection connect = JdbcUtil.getConnect();
        //得到连接
        Statement statement = connect.createStatement();
        //执行查询语句
        /*
            1	爆笑新闻	2022-07-29 22:51:50
            2	上海新闻热单发送	2022-07-29 23:04:34
            4	热点新闻	2022-08-05 22:05:46
            5	热带新闻	2022-08-05 22:05:46
            6	热点新闻一	2022-08-05 22:05:46
            7	热点新闻二	2022-08-05 22:05:46
            8	热点新闻三	2022-08-05 22:05:46
         */
        String queryNews = "select * from news";
        //执行给定的sql 返回单个 ResultSet对象
        /*
            debug 源码 ResultSet对象
            底层数据结构是ArrayList->对象数组 {rowData里面}
         */
        ResultSet resultSet = statement.executeQuery(queryNews);
        //使用while循环 取出数据
        while (resultSet.next()){//光标后移 没有的话返回false
            int id = resultSet.getInt(1);//获取该行的第一列数据
            String content = resultSet.getString(2);
            String sendTime = resultSet.getString(3);
            System.out.println(id+"\t"+content+"\t"+sendTime);
        }
        //关闭连接
        resultSet.close();
        statement.close();
        connect.close();
    }
}
