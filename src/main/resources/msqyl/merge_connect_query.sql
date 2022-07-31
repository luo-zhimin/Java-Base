/*
 * Copyright (c) luoZhiMin 2022.
 */

# 合并查询 union all/union
-- union all
# 该操作符用于取得两个结果集的并集。当使用该操作符时，不会取消重复行(不会去重)
select ename,job,sal from emp where sal>2500; -- 5
select ename,job,sal from emp where job='MANAGER'; -- 3

-- 8
select ename,job,sal from emp where sal>2500
union all
select ename,job,sal from emp where job='MANAGER';

-- union
# 就是将两个查询结果合并，会去重
-- 6
select ename,job,sal from emp where sal>2500
union
select ename,job,sal from emp where job='MANAGER';

# 连接查询
# 外连接
# 列出部门名称和这些部门的员工名称和工作， 同时要求 显示出那些没有员工的部门
# 正常查询 - 笛卡尔集
select d.dname,e.ename,e.job from emp e,dept d
where e.deptno=d.deptno order by dname; -- 3个部门

select * from dept group by dname;
# outer query
select d.deptno,d.dname,e.ename,e.job
from dept d
left join emp e on d.deptno = e.deptno order by dname;

-- 创建测试表
CREATE TABLE stu ( id INT, `name` VARCHAR(32));
INSERT INTO stu VALUES(1, 'jack'),(2,'tom'),(3, 'kity'),(4, 'nono');

CREATE TABLE exam( id INT, grade INT);
INSERT INTO exam VALUES(1, 56),(2,76),(11, 8);
# 左外连接
# 如果左侧的表完全显示我们就说是左外连接
# select..from 表1 left join 表2 on 条件【表1∶就是左表表2∶就是右表】

# 右外连接
# 如果右侧的表完全显示我们就说是右外连接
# select…from 表1 right join 表2 on 条件【表1∶就是左表表表表2∶就是右表】

# 显示所有人的成绩，如果没有成绩，也要显示该人的姓名和 id 号,成绩显示为空
-- left join
select s.id,s.name,grade from stu s left join exam e on s.id = e.id;
-- right join
select s.id,s.name,grade from exam e right join stu s on s.id = e.id;
# 显示所有成绩，如果没有名字匹配，显示空
-- right join
select s.id,s.name,grade from stu s right join exam e on s.id = e.id;
-- left join
select s.id,s.name,grade from exam e left join stu s on s.id = e.id;

# 列出部门名称和这些部门的员工信息（名字和工作），同时列出那些没有员工的部门名
-- left
select d.deptno,d.dname,e.ename,e.job
from dept d
left join emp e on d.deptno = e.deptno order by dname;
-- right
select d.deptno,d.dname,e.ename,e.job
from emp e
right join dept d on d.deptno = e.deptno order by dname;