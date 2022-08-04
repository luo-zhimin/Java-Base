/*
 * Copyright (c) luoZhiMin 2022.8.4.3.16.27
 */

-- 视图
# 1.视图是一个虚拟表，其内容由查询定义。同真实的表一样，视图包含列，其数据来自对应的真实表（基表）

-- 视图的基本使用
-- 创建 修改 查看 删除
# 1.create view 视图名 as select语句
# 2.alter view 视图名 as select语句--更新成新的视图
# 3.SHOW CREATE VIEW 视图名
# 4.drop view 视图名1，视图名2
# 创建一个视图emp_view01，只能查询emp表的(empno、ename,job和deptno)信息
create view emp_view01 as select ename,job,empno,deptno from emp;
-- 查看视图
desc emp_view01;
select ename,job,empno,deptno from emp_view01;
-- 视图修改 基表也会被修改
update emp_view01 set deptno=10 where ename='SMITH';
-- 基表
select * from emp where ename='SMITH';

-- 查看创建视图的指令
show create view emp_view01;
-- 删除视图
drop view emp_view01;

-- 细节
# 1.创建视图后，到数据库去看，对应视图只有一个视图结构文件（形式∶视图名.frm）
# 2.视图的数据变化会影响到基表， 基表的数据变化也会影响到视图【insert update delete】
# 3.视图中可以再使用视图，数据仍然来自基表.
-- 在现有视图里在创建一个新的视图
create view emp_view02 as select ename,job from emp_view01;
select * from emp_view02;
-- update  view01 和 emp 都会受到改变 基表
update emp_view02 set job='worker' where ename='SMITH';

select * from emp;
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7935, 'Jerry', 'mouse', 7902, '1990-12-17', 800.00, null, 30);
select * from emp_view01;
select * from emp_view02;

-- 视图最佳实践
# 1.安全。一些数据表有着重要的信息。有些字段是保密的，不能让用户直接看到。这时就可以创建一个视图，在这张视图中只保留一部分字段。这样，用户就可以查询自己需要的字段，不能查看保密的字段。
# 2.性能。关系数据库的数据常常会分表存储，使用外键建立这些表的之间关系。这时，数据库查询通常会用到连接 （JOIN）。这样做不但麻烦， 效率相对也比较低。如果建立一个视图，将相关的表和字段组合在一起，就可以避免使用JOIN查询数据。
# 3.灵活。如果系统中有一张旧的表，这张表由于设计的问题，即将被废弃。然而，很多应用都是基于这张表，不易修改。这时就可以建立一张视图，视图中的数据直接映射到新建的表。这样，就可以少做很多改动，也达到了升级数据表的目的。

-- exercise
# emp dept 和 salgrade 张
# 显示雇员编号，雇员名，雇员部门名称和薪水级别
create view emp_view03 as
select empno, ename, dname, grade
from emp e,
     dept d,
     salgrade s
where e.deptno = d.deptno
  and e.sal between s.losal and s.hisal;

select  * from emp_view03;