/*
 * Copyright (c) luoZhiMin 2022.8.5.4.19.59
 */

-- jdbc 使用
-- 创建测试表
create table `actor`(
    id int primary key auto_increment,
    name varchar(32) not null default '',
    sex char(1) not null default '女',
    born_date datetime,
    phone char(12)
) comment '演员表';

select VERSION();

select * from actor;