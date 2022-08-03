/*
 * Copyright (c) luoZhiMin 2022.8.3.6.19.12
 */

-- mysql事务
# 事务用于保证数据的一致性，它由一组相关的dml语句组成，该组的dml语句要么全部成功，要么全部失败。如∶转账就要用事务来处理，用以保证数据的一致性。

-- 事务和锁
# 当执行事务操作时（dml语句），mysql会在表上加锁，防止其它用户改表的数据. 这对用户来讲是非常重要的
# mysql数据库控制台事务的几个重要操作
# start transaction --开始一个事务
# savepoint 保存点名 -- 设置保存点
# rollback to 保存点名 -- 回退事务
# rollback -- 回退全部事务
# commit -- 提交事务，所有的操作生效，不能回退

-- 创建一张测试表
create table `transaction_table`(
  id int primary key ,
  name varchar(32)
);

-- 开始一个事务
start transaction;
-- 设置一个保存点
savepoint A;
-- 执行dml操作
insert into transaction_table values (1,'tom');
-- 设置第二个保存点
savepoint B;
insert into transaction_table values (2,'jack');
-- 回退到b
rollback to B;
-- 继续回退
rollback to A;
-- 回退到事务开始的状态
rollback;
commit;
select * from transaction_table;

-- 回退事务
# savepoint 保存点是事务中的点.用于取消部分事务，
# 当结束事务时（commit），会自动的删除该事务所定义的所有保存点.
# 当执行回退事务时，通过指定保存点可以回退到指定的点
-- 提交事务
# 使用commit语句可以提交事务.当执行了commit语句子后，会确认事务的变化、结束事务、删除保存点、释放锁， 数据生效。当使用commit语句结束事务子后
# 其他会话【其他连接】将可以查看到事务变化后的新数据【所有数据就正式生效.】

# 细节：
# 1.如果不开始事务，默认情况下，dml操作是自动提交的，不能回滚
insert into transaction_table values (1,'tom');
rollback ; -- 没有开启自动提交
# 2.如果开始一个事务，你没有创建保存点.你可以执行rollback，默认就是回退到你事务开始的状态.(没有设置保存点)
start transaction;
insert into transaction_table values (2,'jack'),(3,'jerry');
rollback ; -- 没有创建保存点 全部回退 创建事务的时候 默认保存点
# 3.你也可以在这个事务中（还没有提交时），创建多个保存点.比如∶ savepoint aaa； 执行 dml，savepoint bbb；(多个保存点)
# 4.你可以在事务没有提交前， 选择回退到哪个保存点.
insert into transaction_table values (2,'jack'),(3,'jerry');
savepoint second;
insert into transaction_table values (4,'smith');
savepoint third;
rollback to third;
commit ;
# 5.mysql的事务机制需要innodb的存储引擎才可以使用，myisam不好使.(存储引擎)
# 6.开始一个事务start transaction， set autocommit=off；(开始事务方式)

select * from transaction_table;