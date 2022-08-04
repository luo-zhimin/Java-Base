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


-- 事务隔离级别
# 1.多个连接开启各自事务操作数据库中数据时， 数据库系统要负责隔离操作，以保证各个连接在获取数据时的准确性。（通俗解释）
# 2.如果不考虑隔离性，可能会引发如下问题∶脏读 不可重复读 幻读
# 脏读 （dirty read）∶ 当一个事务读取另一个事务尚未提交的改变（update，insert，delete）时，产生脏读
# 不可重复读（non repeatable read）∶ 同一查询在同一事务中多次进行，由于其他提交事务所做的修改或删除，每次返回不同的结果集，此时发生不可重复读。
# 幻读（phantom read）∶ 同一查询在同一事务中多次进行，由于其他提交事务所做的插入操作，每次返回不同的结果集，此时发生幻读

-- 设置事务隔离级别
# 查看当前会话的隔离级别 default REPEATABLE-READ(可重复读)
# 新版本 @@transaction_isolation 旧版本 @@tx_isolation
SELECT @@transaction_isolation;
# 查看当前系统的隔离级别
select @@global.transaction_isolation;
# 3.设置当前会话隔离级别
set session transaction isolation level repeatable read;
# 4. 设置系统当前隔离级别
set global transaction isolation level repeatable read;
# 5. mysql默认的事务隔离级别是 repeatable read，一般情况下，没有特殊要求，没有必要修改（因为该级别可以满足绝大部分项目需求）

# ● 全局修改，修改my.ini配置文件，在最后加上
#可选参数有∶READ-UNCOMMITTED，READ-COMMITTED，REPEATABLE-READ,SERIALIZABLE. [mysqld]
# transaction-isolation=REPEATABLE-READ

create table `account`
(
    id    int,
    name  varchar(32),
    money int
) comment '账户';

-- 打开mysql一个控制台 查看其隔离级别
# mysql> select @@transaction_isolation;
# +-------------------------+
# | @@transaction_isolation |
# +-------------------------+
# | REPEATABLE-READ         |
# +-------------------------+
-- 设置该会话隔离级别 读未提交
set session transaction isolation level read uncommitted ;
select @@transaction_isolation;
-- 开启一个事务
start transaction;

-- mysql 控制台未提交事务 脏读(第一次查询) 幻读(多次查询返回结果不一样) 不可重复读(每次查询结果不一样 其他事务进行了修改/删除)
select * from account; -- 100 2条数据 id=1->800
commit ;

-- 设置该会话隔离级别 读已提交
set session transaction isolation level read committed ;
select @@transaction_isolation;
-- 开始一个事务
start transaction ;
-- 查询 没有读取 未提交事务 没有产生脏读 mysql 控制台提交事务 可以查询 产生幻读 和 不可重复读
select * from account;
commit ;

-- 设置该会话隔离级别 可重复读
set session transaction isolation level repeatable read ;
start transaction;
select * from account;
commit ;

-- 设置该会话隔离级别 可 串行化
set session transaction isolation level serializable ;
start transaction ;
-- 开始之后有操作 除非提交事务 不然会一直锁起来 超时
select * from account;

-- ACID
# 1. 原子性（Atomicity）
# 原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
# 2. 一致性（Consistency）
# 事务必须使数据库从一个一致性状态变换到另外一个一致性状态
# 3.隔离性（Isolation）
# 事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务的操作数据所干扰，多个并发事务之间要相互隔离。
# 4.持久性（Durability）
# 持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来即使数据库发生故障也不应该对其有任何影响
