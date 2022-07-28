/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

-- 数据库操作
-- 创建名称为java_test 数据库

-- 指令创建
-- CREATE DATABASE if not EXISTS java_test;

-- 指令删除数据库
-- DROP DATABASE java_test;

-- 创建一个使用utf8字符集的 java_test02
-- CHARACTER 设置字符集
-- CREATE DATABASE IF NOT EXISTS java_test02 CHARACTER SET utf8;

-- 创建一个使用utf8字符集的 java_test03 并且带校验规则

-- 大小写是否敏感 COLLATE
-- utf8_general_ci 不区分大小写
-- utf8_bin 区分大小写
-- CREATE DATABASE IF NOT EXISTS java_test03 CHARACTER SET utf8 COLLATE utf8_general_ci;

-- DROP DATABASE java_test02;

-- 查看 删除数据库
-- 显示所有的数据库
show
DATABASES;
-- 显示创建数据库的语句
show CREATE
DATABASE javaBase;
-- 删除数据库 慎用
-- IF EXISTS 如果存在
DROP
DATABASE IF EXISTS java_test02;

-- 备份恢复数据库
-- dos 窗口执行
-- DATABASE
-- mysqldump -uroot -p -B 数据库名字1 数据库名字2 > 路径/xx.sql
-- TABLE
-- mysqldump -uroot -p -B 数据库名字 表1 表2 > 路径/xx.sql

-- 进入 mysql 命令窗口执行
-- source 备份路径/xx.sql