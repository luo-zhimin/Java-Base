/*
 * Copyright (c) luoZhiMin 2022.8.5.3.3.25
 */

package com.java.base.day.jdbc;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 模拟JDBC 具体mysql厂商开发
 * @Author : 志敏.罗
 * @create 2022/7/24 19:20
 */
@SuppressWarnings({"all"})
public class BaseKnowJDBC implements JDBCInterface {

    /*
      JDBC为访问不同的数据库提供了统一的接口，为使用者屏蔽了细节问题。
      Java程序员使用JDBC，可以连接任何提供了JDBC驱动程序的数据库系统，从而完成对数据库的各种操作
      JDBC带来的好处：
        JDBC是Java提供一套用于数据库操作的接口API，Java 程序员只需要面向这套接口编程即可。不同的数据库厂商，需要针对这套接口，提供不同实现
     */

    /**
     * 连接
     */
    @Override
    public Object getConnection() {
        System.out.println("得到 mysql 连接.....");
        return null;
    }

    /**
     * curd
     */
    @Override
    public void curd() {
        System.out.println("完成 mysql curd.....");
    }

    /**
     * 关闭连接
     */
    @Override
    public void close() {
        System.out.println("关闭 mysql 连接.....");
    }


    /**
     * 测试模拟的jdbc
     */
    @Test
    void testJDBC(){
        JDBCInterface jdbcInterface = new BaseKnowJDBC();
        //接口实现 多态 动态绑定
        jdbcInterface.getConnection();
        jdbcInterface.curd();
        jdbcInterface.close();
        System.out.println("-----------");
        //oracle
        jdbcInterface = new OracleJDBC();
        jdbcInterface.getConnection();
        jdbcInterface.curd();
        jdbcInterface.close();
    }
}

/**
 * 模拟oracle 实现jdbc
 */
class OracleJDBC implements JDBCInterface{

    /**
     * 连接
     */
    @Override
    public Object getConnection() {
        System.out.println("得到 oracle 连接.....");
        return null;
    }

    /**
     * curd
     */
    @Override
    public void curd() {
        System.out.println("得到 oracle 连接.....");
    }

    /**
     * 关闭连接
     */
    @Override
    public void close() {
        System.out.println("得到 oracle 连接.....");
    }
}


/**
 * 我们规定的jdbc接口
 */
interface JDBCInterface {

    /**
     * 连接
     */
    Object getConnection();

    /**
     * curd
     */
    void curd();

    /**
     * 关闭连接
     */
    void close();
}
