/*
 * Copyright (c) luoZhiMin 2022.8.8.11.53.51
 */

package com.java.base.test;

import com.java.base.day.manhanbuilding.dao.BillDao;
import com.java.base.day.manhanbuilding.dao.MenuDao;
import com.java.base.day.manhanbuilding.entry.Bill;
import com.java.base.day.manhanbuilding.entry.Menu;
import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/8 23:53
 */
public class TestService {

    BillDao billDao = new BillDao();
    MenuDao menuDao = new MenuDao();

    @Test
    void test(){
        System.out.println(billDao.queryMulti("select * from bill", Bill.class));
        System.out.println(menuDao.queryMulti("select * from menu", Menu.class));
    }
}
