# 表复制
# 自我复制数据(蠕虫复制)

# 有时，为了对某个sql语句进行效率测试，我们需要海量数据时，可以使用此法为表创建海量数据
create table my_table01(
    id int,
    name varchar(30),
    salary double,
    job varchar(30),
    dept_no int
);

# 自我复制
-- 复制emp数据到my_table
insert into my_table01 select empno,ename,sal,job,deptno from emp;
-- 自我复制
insert into my_table01 select * from my_table01;
select COUNT(*) from my_table01;

# 去重复数据
-- 把过滤出来的数据(无重复)放到临时表里面 然后清空现有表
-- 在把临时表数据 重新赋值到原有表 后drop临时表

-- 把表的结构复制到 新创建的表里面
create table my_temp like my_table01;

# distinct 去重复 并把 数据放到临时表里面
insert into my_temp select distinct * from my_table01;
# 截断
truncate table my_table01;
# 把临时表的数据 重新赋值 到 source table
insert into my_table01 select * from my_temp;
# 删除临时表
drop table my_temp;

select * from my_temp;
select * from my_table01;