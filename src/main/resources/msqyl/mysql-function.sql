/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

-- FUNCTION 函数

-- 合计/统计函数
-- count
-- 统计一个班级共有多少学生统计数学成绩大于90的学生有多少个
-- 统计总分大于250的人数有多少
SELECT `name`, COUNT(*)
FROM student
where math > 90;
SELECT `name`, COUNT(*)
FROM student
where (chinese + math + english) > 250
GROUP BY `name`;
-- count（*）和count（列）的区别
-- count（*）返回满足条件的记录的行数
-- count（列）统计满足条件的某列有多少个，但是会排除 为null的

-- sum
-- ■统计一个班级数学总成绩
SELECT sum(math) math
FROM student;
-- ■统计一个班级语文、英语、数学各科的总成绩
SELECT sum(chinese) chinese, SUM(math) math, SUM(english) english
FROM student;
-- ■统计一个班级语文、英语、数学的成绩总和
SELECT SUM(chinese + math + english) score
FROM student;
-- ■统计一个班级语文成绩平均分
SELECT sum(chinese) / COUNT(*) math
FROM student;
-- SELECT AVG(chinese) avgChinese from student;
-- 注意∶sum仅对数值起作用，其他没有意义
-- 注意∶对多列求和，“，”号不能少

-- avg
-- 求一个班级数学平均分？
SELECT avg(math)
FROM student;
-- 求一个班级总分平均分
SELECT format(avg(chinese + english + math), 2) avgScore
FROM student;

-- max/min
-- 求班级最高分和最低分（数值范围在统计中特别有用）

SELECT max(chinese + english + math) maxScore,
       min(chinese + english + math) minScore
FROM student;

-- GROUP BY + HAVING
-- having子句用于限制分组显示结果. ?
-- 如何显示每个部门的平均工资和最高工资?
-- SELECT AVG(sal),MAX(sal),d.dname from dept d
-- INNER JOIN emp e on d.deptno=e.deptno GROUP BY d.deptno;

SELECT AVG(sal), max(sal), deptno
FROM emp
GROUP BY deptno
ORDER BY deptno;

-- 显示每个部门的每种岗位的平均工资和最低工资?

SELECT format(AVG(sal), 2), min(sal), deptno, job
FROM emp
GROUP BY deptno, job
ORDER BY deptno;

-- 显示平均工资低于2000的部门号和它的平均工资//别名
SELECT format(AVG(sal), 2) avg_sal, deptno
FROM emp
GROUP BY deptno
HAVING avg_sal < 2000;

-- where和having的作用以及区别：
-- WHERE是一个约束声明，在查询数据库的结果返回之前对数据库中的查询条件进行约束，即在结果返回之前起作用，且where后面不能使用“聚合函数”，因为where的执行顺序在聚合函数之前。
-- HAVING是一个过滤声明，过滤是在查询数据库的结果返回之后进行过滤，即在结果返回之后起作用，且having后面可以使用“聚合函数”。注意：having是对查出来的结果进行过滤，那么对没有查出来的值就不能使用having

SELECT *
from dept;
SELECT *
from emp;
SELECT *
from salgrade;

-- 字符串函数
# 详情
# CHARSET(str) 返回字串字符集
select charset(deptno)
from emp;
-- utf8mb4
# CONCAT (string2 [,... ]) 连接字串  多个字符拼接一列
select concat(ename, ' job is ', job)
from emp;
-- sdasda1
# INSTR(string ,substring) 返回substring在string中出现的位置，没有返回0
select instr('adwad', 'w')
from dual;
-- dual 虚拟表 系统的 可以作为一个测试表使用
# UCASE (string2) 转换成大写
select ucase(ename)
from emp;
-- AAAW11223Z
# LCASE (string2) 转换成小写
select lcase(ename)
from emp;
-- waaasda1
# LEFT (string2 ,length ) 从string2中的左边起取length个字符
select left(ename, 2) target, ename source
from emp;
# right 从string2 中的右边起取length个字符
select right(ename, 3)
from emp;
# LENGTH (string)string长度【按照字节】
select length(ename) length, ename
from emp;
# REPLACE (str search_str ,replace_str ) 在str中用replace_str替换search_str
select lcase(ename) as ename, replace(job, 'MANAGER', '经理') replaceTarget
from emp;
# STRCMP (string1 ,string2) 逐字符比较两字串大小 返回 -1 0 1 -> {>,=,<}
select strcmp(ename, empno)
from emp;
# SUBSTRING (str,position [,length ]) 从str的position开始【从1开始计算】，取length 个字符
select substring(ename, 1, 2), ename
from emp;
-- 从第一个位置开始截取 截取2位
# LTRIM(string2) 去除前端空格  RTRIM(string2) 后端空格 trim 去除俩边的空格
select ltrim(' sss ss'), rtrim(' www '), trim(' sdas ')
from dual;
select *
from emp;

# 以首字母小写的方式显示所有员工 emp 表的姓名
# replace + substring
# first
select replace(ename, substring(ename, 1, 1), substring(lcase(ename), 1, 1))
from emp;
# second
select replace(ename, left(ename, 1), lcase(left(ename, 1)))
from emp;
select concat(lcase(substring(ename, 1, 1)), substring(ename, 2)) as newName
from emp;
select concat(lcase(left(ename, 1)), right(ename, (length(ename) - 1)))
from emp;

-- 数学函数
# 详情
# ABS(num) 绝对值
select abs(-10) number
from dual;
-- 10
# BIN(decimal_number) 十进制转二进制
select bin(10)
from dual;
-- binary 0000 1010
# ceiling(number2) 向上取整，得到比num2大的最小整数
select ceiling(1.1) number
from dual;
-- 2
# CONV(number2,from_base,to_base) 进制转换  from_base (number2进制)来源进制 to_base(要转化成什么进制)
select conv(8, 10, 2)
from dual; -- convert 8 是16进制 转换成为 2进制
select conv(15, 16, 2)
from dual;
# FLOOR (number2) 向下取整，得到比 num2小的最大整数
select floor(-1.1)
from dual;
-- 2
# FORMAT (number,decimal_places ) 保留小数位数 四舍五入
select format(11.4567899, 2) number
from dual;
# HEX (DecimalNumber) 转十六进制
select hex(16)
from dual;
/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

-- 16->1
# LEAST (number , number2 [...]) 求最小值
select least(11, 232, 4545, 1) minNumber
from dual;
# MOD (numerator ,denominator ) 求余
select mod(10, 3)
from dual;
-- 1 => (10%3)
# RAND([seed]) RAND([seed]) 其范围为0≤v≤1.0
# 使用rand()每次返回一个 0≤v≤1.0 的随机数
# 使用rand(seed) 返回随机数 范围0≤v≤1.0 如果seed不变 该随机数不变
select rand(3)
from dual;
# rand（）返回一个随机浮点值v，范围在0到1之间（即，其范围为0≤v≤1.0）。若已指定一个整数参数N 则它被用作种子值，用来产生重复序列


-- question ONLY_FULL_GROUP_BY 问题 临时修改
-- SELECT @@sql_mode;
-- set sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

