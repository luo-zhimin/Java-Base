/*
 * Copyright (c) luoZhiMin 2022.8.6.10.31.26
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
 * jdbc batch
 * @Author : 志敏.罗
 * @create 2022/8/6 22:31
 */
public class JdbcBatch {

    /*
        1.当需要成批插入或者更新记录时。可以采用Java的批量更新机制，这一机制允许多条语句一次性提交给数据库批量处理。通常情况下比单独提交处理更有效率。
        2.JDBC的批量处理语句包括下面方法∶
            addBatch（）∶添加需要批量处理的SQL语句或参数
            executeBatch（）∶执行批量处理语句；
            clearBatch（）∶清空批处理包的语句
        3.JDBC连接MySQL时，如果要使用批处理功能，请再url中加参数?rewriteBatchedStatements=true
        4.批处理往往和PreparedStatement一起搭配使用，可以既减少编译次数，又减少运行次数，效率大大提高
     */

    //batch 5k条时间对比  (not use batch)751ms  (use batch)84ms

    @Test
    void notUseBatch(){
        Connection connect = getConnect();
        String insert = "insert into admin values (?,?)";
        PreparedStatement preparedStatement = null;
        long start = System.currentTimeMillis();
        try {
            preparedStatement = connect.prepareStatement(insert);
            for (int i = 0; i < 5000; i++) {
                preparedStatement.setString(1,"tom"+i);
                preparedStatement.setString(2,"666"+i);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(connect,null,preparedStatement);
        }
        long end = System.currentTimeMillis();
        System.out.println("no use batch = "+(end-start));//751ms
    }

    @Test
    void useBatch(){
        Connection connect = getConnect();
        String insert = "insert into admin values (?,?)";
        PreparedStatement preparedStatement = null;
        long start = System.currentTimeMillis();
        try {
            preparedStatement = connect.prepareStatement(insert);
            for (int i = 0; i < 5000; i++) {
                preparedStatement.setString(1,"Allen"+i);
                preparedStatement.setString(2,"666"+i);
                //将sql加入到批处理中 -- 1000执行一次
                /*
                    ClientPreparedStatement  mysql jar 5.1.37

                    //1.第一步创建一个ArrayList 集合 放入数据  ArrayList - elementData => Object[]
                    //2.ArrayList - elementData => Object[]
                    //3.当 elementData 满后,就按照 1.5 扩容
                    //4.当添加到指定的值后，就 executeBatch
                    //5.批量处理会减少我们发送 sql 语句的网络开销，而且减少编译次数，因此效率提高

                    public void addBatch() throws SQLException {
                        synchronized (this.checkClosed().getConnectionMutex()) {
                            if (this.batchedArgs == null) {
                                this.batchedArgs = new ArrayList();//创建一个ArrayList
                            }
                            for (int i = 0; i < this.parameterValues.length; ++i) {
                                this.checkAllParametersSet(this.parameterValues[i], this.parameterStreams[i], i);
                            }
                            this.batchedArgs.add(new PreparedStatement.BatchParams(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths, this.isNull));
                        }
                    }
                 */
                preparedStatement.addBatch();
                if ((i+1)%1000==0){
                    preparedStatement.executeBatch();
                    //执行完之后 清楚之前的sql 再次累计
                    preparedStatement.clearBatch();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(connect,null,preparedStatement);
        }
        long end = System.currentTimeMillis();
        System.out.println("use batch = "+(end-start));//84ms
    }
}
