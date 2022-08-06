/*
 * Copyright (c) luoZhiMin 2022.8.5.10.49.12
 */

package com.java.base.day.jdbc;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static com.java.base.day.jdbc.util.JdbcUtil.getConnect;

/**
 * Created by IntelliJ IDEA.
 * statement 注入问题
 * sql 注入
 * @Author : 志敏.罗
 * @create 2022/8/5 22:49
 */
public class JdbcStatement {

    /*
        1.Statement对象用于执行静态SQL语句并返回其生成的结果的对象
        2.在连接建立后，需要对数据库进行访问，执行命名或是SQL语句，可以通过
            Statement【存在SQL注入】
            PreparedStatement【预处理】
            CallableStatement【存储过程】
        3.Statement对象执行SQL语句， 存在SQL注入风险
        4.SQL注入是利用某些系统没有对用户输入的数据进行充分的检查，而在用户输入数据中注入非法的SQL语句段或命令，恶意攻击数据库
        5.要防范SQL注入，只要用PreparedStatement（从Statement扩展而来）取代Statement就可以了
     */

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //获取连接
        Connection connect = getConnect();
        //得到连接 进行sql处理
        Statement statement = connect.createStatement();
        //用户输入用户名字
        System.out.print("请输入用户名：");
        String adminUser = scanner.nextLine();
        System.out.print("请输入密码：");
        String adminPassword = scanner.nextLine();
        // sql 注入
        String getAdmin = "select name,password from admin where " +
                "name ='" + adminUser + "'  and password = '" + adminPassword + "' ";
        //执行 得到结果集
        ResultSet resultSet = statement.executeQuery(getAdmin);
        if (resultSet.next()){
            System.out.println("login successful");
        }else {
            System.out.println("login fail");
        }
        //close
        resultSet.close();
        statement.close();
        connect.close();
    }
}
