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

insert into news values (4, '热点新闻',now()),(5, '热带新闻',now()),(6, '热点新闻一',now()),(7, '热点新闻二',now()),(8, '热点新闻三',now());
update news set content='上海新闻热单发送' where id=2;
delete from news where id=3;
select * from news;

-- sql 注入
create table `admin`(
  name varchar(32) not null unique ,
  password varchar(32) not null default ''
);

-- 添加测试数据
insert into admin values ('tom','123');

-- normal find
select * from admin where name='tom' and password='123';

-- sql 注入
-- 输入用户名 1' or  密码 or '1'='1 万能密码 'true的条件'
-- 后面输入一个true的条件 前面 or 拼接
select * from admin where name='1' or' and password=' or '1'='1';

select * from admin;
