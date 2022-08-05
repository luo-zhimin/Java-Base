/*
 * Copyright (c) luoZhiMin 2022.8.4.10.26.37
 */

select @@sql_mode;
set @@sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- 练习
# 查看DEPT表和EMP表的结构
desc dept;
desc emp;
# 使用简单查询语句完成
#  显示所有部门名称。
select deptno,dname from dept;
#  显示所有雇员名及其全年收入13月（工资+补助），并指定列别名 '年收入'
select ename,(sal+ifnull(comm,0)) * 13 as '年收入' from emp group by ename;

# 限制查询数据。
# 1）显示工资超过2850的雇员姓名和工资。
select ename,sal from emp where sal>2850;
# 2）显示工资不在1500到2850之间的所有雇员名及工资。
select ename,sal from emp where sal not between 1500 and 2850;
# 3）显示编号为7566的雇员姓名及所在部门编号。
select ename,deptno from emp where empno=7566;
# 4）显示部门10和30中工资超过1500的雇员名及工资。
select ename,sal from emp where deptno in (10,30) and sal>1500;
# 5）显示无管理者的雇员名及岗位。
select ename,job from emp where mgr is null ;

# 排序数据。
# 1）显示在1991年2月1日到1991年5月1日之间雇用的雇员名，岗位及雇佣曰期，并以雇佣曰期进行排序。
select ename,job,hiredate from emp where hiredate between date('1991-02-01') and date('1991-05-01') order by hiredate;
# 2）显示获得补助的所有雇员名，工资及补助，并以工资降序排序
select ename,job,comm from emp where comm is not null order by sal desc ;


-- use emp table
# 1.选择部门30中的所有员工
select * from emp where empno=30;
# 2.列出所有办事员（CLERK）的姓名，编号和部门编号.
select ename,empno,deptno from emp where job='CLERK';
# 3.找出佣金高于薪金的员工
select * from emp where ifnull(comm,0)>sal;
# 4.找出佣金高于薪金60%的员工
select * from emp where ifnull(comm,0) > (sal*0.6);
# 5.找出部门10中所有经理（MANAGER）和部门20中所有办事员（CLERK）的详细资料，
select * from emp where (deptno=10 and job='MANAGER') or (deptno=20 and job='CLERK');
# 6.找出部门10中所有经理（MANAGER），部门20中所有办事员（CLERK），还有既不是经理又不是办事员但其薪金大于或等于2000的所有员工的详细资料.
select * from emp where (deptno=10 and job='MANAGER')
or (deptno=20 and job='CLERK')
or job not in ('MANAGER','CLERK') and sal>=2000;
# 7.找出收取佣金的员工的不同工作.
select distinct job from emp where comm is not null;
# 8.找出不收取佣金或收取的佣金低于100的员工.
select * from emp where comm is null or comm <100;
# 9.找出各月倒数第3天受雇的所有员工.
select * from emp where date_sub(last_day(hiredate),interval 2 day) = hiredate;
-- 8.31
select date_sub(last_day(now()),interval 2 day) from dual;
select last_day(now()) -2 from dual; -- 20220829
select date(20220829) from dual; -- 2022-08-29
# 10.找出早于12年前受雇的员工
select * from emp where date_sub(hiredate,interval 12 year ) < hiredate;
select date_sub(now(),interval 12 year) year from dual;
# 11.以首字母小写的方式显示所有员工的姓名.
select concat(lcase(substring(ename,1,1)),substring(ename,2,length(ename))) name,ename from emp;
# 12.显示正好为5个字符的员工的姓名.
select ename from emp where length(trim(ename))=5;
# 13.显示不带有"R"的员工的姓名.
select ename from emp where ename not like '%R%';
# 14.显示所有员工姓名的前三个字符
select ename,substring(ename,1,3) from emp;
# 15.显示所有员工的姓名，用a替换所有"A"
select ename,replace(ename,'A','a') replaceName from emp;
select ename,replace(ename,'a','A') replaceName from emp;
# 16.显示满10年服务年限的员工的姓名和受雇日期，
select ename,hiredate from emp where date_sub(now(),interval 10 year ) >= hiredate;
select date_sub(now(),interval 10 year ) from dual;
# 17.显示员工的详细资料，按姓名排序.
select * from emp order by ename ;
# 18.显示员工的姓名和受雇日期，根据其服务年限，将最老的员工排在最前面，
select ename,hiredate from emp order by hiredate;
# 19.显示所有员工的姓名、工作和薪金，按工作降序排序，若工作相同则按薪金排序，
select ename,job,sal from emp order by job desc ,sal;
# 20.显示所有员工的姓名、加入公司的年份和月份，按受雇日期所在月排序，若月份相同则将最早年份的员工排在最前面
select ename,year(hiredate) year,month(hiredate) month,date_format(hiredate,'%Y-%m') johnDay  from emp order by month,year;
# 21.显示在一个月为30天的情况所有员工的日薪金，忽略余数.
select ename,floor(sal/30) salay from emp;
# 22.找出在（任何年份的）2月受聘的所有员工。
select * from emp where hiredate like '_____02%';
# 23.对于每个员工，显示其加入公司的天数.
select ename,datediff(now(),hiredate) day from emp;
# 24.显示姓名字段的任何位置包含"A"的所有员工的姓名.
select * from emp where ename like '%A%';
# 25.以年月日的方式显示所有员工的服务年限.（大概）
select ename,
       floor(datediff(now(), hiredate) / 365)               year,
       floor((datediff(now(), hiredate) / 365)*12) month,
       datediff(now(), hiredate)                     day
