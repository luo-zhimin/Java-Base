/*
 * Copyright (c) luoZhiMin 2022.8.8.9.34.25
 */

package com.java.base.day.manhanbuilding.service;

import com.java.base.day.manhanbuilding.dao.BillDao;
import com.java.base.day.manhanbuilding.entry.Menu;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/8 21:34
 */
public class BillService {

    private static final BillDao billDao;
    private static final MenuService menuService;
    private static final DiningTableService diningTableService;

    static {
        billDao = new BillDao();
        menuService = new MenuService();
        diningTableService = new DiningTableService();
    }


    /**
     * 生成账单
     * @param menuId 菜单id
     * @param nums 菜名的数量
     * @param tableId 座位号
     * @return 是否成功
     */
    public Boolean saveBill(int menuId,int nums,int tableId){
        UUID uuid = UUID.randomUUID();
        //计算money
        Menu menu = menuService.getMenuById(menuId);
        if (menu==null){
            System.out.println("该菜单不存在~");
            return false;
        }
        double money = menu.getPrice()*nums;

        int save = billDao.update("insert into bill values (null,?,?,?,?,?,now(),?)",
                uuid.toString(), menuId, nums, money, tableId, "未结账");
        if (save<0){
            return false;
        }

        // 更新 餐桌 状态
        return diningTableService.updateState(tableId, "就餐中");
    }
}
