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
from dual; -- 10
# BIN(decimal_number) 十进制转二进制
select bin(10)
from dual; -- binary 0000 1010
# ceiling(number2) 向上取整，得到比num2大的最小整数
select ceiling(1.1) number
from dual; -- 2
# CONV(number2,from_base,to_base) 进制转换  from_base (number2进制)来源进制 to_base(要转化成什么进制)
select conv(8, 10, 2)
from dual; -- convert 8 是16进制 转换成为 2进制
select conv(15, 16, 2)
from dual;
# FLOOR (number2) 向下取整，得到比 num2小的最大整数
select floor(-1.1)
from dual; -- 2
# FORMAT (number,decimal_places ) 保留小数位数 四舍五入
select format(11.4567899, 2) number
from dual; -- 11.46
# HEX (DecimalNumber) 转十六进制
select hex(16)
from dual; -- 16->1
# LEAST (number , number2 [...]) 求最小值
select least(11, 232, 4545, 1) minNumber
from dual;
# MOD (numerator ,denominator ) 求余
select mod(10, 3)
from dual; -- 1 => (10%3)
# RAND([seed]) RAND([seed]) 其范围为0≤v≤1.0
# 1.使用rand()每次返回一个 0≤v≤1.0 的随机数
# 2.使用rand(seed) 返回随机数 范围0≤v≤1.0 如果seed不变 该随机数不变
select rand(3)
from dual;
# rand（）返回一个随机浮点值v，范围在0到1之间（即，其范围为0≤v≤1.0）。若已指定一个整数参数N 则它被用作种子值，用来产生重复序列


-- 日期函数
# 创建相关测试表
# 新闻表
create table news (
    id int,
    content varchar(30),
    sendTime datetime
);
# 第一组
# 详情
# CURRENT_DATE( ) 当前日期
# date 年-月-日 yyyy-MM-dd
select current_date() from dual; -- 2022-07-29
# CURRENT_TIME( ) 当前时间
select current_time() from dual; -- 22:49:55
# CURRENT_TIMESTAMP() 当前时间戳
# timestamp 年-月-日 时:分:秒 yyyy-MM-dd HH:mm:ss
select current_timestamp() from dual; -- 2022-07-29 22:50:32

insert into news values (1,'爆笑新闻',current_timestamp());
select * from news;

# 第二组
insert into news values (2,'上海新闻',now());
insert into news values (3,'北京新闻',current_timestamp());
# DATE (datetime) 返回datetime的日期部分
select date(now()) from dual;
# DATE_ADD(date2,INTERVAL d_value d_type) 在date2中加上日期或时间
select date_add(now(),interval 10 minute) afterTime from dual; -- 2022-07-29 23:20:44  10 minute 后
# DATE_SUB(date2,INTERVAL d_value d_type) 在date2上减去一个时间
select date_sub(now(),interval 10 minute) beforeTime from dual; -- 2022-07-29 23:01:52 10 minute 前
# DATEDIFF (date1,date2) 两个日期差（结果是天）
select datediff(now(),date_add(now(),interval 1 year)) day from dual; -- -365 现在和一年后比较
select datediff(now(),date_sub(now(),interval 1 year)) day from dual; -- 365 现在和一年前比较

# exercise
# 1.显示所有留言信息，发布日期只显示日期，不用显示时间.
select id,content,date(sendTime) as sendTime from news;
# 2.请查询在10分钟内发布的帖子
select * from news where date_sub(now(),interval 10 minute)<=sendTime;
select * from news where date_add(sendTime,interval 10 minute)<=now();
# 3.请在mysql的sql语句中求出2011-11-11和1990-1-1相差多少天
select datediff('2011-11-11','1990-1-1') day from dual;
# 4.请用mysql的sql语句求出你活了多少天
select datediff(date(now()),'1998-03-26') day from dual;
# 5.如果你能活80岁，求出你还能活多少天.
select datediff(date_add('1958-03-26',interval 80 year ),now()) day from dual;