from emp order by day desc ;

# 根据∶emp员工表，dept部门表，工资=薪金sal+佣金comm
# 列出至少有一个员工的所有部门
select deptno,count(*) count from emp group by deptno having count>0;
# 列出薪金比“SMITH”多的所有员工。
select * from emp where sal > (select sal from emp where ename='SMITH');
# 列出受雇日期晚于其直接上级的所有员工。
select e.ename,p.ename parentName,e.hiredate,p.hiredate parentDate from emp e,emp p
where e.mgr=p.empno and e.hiredate>p.hiredate;
# 列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。
select e.*,dname from emp e right join dept d on e.deptno = d.deptno;
# 列出所有“CLERK”（办事员）的姓名及其部门名称。
select ename,dname from emp e inner join dept d on e.deptno = d.deptno where e.job='CLERK';
# 列出最低薪金大于1500的各种工作。
select distinct job from emp where (sal+ifnull(comm,0))>1500;
select job,min(sal) min from emp group by job having min>1500;
# 列出在部门“SALES”（销售部）工作的员工的姓名。
select ename from emp e inner join dept d on e.deptno = d.deptno where dname='SALES';
# 列出薪金高于公司平均薪金的所有员工。
select * from emp where (sal+ifnull(comm,0)) > (select avg(sal+ifnull(comm,0)) from emp);
# 列出与“SCOTT”从事相同工作的所有员工。
select * from emp where job = (select job from emp where ename='SCOTT');
# 列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金。
select ename, (sal + ifnull(comm, 0)) salary
from emp
where (sal + ifnull(comm, 0)) > (select max(sal + ifnull(comm, 0)) salary from emp where deptno = 30);
# 列出在每个部门工作的员工数量、平均工资和平均服务期限
select deptno,COUNT(*),format(avg(sal),2) avg_salary,floor(avg(datediff(now(),hiredate)/365)) year from emp group by deptno;
# 列出所有员工的姓名、部门名称和工资。
select ename,dname,sal from emp e inner join dept d on e.deptno = d.deptno;
# 列出所有部门的详细信息和部门人数。
select d.*,COUNT(d.deptno) count from emp e inner join dept d on e.deptno = d.deptno group by e.deptno;
# 列出各种工作的最低工资。
select job,min(sal) minSalary from emp group by job;
# 列出 MANAGER（经理）的最低薪金。
select job,min(sal) minSalary from emp where job='MANAGER';
# 列出所有员工的年工资，按年薪从低到高排序。
select ename,(sal+ifnull(comm,0))*12 yearSalary from emp order by yearSalary;

drop table student,class,department;

