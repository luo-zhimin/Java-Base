# 多表查询

# 多表原生查询
# 笛卡尔集 现象
# 笛卡尔集的 列数为每个表的列数之和，行数为每个表的行数相乘
# 小技巧∶多表查询的条件不能少于表的个数-1(n-1) n为table，否则会出现笛卡尔集

# 显示雇员名，雇员工资及所在部门的名字【笛卡尔集】
select ename, sal, e.deptno, d.deptno, dname
from emp e,
     dept d
where d.deptno = e.deptno;
# 显示部门号为10的部门名、员工名和工资显示各个员工的姓名，工资，及其工资的级别
select e.deptno, d.dname, ename, sal, s.grade
from emp e,
     dept d,
     salgrade s
where e.deptno = d.deptno
  and e.deptno = 10
  and sal between losal and hisal;
# 显示雇员名，雇员工资及所在部门的名字，并按部门排序【降序排】
select ename, sal, e.deptno, d.dname
from emp e,
     dept d
where e.deptno = d.deptno
order by e.deptno desc;

# 自连接
# 自连接是指在同一张表的连接查询【将同一张表看做两张表】
# 显示公司员工和他的上级的名字 mgr
select e.empno, e.ename, p.mgr parentDept, p.ename parentName
from emp e,
     emp p
where e.mgr = p.empno;

# 子查询
# 子查询是指嵌入在其它sql语句中的select语句,也叫嵌套查询

# 单行子查询
# 单行子查询是指只返回一行数据的子查询语句
select deptno
from emp
where ename = 'SMITH';
# 显示与 SMITH 同一部门的所有员工
select *
from emp
where deptno = (select deptno from emp where ename = 'SMITH');

# 多行子查询
# 多行子查询指返回多行数据的子查询 使用关键字 in

# 查询和部门 10 的工作相同的雇员的 名字、岗位、工资、部门号, 但是不含 10 号部门自己的雇员
select ename, job, sal, deptno
from emp
where job in (select distinct job from emp where deptno = 10)
#   and deptno != 10;
  and deptno <> 10;

# 子查询当做临时表使用
select e.ename, e.empno, ew.job
from emp e,
     (select * from emp) ew
where e.empno = ew.empno;

# 在多行子查询中使用 all 操作符
# all 所有的
# 显示工资比部门 30 的所有员工的工资高的员工的姓名、工资和部门号
# 比他最高的工资还高
select ename, sal, empno
from emp
where sal > all (select sal from emp where deptno = 30);

select ename, sal, empno
from emp
where sal > (select max(sal) from emp where deptno = 30);
-- 日常

# 在多行子查询中使用 any 操作符
# 显示工资比部门 30 的其中一个员工的工资高的员工的姓名、工资和部门号
# any 其中一个
select ename, sal, empno
from emp
where sal > any (select sal from emp where deptno = 30);

select ename, sal, empno
from emp
where sal > (select min(sal) from emp where deptno = 30);
-- 日常

# 多列子查询
# 多列子查序则是指查询返回多个列数据的子查询语句
# 请查询和宋江数学，英语，语文 成绩完全相同的学生
select s.*
from student s,
     (select * from student where NAME = '宋江') sj
where s.math = sj.math
  and s.chinese = sj.chinese
  and s.english = sj.english;

# 查询与ALLEN的部门和岗位完全相同的所有雇员（并且不含ALLEN本人）
# （字段1，字段2…）=（select字段1，字段2 from。。。)
select e.ename, e.deptno, e.job
from emp e,
     (select deptno, job from emp where ename = 'ALLEN') w
where e.job = w.job
  and e.deptno = w.deptno
  and e.ename <> 'ALLEN'; -- 日常

select *
from emp
where (deptno, job) = (select deptno, job from emp where ename = 'ALLEN')
  and ename != 'ALLEN';

# child query exercise
# 查找 每个部门工资高于 本部门平均工资的人的资料
select e.*, format(avgSal, 2) as avgSal
from emp e,
     (select avg(sal) avgSal, deptno from emp group by deptno) w
where e.deptno = w.deptno
  and sal > avgSal
order by e.deptno;

# 查找每个部门工资最高的人的详细资料
select *
from emp
group by deptno
having max(sal);

# 查询每个部门的信息（包括∶部门名，编号，地址）和人员数量
select d.*, count
from (select COUNT(*) count, deptno from emp group by deptno) e,
     dept d
where e.deptno = d.deptno;

# select @@sql_mode;
# set sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';