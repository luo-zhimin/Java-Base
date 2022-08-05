/*
 * Copyright (c) luoZhiMin 2022.8.5.4.11.58
 */

package com.java.base.day.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * 入门
 * @Author : 志敏.罗
 * @create 2022/8/5 16:11
 */
public class JDBCIntroduction {

    /**
     * jdbc入门
     * 编写步骤
     *  1.注册驱动-加载Driver类
     *  2.获取连接-得到Connection
     *  3.执行增删改查-发送SQL给mysql执行
     *  4.释放资源-关闭相关连接
     *
     * 前置工作
     *  下载对应得驱动
     *  在你项目的目录下 新建一个文件 把jar包放进去 在把目录加入到项目中->右键->添加为库
     *  驱动尽量和你本地的mysql版本对应
     *
     *  dml{update insert delete}
     */
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        Driver driver = new Driver();//创建driver对象

        //2.获取连接
        //jdbc:mysql:// 表示协议 通过jdbc的方式连接mysql
        //localhost  主机 表示ip地址
        //3306 是mysql监听的端口
        //javaBase 表示连接到哪个数据库
        //连接本质底层就是socket
        String url = "jdbc:mysql://localhost:3306/javaBase";
        //用户名+密码 放入properties对象中
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","2020.0.l");

        //返回连接
        Connection connect = driver.connect(url, properties);
        //3.执行增删改查
        String insertActor ="insert into actor values(null,'胡歌','男','1986-11-01','1111')";
        String updateActor ="update actor set born_date='1982-09-20' where id=1";
        String deleteActor ="delete from actor where id=1";
        //创建一个Statement 可以帮助我们执行静态的sql语句 并返回结果
        Statement statement = connect.createStatement();
        //dml 语句 返回的就是影响的行数
        int i = statement.executeUpdate(updateActor);
        System.out.println(i>0?"successful":"fail");
        //4.释放资源
        statement.close();
        connect.close();
    }
}
