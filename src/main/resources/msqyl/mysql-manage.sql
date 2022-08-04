/*
 * Copyright (c) luoZhiMin 2022.8.4.5.28.19
 */

-- mysql 管理
-- mysql 用户
select user,host,authentication_string from user;

# 1.host: 可以指定ip地址，比如∶127.0.0.1
# 2.user: 用户名；
# 3.authentication string∶密码，是通过mysql的password（）函数加密之后的密码。
-- 创建用户
# create user '用户名@允许登录位置'  identified by  '密码’ 说明创建用户，同时指定密码
create user test@localhost identified by '123456';
-- 删除用户
# drop user '用户名'@'允许登录位置';
drop user test@127.0.0.1;
-- 修改密码
-- 低版本
# 修改自己的密码∶
set password = password ('密码');
# 修改他人的密码（需要有修改用户密码权限）∶
set password for '用户名'@'登录位置'= password ('密码');
-- 8.0
# alter user '用户名'@'允许登录位置' identified by '密码';
alter user 'test'@'localhost' identified by '123456';

-- mysql中的权限
-- 给用户授权
# 基本语法∶
# grant 权限列表 on 库.对象名 to '用户名'@'登录位置'【identified by  '密码'  】
# 说明
# 1，权限列表，多个权限用逗号分开
# grant select on …
# grant select,delete,create on ……
# grant all [privileges] on .... -- 表示赋予该用户在该对象上的所有权限
# 2.特别说明
# *.* ∶代表本系统中的所有数据库的所有对象（表，视图，存储过程）
# 库.* ∶ 表示某个数据库中的所有数据对象（表，视图，存储过程等）
# 3.identified by可以省略，也可以写出.
# （1）如果用户存在，就是修改该用户的密码。
# （2）如果该用户不存在，就是创建该用户!

-- 回收用户授权
# revoke 权限列表 on 库对象名 from '用户名"@"登录位置'；

-- 权限生效指令
flush privileges; -- 刷新权限

-- exercise
# 1.创建一个用户（你的名字，拼音），密码  123，并且只可以从本地登录，不让远程登录mysql
create user 'bd'@'localhost' identified by '123';
# 2.创建库和表 test_db 下的 news 表 ，要求∶使用root 用户创建
create database test_bd;
create table test_bd.`news`(
    id int,
    name varchar(32),
    content varchar(255)
)comment 'news';
# 3.给用户分配查看 news 表和添加数据的权限
grant select,insert on test_bd.news to 'test'@'localhost';
-- 增加权限
grant update,create view,alter on test_bd.news to test@localhost;

# 4.测试看看用户是否只有这几个权限
# 5.修改密码为 abc ，要求∶使用root用户完成
alter user 'test'@'localhost' identified by 'abc';
# 6.重新登录

-- 回收权限
revoke update,alter,create on test_bd.news from test@localhost;

# 7.使用 root 用户删除你的用户
drop user test@localhost;

-- 查看权限
show grants for test@localhost;

-- 注意细节
# 1.在创建用户的时候，如果不指定Host，则为%，%表示表示所有IP都有连接权限
create user jack;
# 2.也可以这样指定
create user 'john'@'192.168.1.%'; -- 表示 xxx用户在192.168.1.*的ip可以登录mysql
# 3.在删除用户的时候，如果host不是%，需要明确指定"用户"@'host值'
drop user 'john'@'192.168.1.%';

select user,Host from user;