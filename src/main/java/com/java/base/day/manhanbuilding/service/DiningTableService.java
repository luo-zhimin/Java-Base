/*
 * Copyright (c) luoZhiMin 2022.8.8.9.31.43
 */

package com.java.base.day.manhanbuilding.service;

import com.java.base.day.manhanbuilding.dao.DiningTableDao;
import com.java.base.day.manhanbuilding.entry.DiningTable;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/8/8 21:31
 */
public class DiningTableService {

    private static final DiningTableDao dingingTableDao;

    static {
        dingingTableDao = new DiningTableDao();
    }

    private List<DiningTable> getTables(){
        return dingingTableDao
                .queryMulti("select * from diningTable", DiningTable.class);
    }

    public void getTableStates(){
        if (CollectionUtils.isNotEmpty(getTables())){
            System.out.println("餐桌编号"+"\t"+"餐桌状态");
            getTables().forEach(table->{
                System.out.println(table.getId()+"\t\t"+table.getState());
            });
        }
    }

    public boolean checkTableIsUse(int tableId){
        DiningTable diningTable = dingingTableDao.querySingle("select * from diningTable where id=?", DiningTable.class, tableId);
        if (diningTable==null){
            System.out.println("=====该餐桌不存在～=====");
            return false;
        }
        if (!diningTable.getState().equals("空")) {
            System.out.println("该餐桌已经被人使用 对不起 请重新预定～～");
            return false;
        }
        return true;
    }

    public boolean updateState(int id,String name,String tel){
        int update = dingingTableDao.update("update diningTable set state='已经预定' , orderName=?,orderTel=? where id=?",
                name, tel, id);
        return update > 0;
    }


    public boolean checkTable(int tableId){
        DiningTable diningTable = dingingTableDao
                .querySingle("select * from diningTable where id=?", DiningTable.class, tableId);
        if (diningTable==null){
            System.out.println("=====该餐桌不存在～=====");
            return false;
        }
        //校验 就餐等...
        if (diningTable.getState().equals("空")) {
            System.out.println("====该餐桌没有人在使用，请选择正确的餐桌～===");
            return false;
        }
        return true;
    }

    public boolean updateState(int id,String state){
        int update = dingingTableDao.update("update diningTable set state=? where id=?",
                state, id);
        return update > 0;
    }
}
