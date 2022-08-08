/*
 * Copyright (c) luoZhiMin 2022.8.8.5.28.40
 */

package com.java.base.day.manhanbuilding.service;

import com.java.base.day.manhanbuilding.dao.EmployeeDao;
import com.java.base.day.manhanbuilding.entry.Employee;

/**
 * Created by IntelliJ IDEA.
 * 处理用户
 * @Author : 志敏.罗
 * @create 2022/8/8 17:28
 */
public class UserService {

    private static final EmployeeDao employeeDao;

    static {
        employeeDao = new EmployeeDao();
    }

    /**
     * 校验用户是否存在
     * @param userName 用户名
     * @param password 密码
     * @return 是否存在
     */
    public boolean checkUser(String userName,String password){
        Employee employee = employeeDao.
                querySingle("select * from employee where empId=? and pwd = md5(?)", Employee.class, userName, password);
        return employee!=null;
    }
}