# 第二组细节说明
# 1.DATE_ADD（）中的 interval后面可以是 year minute second day 等.
# 2.DATE_SUB（）中的 interval 后面可以是 year minute second hour day 等.
# 3.DATEDIFF（date1，date2）得到的是天数，而且是date1-date2的天数，因此可以取负数
# 4.这四个函数的日期类型可以是 date，datetime或者timestamp

# 第三组
# TIMEDIFF(date1,date2) 两个时间差（多少小时多少分钟多少秒）
select timediff(now(),'2022-07-29 22:51:50') time from dual; -- 00:41:25
# NOW()当前时间年月日
select now() from dual;
# YEAR|Month|DAY|DATE(datetime) FROM_UNIXTIME() unix_timestamp();
select year(now()),month(now()),day(now()),hour(now()),minute(now()),second(now()) from dual;
select date_format(from_unixtime(1659109065721/1000),'%Y-%m-%d %H:%i:%s');
select unix_timestamp() from dual; -- 1659109731 生成当前时间的时间戳 返回的是1970-1-1 到现在的秒数
select from_unixtime(unix_timestamp()) time from dual;
select from_unixtime(1659109065721/1000,'%Y-%m-%d %H:%i:%s') time from dual;

# 补充 常用符号标识
# %a：缩写星期名
# %b：缩写月名
# %c：月，数值
# %D：带有英文前缀的月中的天
# %d：月的天，数值(00-31)
# %e：月的天，数值(0-31)
# %f：微秒
# %H：小时 (00-23)
# %h：小时 (01-12)
# %I：小时 (01-12)
# %i：分钟，数值(00-59)
# %j：年的天 (001-366)
# %k：小时 (0-23)
# %l：小时 (1-12)
# %M：月名
# %m：月，数值(00-12)
# %p：AM 或 PM
# %r：时间，12-小时（hh:mm:ss AM 或 PM）
# %S：秒(00-59)
# %s：秒(00-59)
# %T：时间, 24-小时 (hh:mm:ss)
# %U：周 (00-53) 星期日是一周的第一天
# %u：周 (00-53) 星期一是一周的第一天
# %V：周 (01-53) 星期日是一周的第一天，与 %X 使用
# %v：周 (01-53) 星期一是一周的第一天，与 %x 使用
# %W：星期名
# %w：周的天 （0=星期日, 6=星期六）
# %X：年，其中的星期日是周的第一天，4 位，与 %V 使用
# %x：年，其中的星期一是周的第一天，4 位，与 %v 使用
# %Y：年，4 位
# %y：年，2 位

-- 加密和系统函数
# 创建测试用户表
create table users(
    id int,
    name varchar(30) not null default '',
    pwd char(32) not null default ''
);
# USER() 查询用户
select user(); -- current user
# DATABASE() 数据库名称
select database(); -- current database
# MD5(str) 为字符串算出一个MD532的字符串，（用户密码）加密
select md5(123456) md5 from dual; -- e10adc3949ba59abbe56e057f20f883e
# PASSWORD(str) 从原文密码str计算并返回密码字符串，通常用于对mysql 数据库的用户密码加密
select user,Host,authentication_string from mysql.user;
# mysql 5.7.9 之后取消了password 函数
# mysql 中文文档地址 https://docs.gitcode.net/mysql/guide/general-information/mysql-nutshell.html
select password('11') from dual; -- *0801D10217B06C5A9F32430C1A34E030D41A0257
# 8.0 update user password
# alter user'' identified with mysql_native_password by '';
select version();

insert into users (id, name, pwd) VALUES (1,'测试',md5(123456));
select * from users where name='测试' and pwd=md5('123456');




-- question ONLY_FULL_GROUP_BY 问题 临时修改
-- SELECT @@sql_mode;
-- set sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

