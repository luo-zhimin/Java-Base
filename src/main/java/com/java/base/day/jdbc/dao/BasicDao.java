/*
 * Copyright (c) luoZhiMin 2022.8.8.10.35.14
 */

package com.java.base.day.jdbc.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.java.base.day.jdbc.util.JdbcDruidUtil.closeDruidConnection;
import static com.java.base.day.jdbc.util.JdbcDruidUtil.getDruidConnection;

/**
 * Created by IntelliJ IDEA.
 * 基类 T 指定具体的类型
 * @Author : 志敏.罗
 * @create 2022/8/8 10:35
 */
public class BasicDao<T> {

    /*
        DAO∶ data access object
        数据访问对象这样的通用类，称为BasicDao，是专门和数据库交互的，即完成对数据库（表）的crud操作。
        在BasicDao的基础上，实现一张表对应一个Dao，更好的完成功能，比如Customer表-Customer.java类（javabean）-CustomerDao.java
     */

//    private final static Connection connection;
    private final static QueryRunner queryRunner;

    static {
//        connection = getDruidConnection();
        queryRunner = new QueryRunner();
    }


    //通用方法 封装

    /**
     * update
     * @param sql 可执行sql
     * @param params 可变参数
     * @return 受影响的行
     */
    public int update(String sql, Object... params){
        Connection connection = getDruidConnection();
        int update;
        try {
            update = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //close
        closeDruidConnection(connection,null,null);
        return update;
    }

    /**
     * 返回多个对象(即查询的结果是多行),针对任意表
     * @param query 查询的语句
     * @param clazz 传入一个class对象
     * @param params 可变参数对象-占位符号的值
     * @return 对象集合
     */
    public List<T> queryMulti(String query,Class<T> clazz,Object... params){
        List<T> result;
        Connection connection = getDruidConnection();
        try {
            result = queryRunner.query(connection,query,new BeanListHandler<>(clazz),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeDruidConnection(connection,null,null);
        return result;
    }

    /**
     * @param query 查询的语句
     * @param clazz 传入一个class对象
     * @param params 可变参数对象-占位符号的值
     * @return 返回单行对象
     */
    public T querySingle(String query,Class<T> clazz,Object... params){
        Connection connection = getDruidConnection();
        T t;
        try {
            t = queryRunner.query(connection,query,new BeanHandler<>(clazz),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeDruidConnection(connection,null,null);
        return t;
    }

    /**
     * @param query 查询的语句
     * @param params 可变参数对象-占位符号的值
     * @return 返回单行单列对象(单值) -- (String，int.....)
     */
    public Object queryScalar(String query,Object... params){
        Connection connection = getDruidConnection();
        Object t;
        try {
            t = queryRunner.query(connection,query,new ScalarHandler<>(),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeDruidConnection(connection,null,null);
        return t;
    }
}
