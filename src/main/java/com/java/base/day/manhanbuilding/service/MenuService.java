/*
 * Copyright (c) luoZhiMin 2022.8.8.9.32.28
 */

package com.java.base.day.manhanbuilding.service;

import com.java.base.day.manhanbuilding.dao.MenuDao;
import com.java.base.day.manhanbuilding.entry.Menu;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/8 21:32
 */
public class MenuService {

    private static final MenuDao menuDao;

    static {
        menuDao = new MenuDao();
    }

    private List<Menu> getMenus(){
        return menuDao.queryMulti("select * from menu", Menu.class);
    }

    public void showMenus(){
        if (CollectionUtils.isNotEmpty(getMenus())){
            System.out.println("菜品编号"+"\t"+"菜品名"+"\t"+"类别"+"\t\t\t"+"价格");
            getMenus().forEach(menu -> {
                System.out.println(menu.getId()+"\t\t"+menu.getName()+"\t"+ menu.getType()+"\t\t"+ menu.getPrice());
            });
        }
    }


    public Menu getMenuById(int id){
        return menuDao.querySingle("select * from menu where id=?", Menu.class, id);
    }

    public Boolean checkMenu(int menuId){
        return getMenuById(menuId) == null;
    }
}
