/*
 * Copyright (c) luoZhiMin 2022.8.8.9.31.43
 */

package com.java.base.day.manhanbuilding.service;

import com.java.base.day.manhanbuilding.dao.DiningTableDao;
import com.java.base.day.manhanbuilding.entry.Bill;
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

    private static final BillService billService;

    static {
        dingingTableDao = new DiningTableDao();
        billService = new BillService();
    }

    /**
     * @return 得到所有的餐桌
     */
    private List<DiningTable> getTables(){
        return dingingTableDao
                .queryMulti("select * from diningTable", DiningTable.class);
    }

    /**
     * 展示所有餐桌状态
     */
    public void getTableStates(){
        if (CollectionUtils.isNotEmpty(getTables())){
            System.out.println("餐桌编号"+"\t"+"餐桌状态");
            getTables().forEach(table->{
                System.out.println(table.getId()+"\t\t"+table.getState());
            });
        }
    }

    /**
     * 检查餐桌是否可以使用
     * @param tableId 餐桌编号
     * @return 是否可用
     */
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

    /**
     * 更新餐桌状态 -- 预定
     * @param id 餐桌id
     * @param name 预定人名字
     * @param tel 预定人电话
     * @return 是否更新成功
     */
    public boolean updateState(int id,String name,String tel){
        int update = dingingTableDao.update("update diningTable set state='已经预定' , orderName=?,orderTel=? where id=?",
                name, tel, id);
        return update > 0;
    }


    /**
     * 检查餐桌
     * @param tableId 桌子编号
     * @param state 1 点餐 2 结账
     * @return 是否可用
     */
    public boolean checkTable(int tableId,int state){
        DiningTable diningTable = dingingTableDao
                .querySingle("select * from diningTable where id=?", DiningTable.class, tableId);
        if (diningTable==null){
            System.out.println("=====该餐桌不存在～=====");
            return false;
        }
        switch (state){
            case 1:
                //就餐
                if (diningTable.getState().equals("空")) {
                    System.out.println("====该餐桌没有人在使用，请选择正确的餐桌～===");
                    return false;
                }
                break;
            case 2:
                //结账 是否有账单
                List<Bill> bills = billService.getBillsByTableId(tableId);
                if (CollectionUtils.isEmpty(bills)){
                    System.out.println("====该餐桌没有账单，请选择正确的餐桌～===");
                    return false;
                }
                break;
            default:
                //等待拓展
                break;
        }

        return true;
    }

    /**
     * 更新餐桌状态
     * @param id 餐桌id
     * @param state 状态
     * @return 是否更新成功
     */
    public boolean updateState(int id,String state){
        int update = dingingTableDao.update("update diningTable set state=? where id=?",
                state, id);
        return update > 0;
    }

    /**
     * 更新餐桌状态--初始化餐桌
     * @param id 餐桌id
     * @param state 状态
     * @return 是否更新成功
     */
    public Boolean updateTableState(int id,String state){
        int update = dingingTableDao.update("update diningTable set state=?,orderName='',orderTel='' where id=?",
                state, id);
        return update > 0;
    }
}
