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
SELECT avg(chinese + english + math)
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

/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

-- 显示每个部门的每种岗位的平均工资和最低工资?

SELECT AVG(sal), min(sal), deptno, job
FROM emp
GROUP BY deptno, job
ORDER BY deptno;

-- 显示平均工资低于2000的部门号和它的平均工资//别名
SELECT AVG(sal) avg_sal, deptno
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


-- SELECT @@sql_mode;
-- set sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';