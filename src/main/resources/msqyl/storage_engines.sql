/*
 * Copyright (c) luoZhiMin 2022.8.4.11.14.42
 */

# mysql表类型和存储引擎

# 1.MySQL的表类型由存储引擎（Storage Engines）决定，主要包括MylSAM，innoDB、Memory等。
# 2.MySQL数据表主要支持六种类型，分别是∶CSV、Memory、ARCHIVE、MRG MYISAM、MYISAM、InnoBDB
# 3.这六种又分为两类
#     一类是"事务安全型"（transaction-safe），比如∶InnoDB；
#     其余都属于第二类，称为“非事务安全型”（non-transaction-safe）【mysiam和memory】
-- 查看存储引擎
show engines;

# 细节
# 1.MyISAM不支持事务、也不支持外键，但其访问速度快，对事务完整性没有要求
create table `myISAM_table`(
  id int,
  name varchar(32)
#   foreign key (id) references menu(id)
) engine myisam;
# 添加速度快 不支持外键 事务 表锁
start transaction ;
savepoint a;
insert into myISAM_table values (1,'jack');
-- 回退没有用 不支持事务
rollback to a;
select * from myISAM_table;

# 2.InnoDB存储引擎提供了具有提交、回滚和崩溃恢复能力的事务安全。但是比起MyISAM存储引擎，InnoDB写的处理效率差一些并且会占用更多的磁盘空间以保留数据和索引。
-- innodb 支持事务 行级锁 支持外键

# 3.MEMORY存储引擎使用存在内存中的内容来创建表。每个MEMORY表只实际对应一个磁盘文件。MEMORY类型的表访问非常得快，因为它的数据是放在内存中的，
# 并且默认使用HASH索引。但是一旦MySQL服务关闭，表中的数据就会丢失掉，表的结构还在
-- 数据在内存中 读写速度很快 读写开销小 IO 默认支持索引
create table `memory_table`
(
    id   int,
    name varchar(32)
) engine memory;
insert into memory_table values (1,'jack'),(2,'tom'),(3,'jerry');
select * from memory_table;

-- 如何选择表的存储引擎
# 如果你的应用不需要事务，处理的只是基本的CRUD操作，那么MylSAM是不二选择，速度快
# 如果需要支持事务，选择InnoDB。
# Memory 存储引擎就是将数据存储在内存中，由于没有磁盘I./O的等待，速度极快。但由于是内存存储引擎，所做的任何修改在服务器重启后都将消失。（经典用法用户的在线状态）

-- 修改存储引擎
alter table myISAM_table engine = myisam;