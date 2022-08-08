/*
 * Copyright (c) luoZhiMin 2022.8.7.9.16.20
 */

package com.java.base.day.jdbc;

import com.java.base.day.jdbc.entry.Actor;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.java.base.day.jdbc.util.JdbcDruidUtil.closeDruidConnection;
import static com.java.base.day.jdbc.util.JdbcDruidUtil.getDruidConnection;

/**
 * Created by IntelliJ IDEA.
 * 优化 resultSet apache DBUtils
 * @Author : 志敏.罗
 * @create 2022/8/7 21:16
 */
@SuppressWarnings({"all"})
public class OptimizationResultSet {


    /**
     * 自己封装解决 封装到=> ArraryList
     */
    @Test
    @SneakyThrows
    List<Actor> testResutlSetToArray(){
        List<Actor> actors = new ArrayList<>();

        Connection connect = getDruidConnection();
        System.out.println("运行类型 = "+connect.getClass());//class com.alibaba.druid.pool.DruidPooledConnection
        String select = "select * from actor";
        PreparedStatement preparedStatement = connect.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            String bornDate = resultSet.getString("born_date");
            String phone = resultSet.getString("phone");
//            System.out.println(id+"\t"+name+"\t"+sex+"\t"+bornDate+"\t"+phone);
            //把得到的记录封装到一个actor对象里面 在放入到List里面
            Actor actor = new Actor(id, name, sex, bornDate, phone);
            actors.add(actor);
        }
        System.out.println("actors = "+actors);
        //close
        closeDruidConnection(connect,resultSet,preparedStatement);
        //ArrayList 和 connection 没有关系 所以关闭之后也没有关系 因为数据都到了集合中
        return actors;
    }

    /*
        commons-dbutils是Apache组织提供的一个开源JDBC工具类库，它是对JDBC的封装，使用dbutils能极大简化jdbc编码的工作量
        DbUtils类
            QueryRunner 类该类封装了SQL的执行，是线程安全的。可以实现增、删、改、查、批处理
            使用QueryRunner类实现查询
            ResultSetHandler 接口∶该接口用于处理java.sql.ResultSet，将数据按要求转换为另一种形式
                ArrayHandler∶把结果集中的第一行数据转成对象数组。
                ArrayListHandler∶把结果集中的每一行数据都转成一个数组，再存放到List中。
                BeanHandler∶将结果集中的第一行数据封装到一个对应的JavaBean实例中。
                BeanListHandler∶将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
                ColumnListHandler∶将结果集中某一列的数据存放到List中。
                KeyedHandler（mame）∶将结果集中的每行数据都封装到Map里，再把这些map再存到一个map里，其key为指定的key，
                MapHandler∶将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
                MapListHandler∶将结果集中的每一行数据都封装到一个Map里，然后再存放到List
     */

    /**
     * 返回List apche dbUtils + druid
     */
    @Test
    @SneakyThrows
    void testApacheUtiilsSelectAll(){

        Connection connect = getDruidConnection();
        //使用DBUtils 类和接口 加入相关依赖 pom.xml
        //创建一个queryRunner
        QueryRunner queryRunner = new QueryRunner();
        String select = "select * from actor";
        //可以执行相关查询
        //query 执行查询 得到resultSet 封装到ArrayList 返回集合
        //参数 1 连接 2 sql 3 new BeanListHandler<>(Actor.class) 将resultSet->Actor->ArrayList (底层是reflection)
        //底层 会在query 关闭 resultSet preparedStatement
        List<Actor> actors = queryRunner
                .query(connect, select, new BeanListHandler<>(Actor.class));
        System.out.println(actors);
        //释放资源
        closeDruidConnection(connect,null,null);
        /*
            queryRunner.query() 源码
            stmt = this.prepareStatement(conn, sql); 得到preparedStatement
            this.fillStatement(stmt, params); 执行预处理
            rs = this.wrap(stmt.executeQuery()); 执行语句得到结果
            result = rsh.handle(rs); 返回的resultSet 封装到 Object 里面 反射 传入的class对象处理

            //关闭连接
             finally {
                try {
                    close(rs);
                } finally {
                    close(stmt);
                    if (closeConn) {
                        close(conn);
                    }
                }
            }
         */
    }

    /**
     * 单个对象
     */
    @Test
    @SneakyThrows
    void testApacheUtiilsSelectSingle(){

        Connection connect = getDruidConnection();
        //使用DBUtils 类和接口 加入相关依赖 pom.xml
        //创建一个queryRunner
        QueryRunner queryRunner = new QueryRunner();
        String select = "select * from actor where id=?";
        //可以执行相关查询
        //new BeanHandler<>(Actor.class) 单个对象
        Actor actor = queryRunner
                .query(connect, select, new BeanHandler<>(Actor.class),1);
        System.out.println(actor);
    }

    /**
     * 返回 单行单列 对象
     */
    @Test
    @SneakyThrows
    void testApacheUtiilsSelectScalar(){

        Connection connect = getDruidConnection();
        //使用DBUtils 类和接口 加入相关依赖 pom.xml
        //创建一个queryRunner
        QueryRunner queryRunner = new QueryRunner();
        //返回单行单列 对象 object
        String select = "select name from actor where id=?";
        //可以执行相关查询
        //new ScalarHandler<>() 单个对象
        Object actor = queryRunner
                .query(connect, select, new ScalarHandler<>(),1);
        System.out.println(actor);
    }

    /**
     * apache dbutils + druid --> dml{insert-update-delete}
     */
    @Test
    @SneakyThrows
    void testApacheUtiilsDml(){

        Connection connect = getDruidConnection();
        //使用DBUtils 类和接口 加入相关依赖 pom.xml
        //创建一个queryRunner
        QueryRunner queryRunner = new QueryRunner();
        String insert = "insert into actor values (null,?,?,date(now()),?)";
        String update = "update actor set born_date=? where id=?";
        String delete = "delete from actor where id=?";
        //可以执行相关语句
        //返回受影响的行数
        //insert
//        int rows = queryRunner
//                .update(connect, insert, "关晓彤", "女", "1100");
        //update
        int rows = queryRunner
                .update(connect, update, "1997-9-17",2);
        //delete
//        int rows = queryRunner
//                .update(connect, delete, 2);
        System.out.println(rows>0?"successful":"fail");
    }
}
