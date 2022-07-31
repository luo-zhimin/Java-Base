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

