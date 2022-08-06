/*
 * Copyright (c) luoZhiMin 2022.8.6.9.47.28
 */

package com.java.base.day.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.java.base.day.jdbc.util.JdbcUtil.closeConnect;
import static com.java.base.day.jdbc.util.JdbcUtil.getConnect;

/**
 * Created by IntelliJ IDEA.
 * jdbc transaction
 * @Author : 志敏.罗
 * @create 2022/8/6 21:47
 */
@SuppressWarnings({"all"})
public class JdbcTransaction {

    /*
        1.JDBC程序中当一个Connection对象创建时，默认情况下是自动提交事务∶每次执行一个SQL语句时，如果执行成功，就会向数据库自动提交，而不能回滚。
        2.JDBC程序中为了让多个SQL语句作为一个整体执行，需要使用事务
        3.调用Connection的setAutoCommit（false）可以取消自动提交事务
        4.在所有的SQL语句都成功执行后，调用Connection的commit（）；方法提交事务
        5.在其中某个操作失败或出现异常时，调用Connection的rollback（）；方法回滚事务
     */

    //进行对比 模拟转账业务 事务/没有事务

    @Test
    void noTransaction() {
        //模拟转账业务
        Connection connect = getConnect();
        String update = "update account set money=money-100 where id=3";
        String update1 = "update account set money=money+100 where id=1";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(update);//默认情况下 事务自动提交
            //执行第一条
            preparedStatement.executeUpdate();
            //制造异常
            int i = 1 / 0;
            preparedStatement = connect.prepareStatement(update1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(connect, null, preparedStatement);
        }
    }


    @Test
    void useTransaction() {
        //模拟转账业务
        Connection connect = getConnect();
        String update = "update account set money=money-100 where id=3";
        String update1 = "update account set money=money+100 where id=1";
        PreparedStatement preparedStatement = null;
        try {
            //设置事务 手动提交
            connect.setAutoCommit(false);
            preparedStatement = connect.prepareStatement(update);//默认情况下 事务自动提交
            //执行第一条
            preparedStatement.executeUpdate();
            //int i = 1 / 0;
            preparedStatement = connect.prepareStatement(update1);
            preparedStatement.executeUpdate();

            //提交事务
            connect.commit();

        } catch (SQLException e) {
            //这里可以进行回滚 撤销执行的sql
            try {
                System.out.println("发生了异常 撤销执行的sql");
                connect.rollback();//可以设置 savepoint 默认回到最初点
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            closeConnect(connect, null, preparedStatement);
        }
    }
}
