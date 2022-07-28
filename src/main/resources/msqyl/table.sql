-- 创建表
CREATE TABLE if NOT EXISTS `user`
(
    id BIGINT
(
    20
) NOT NULL ,
    name VARCHAR
(
    20
),
    password VARCHAR
(
    30
),
    PRIMARY KEY
(
    `id`
)
    ) ENGINE=INNODB;
-- CHARACTER set utf8 COLLATE utf8_bin
-- desc user;	

-- 数值型(整数)的基本使用
CREATE TABLE `t3`
(
    id TINYINT
);

-- 如果没有指定unsigned 就是有符号的
-- 有符号的范围 -128～127 没有符号 0～255
INSERT INTO t3
VALUES (-127);
INSERT INTO t3
VALUES (127);

-- Out of range value for column 'id' at row 1
-- INSERT INTO t3 VALUES(-129);
-- INSERT INTO t3 VALUES(128);

-- 创建无符号的 类型后面➕ UNSIGNED
CREATE TABLE `t4`
(
    id TINYINT UNSIGNED
);

INSERT INTO t4
VALUES (11);
-- 1Out of range value for column 'id' at row 1
INSERT INTO t4
VALUES (-1);
INSERT INTO t4
VALUES (256);

-- 数值型(bit)的使用
-- bit(N) N 在 1-64之间 0～8个字节
-- 显示按照 bit 显示 bit(8) 为一个字节 范围是0-255
-- 存储是二进制类型 按位
CREATE TABLE `t5`
(
    num bit(8)
);
INSERT INTO t5
VALUES (1);
INSERT INTO t5
VALUES (3);
INSERT INTO t5
VALUES (255); -- 11111111
SELECT *
from t5
WHERE num = 255;

-- DROP TABLE t3,t4,t5;
-- 数值型(小数)的基本使用
-- decimal 类型、float、double 使用

create table `t6`
(
    number1 FLOAT,
    number2 DOUBLE,
    number3 DECIMAL(30, 20)
);

INSERT INTO t6
VALUES (88.12345678912345, 88.12345678912345, 88.12345678912345);
-- DECIMAL 可以存放很大的数 DECIMAL(m,n) m最大65 n最大30
create table `t7`
(
    number1 DECIMAL(65, 30)
);

-- ALTER table t7 MODIFY number1 DECIMAL(65);
INSERT INTO t7
VALUES (8383838838383009338388383838383838383999999933338388388);
SELECT *
from t6,
     t7;

-- 字符串的基本使用
-- char varchar
-- char(size) 固定长度 255字符  varchar(size) 可变长度 最大65532字节 1～3个用于记录大小 65532可用字节
-- utf8 最大 21844个字符 和编码有关系

-- Column length too big for column 'name' (max = 255); use BLOB or TEXT instea
-- CREATE TABLE `t8`(
--  `name` CHAR(256)
-- );

CREATE TABLE `t8`
(
    `name1` CHAR(255),
--  Column length too big for column 'name2' (max = 21845); use BLOB or TEXT instead
--  `name2` VARCHAR(65532)

-- Row size too large. The maximum row size for the used table type, not counting BLOBs, is 65535. 
-- This includes storage overhead, check the manual. You have to change some columns to TEXT or BLOBs
--  `name2` VARCHAR(21845)
-- utf8的话 计算 size = (65535-3)/3 => 21844
    `name2` VARCHAR(21000)
)CHARSET utf8mb3;

-- char(4) VARCHAR(4) 这个4代表都是字符 VARCHAR 字符按照编码来存放 计算 
-- VARCHAR 不够用 可以使用 text mediumtext longtext

-- 日期类型
-- date , datetime , timestamp
CREATE TABLE `t9`
(
    time1 date,
    time2 datetime,
    time3 timestamp
);

-- ALTER table t9 MODIFY column time3 TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP;

SELECT DATE (time3) date, time3
from t9;

INSERT into t9(time1, time2)
VALUES ('2022-11-11', '2022-11-11 10:10:10');
UPDATE t9
set time1 = '2022-11-12';

SELECT *
from t9;

-- create table exercise
CREATE TABLE `emp`
(
    id         BIGINT ( 20 ),
    `name`     CHAR(32),
    sex        CHAR(1),
    brithday   date,
    enrty_date datetime,
    job        VARCHAR(500),
    salay      DOUBLE,
    resume     text
);

-- 修改表操作
-- 修改数据库名字
rename
table t6 to t10;
-- 修改字符集
-- ALTER TABLE emp character set utf8;
ALTER TABLE emp charset utf8;
-- 添加列
ALTER TABLE emp
    add column address varchar(50) DEFAULT null after resume;
-- 修改列
ALTER TABLE emp MODIFY COLUMN job VARCHAR (1000);
-- 删除列
ALTER TABLE emp DROP COLUMN sex;
/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

-- 查看
desc emp;