-- 修改之前的表名 避免干扰
rename table student to student_01;
-- 8
# 学校环境如下∶一个系有若干个专业，每个专业一年只招一个班，每个班有若干个学生。现要建立关于系、学生、班级的数据库，关系模式为∶
# 班 CLASS（班号classid，专业名subject，系名deptname，入学年份enrolltime，人数num）
create table `class`(
    class_id int primary key comment '班号',
    subject varchar(32),
    dept_name varchar(32) ,
    enroll_time int,
    num int
)comment 'class';
alter table class add foreign key (dept_name) references department(dept_name);
-- 修改
alter table class modify enroll_time int;
# 学生 STUDENT（学号studentid，姓名name，年龄age，班号classid）
create table `student`(
  student_id int primary key ,
  name varchar(32) not null ,
  age int,
  class_id int,
  -- 外键
  foreign key (class_id) references class(class_id)
) comment 'student';
-- 修改
alter table student modify name varchar(32) not null ;
# 系 DEPARTMENT（系号departmentid，系名deptname）
create table `department`(
    department_id int primary key ,
    dept_name varchar(32) unique
    -- foreign key (dept_name) references class(dept_name)
)comment '系';
# 建表，在定义中要求声明∶
# （1）每个表的主外码。
# （2）deptname是唯一约束。
# （3）学生姓名不能为空
desc class;
desc student;
desc department;

# 插入如下数据
# CLASS (
# 101，软件，计算机，1995，20;
# 102，微电子，计算机，1996，30;
# 111，无机化学，化学，1995，29;
# 112，高分子化学，化学，1996，25;
# 121，统计数学，数学，1995，20;
# 131，现代语言，中文，1996，20;
# 141，国际贸易，经济，1997，30;
# 142，国际金融，经济，1996，14;
# );
insert into class values (101,'软件','计算机',1995,20),
                         (102,'微电子','计算机',1996,30),
                         (111,'无机化学','化学',1995,29),
                         (112,'高分子化学','化学',1996,25),
                         (121,'统计数学','数学',1995,20),
                         (131,'现代语言','中文',1996,20),
                         (141,'国际贸易','化学',1997,30),
                         (142,'国际金融','经济',1996,14);
# DEPARTMENT(
# 001,数学;
# 002,计算机;
# 003,化学；
# 004,中文;
# 005,经济;
# ）
insert into department values (001,'数学'),
                              (002,'计算机'),
                              (003,'化学'),
                              (004,'中文'),
                              (005,'经济');
# STUDENT (
# 8101，张三，18，101;
# 8102，钱四，16，121;
# 8103，王玲，17，131;
# 8105，李飞，19，102;
# 8109，赵四，18，141;
# 8110，李可，20，142;
# 8201，张飞，18，111;
# 8302，周瑜，16，112;
# 8203，王亮，17，111;
# 8305，董庆，19，102;
# 8409，赵龙，18，101;
# 8510，李丽，20，142;
# ）
insert into student values (8101,'张三',18,101),
                           (8102,'钱四',16,121),
                           (8103,'王玲',17,131),
                           (8105,'李飞',19,102),
                           (8109,'赵四',18,141),
                           (8110,'李可',20,142),
                           (8201,'张飞',18,111),
                           (8302,'周瑜',16,112),
                           (8203,'王亮',17,111),
                           (8305,'董庆',19,102),
                           (8409,'赵龙',18,101),
                           (8510,'李丽',20,142);

# 完成以下查询功能
# 3.1找出所有姓李的学生。
select * from student where name like '李%';
# 3.2列出所有开设超过1个专业的系的名字。
select COUNT(subject) count,dept_name from class group by dept_name;
# 3.3列出人数大于等于30的系的编号和名字。 num class
select department_id,d.dept_name,sum(c.num) number from department d
    inner join class c on d.dept_name = c.dept_name
group by c.dept_name having number>30;

# 学校又新增加了一个物理系，编号为006
insert into department values (006,'物理系');
insert into department values (007,'天文');
# 学生张三退学，请更新相关的表
-- 找到对应的班级人数-1
-- 需要开启事务
start transaction;
select class_id from student where name='张三';
delete from student where name='张三';
savepoint `delete`;
update class set num=num-1 where class_id = (select class_id from student where name='张三');
savepoint `update`;
-- 判断是否需要回滚
 -- rollback to `delete`;
-- 提交之后便会释放锁 释放节点 确认好再去提交事务
commit ;
select * from class;

-- insert into student values (8101,'张三',18,101);

-- grant
grant select,create,delete,update on tmp.* to test@localhost;
show grants for test@localhost;
-- 必须权限分配里面有 分配的时候没有细分 移除时候也不可以细分
revoke delete on tmp.dept from 'test'@'localhost'; -- 错
revoke delete on tmp.* from 'test'@'localhost'; -- 对

select user,host from mysql.user;