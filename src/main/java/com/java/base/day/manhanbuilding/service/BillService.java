/*
 * Copyright (c) luoZhiMin 2022.8.8.9.34.25
 */

package com.java.base.day.manhanbuilding.service;

import com.java.base.day.manhanbuilding.dao.BillDao;
import com.java.base.day.manhanbuilding.dao.MultiResponseDao;
import com.java.base.day.manhanbuilding.entry.Bill;
import com.java.base.day.manhanbuilding.entry.Menu;
import com.java.base.day.manhanbuilding.entry.MultiResponse;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
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

    private static final MultiResponseDao responseDao;

    static {
        responseDao = new MultiResponseDao();
        billDao = new BillDao();
        menuService = new MenuService();
        diningTableService = new DiningTableService();
    }


    /**
     * 生成账单
     *
     * @param menuId  菜单id
     * @param nums    菜名的数量
     * @param tableId 座位号
     * @return 是否成功
     */
    public Boolean saveBill(int menuId, int nums, int tableId) {
        UUID uuid = UUID.randomUUID();
        //计算money
        Menu menu = menuService.getMenuById(menuId);
        if (menu == null) {
            System.out.println("该菜单不存在~");
            return false;
        }
        double money = menu.getPrice() * nums;

        int save = billDao.update("insert into bill values (null,?,?,?,?,?,now(),?)",
                uuid.toString(), menuId, nums, money, tableId, "未结账");
        if (save < 0) {
            return false;
        }

        // 更新 餐桌 状态
        return diningTableService.updateState(tableId, "就餐中");
    }

    /**
     * @return 得到所有的账单
     */
    private List<Bill> getBills() {
        return billDao.queryMulti("select * from bill", Bill.class);
    }

    /**
     * @param tableId 餐桌编号
     * @return 得到对应餐桌的账单
     */
    protected List<Bill> getBillsByTableId(int tableId) {
        return billDao.queryMulti("select * from bill where diningTableId=? and state=?", Bill.class,tableId,"未结账");
    }

    /**
     * 更新账单
     * @param tableId 桌号
     * @param state 状态
     * @return 是否成功
     */
    private Boolean updateBill(int tableId,String state){
        return billDao.update("update bill set state=? where diningTableId=? and state='未结账'",state,tableId)>0;
    }

    /**
     * 查看账单
     */
    public void showBills() {
        if (CollectionUtils.isNotEmpty(getBills())) {
            System.out.println("编号" + "\t\t" + "菜品号" + "\t" + "菜品量" + "\t" + "金额" + "\t\t"
                    + "桌号" + "\t\t" + "日期" + "\t\t\t\t\t\t" + "状态");
            getBills().forEach(bill -> {
                System.out.println(bill.getId() + "\t\t" + bill.getMenuId() + "\t\t" + bill.getNums() + "\t\t" + bill.getMoney()
                        + "\t" + bill.getDiningTableId() + "\t\t" + bill.getBillDate() + "\t\t" + bill.getState());
            });
        }
    }

    /**
     * 结账
     * @param tableId 餐桌编号
     * @param state 账单状态
     * @return 是否结账成功
     */
    public Boolean checkOut(int tableId,String state){
        List<Bill> bills = getBillsByTableId(tableId);
        if (CollectionUtils.isEmpty(bills)){
            System.out.println("该桌没有菜单～");
            return false;
        }
        // 更新 餐桌 和 账单
        if (updateBill(tableId,state)){
            return diningTableService.updateTableState(tableId, "空");
        }
        return false;
    }

    /**
     * @return 得到账单的具体信息
     */
    private List<MultiResponse> getBillResponse(){
        return responseDao.queryMulti("select b.id, menuId,name, nums, money, diningTableId," +
                " state from bill b inner join menu m on b.menuId=m.id", MultiResponse.class);
    }

    public void showBillResponse(){
        if (CollectionUtils.isNotEmpty(getBillResponse())){
            System.out.println("编号" + "\t\t" + "菜品号" + "\t" + "菜品名" + "\t" + "菜品量" + "\t" + "金额" + "\t\t"
                    + "桌号" + "\t\t" + "状态");
            getBillResponse().forEach(bill -> {
                System.out.println(bill.getId() + "\t\t" + bill.getMenuId() + "\t\t"+bill.getName()+ "\t\t" + bill.getNums() + "\t" + bill.getMoney()
                        + "\t" + bill.getDiningTableId() + "\t\t" + bill.getState());
            });
        }
    }
}
