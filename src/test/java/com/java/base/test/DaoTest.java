/*
 * Copyright (c) luoZhiMin 2022.8.8.11.33.4
 */

package com.java.base.test;

import com.java.base.day.jdbc.dao.ActorDao;
import com.java.base.day.jdbc.dao.GoodsDao;
import com.java.base.day.jdbc.dao.OrderDao;
import com.java.base.day.jdbc.entry.Actor;
import com.java.base.day.jdbc.entry.Goods;
import com.java.base.day.jdbc.entry.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * test Dao
 * @Author : 志敏.罗
 * @create 2022/8/8 11:33
 */
public class DaoTest {

    private static final ActorDao actorDao;
    private static final GoodsDao goodsDao;
    private static final OrderDao orderDao;

    static {
        actorDao = new ActorDao();
        goodsDao = new GoodsDao();
        orderDao = new OrderDao();
    }

    @Test
    void testActorDao(){
        List<Actor> actors = actorDao.
                queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("actors "+actors);

        //单行记录
        Actor actor = actorDao.
                querySingle("select * from actor where id = ?", Actor.class, 1);
        System.out.println("actor "+actor);

        //单行单列
        Object o = actorDao.queryScalar("select name from actor where id=?", 1);
        System.out.println("name = "+o);

        //insert 1987 年 8 月 25 日
        int update = actorDao.update("insert into actor values (null,?,?,?,?)", "刘亦菲", "女", "1987-8-25", "10086");
        System.out.println(update > 0 ? "successful" : "fail");
    }

    @Test
    void testGoodsDao(){
        List<Goods> goods = goodsDao.
                queryMulti("select * from goods where goods_id >= ?", Goods.class, 1);
        System.out.println(goods);

        //单行记录
        Goods good = goodsDao.
                querySingle("select * from goods where goods_id = ?", Goods.class, 1);
        System.out.println(good);

        //单行单列
        Object o = goodsDao.queryScalar("select goods_name from goods where goods_id=?", 1);
        System.out.println("name = "+o);
    }

    @Test
    void testOrderDao(){
        List<Order> orders = orderDao.
                queryMulti("select * from `order` where id >= ?", Order.class, 1);
        System.out.println(orders);

        //单行记录
        Order order = orderDao.
                querySingle("select * from `order` where id = ?", Order.class, 1);
        System.out.println(order);

        //单行单列
        Object o = orderDao.queryScalar("select goods_name from `order` where id=?", 1);
        System.out.println("name = "+o);
    }
}
