/*
 * Copyright (c) luoZhiMin 2022.8.6.2.4.47
 */

package com.java.base.day.jdbc;

import com.java.base.day.jdbc.util.JdbcUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * jdbc sql 预处理
 * @Author : 志敏.罗
 * @create 2022/8/6 14:04
 */
//@SuppressWarnings({"all"})
public class JdbcPreparedStatement {

    /*
       PreparedStatement
            1.PreparedStatement执行的SQL语句中的参数用问号（?）来表示，调用
              PreparedStatement 对象的setXxx（）方法来设置这些参数.setXxx（）方法有两个参数，
              第一个参数是要设置的SQL语句中的参数的索引（从1开始），第二个是设置的SQL语句中的参数的值
            2.调用executeQuery（），返回ResultSet对象
            3.调用executeUpdate（）执行更新，包括增、删、修改

        预处理的好处
            1.不再使用+拼接sql语句，减少语法错误
            2.有效的解决了sql注入问题!
            3.大大减少了编译次数，效率较高
     */

    @SneakyThrows
    public static void preparedStatementSelect() {
        String[] users = getUserNameAndPassword();
        //组装sql ?==>占位符号
        String sql = "select name,password from admin where name=? and password=? ";
        //获取连接
        Connection connect = JdbcUtil.getConnect();
        //得到preparedStatement 连接  进行sql处理
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        //给？号赋值
        preparedStatement.setString(1,users[0]);
        preparedStatement.setString(2,users[1]);

        //执行查询时候 不需要在写sql
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("login successful");
        }else {
            System.out.println("login fail");
        }
        //close
        resultSet.close();
        preparedStatement.close();
        connect.close();
    }

    @SneakyThrows
    public static void preparedStatementDml() {
        String[] users = getUserNameAndPassword();
        //组装sql ?==>占位符号
//        String insert = "insert into admin values (?,?)";
//        String delete = "delete from admin where name =? and password = ? ";
        String update = "update admin set password = ? where name=? ";
        //获取连接
        Connection connect = JdbcUtil.getConnect();
        //得到preparedStatement 连接  进行sql处理
        PreparedStatement preparedStatement = connect.prepareStatement(update);
        //给？号赋值
        //insert delete
//        preparedStatement.setString(1,adminUser);
//        preparedStatement.setString(2,adminPassword);
        //update
        preparedStatement.setString(1,users[1]);
        preparedStatement.setString(2,users[0]);

        //执行查询时候 不需要在写sql
        int i = preparedStatement.executeUpdate();
        System.out.println(i>0?"successful":"fail");
        //close
        preparedStatement.close();
        connect.close();
    }

    protected static String[] getUserNameAndPassword(){
        Scanner scanner = new Scanner(System.in);
        //用户输入用户名字
        System.out.print("请输入用户名：");
        String adminUser = scanner.nextLine();
        System.out.print("请输入密码：");
        String adminPassword = scanner.nextLine();
        return new String[]{adminUser,adminPassword};
    }


    public static void main(String[] args) {
        //查询
        preparedStatementSelect();
        //dml
        preparedStatementDml();
    }
}
class JdbcPreparedStatementExercise{

    /*
        使用PreparedStatement添加5条数据
        修改 tom 的记录，将name 改成 king
        删除一条的记录
        查询全部记录，并显示在控制台
     */

    @Test
    @SneakyThrows
    public static void main(String[] args) {
        Connection connect = JdbcUtil.getConnect();
        //可以循环添加 输入 scanner 使用 getUserNameAndPassword
        PreparedStatement preparedStatement = null;
        String[] users;
        // insert
//        for (int i = 0; i < 5; i++) {
//            users = JdbcPreparedStatement.getUserNameAndPassword();
//            String insert = "insert into admin values (?,?)";
//            preparedStatement = execute(connect,users,insert);
//        }

        //update
        users = JdbcPreparedStatement.getUserNameAndPassword();
        String update = "update admin set name =? where name =?";
//        preparedStatement = execute(connect,users,update);
        //delete
        String delete ="delete from admin where name= ? and password=?";
//        preparedStatement = execute(connect,users,delete);

        //select

        String select = "select * from admin";
        preparedStatement = connect.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String password = resultSet.getString(2);
            System.out.println(name+"\t"+password);
        }

        //close
        resultSet.close();
        preparedStatement.close();
        connect.close();
    }

    @SneakyThrows
    private static PreparedStatement execute(Connection connect,
                                String[] users,
                                String sql){
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1,users[0]);
        preparedStatement.setString(2,users[1]);
        int row = preparedStatement.executeUpdate();
        System.out.println(row>0 ? "successful":"fail");
        return preparedStatement;
    }
}
