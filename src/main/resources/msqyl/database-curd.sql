-- 数据库的 简单 curd 操作 
-- c->create u->update r->read d ->delete

-- student
desc student;
# change filed
alter table student
    change socre score DOUBLE;
-- INSERT
INSERT INTO student(`name`, `score`)
VALUES ('王武', 100);
-- 细节
-- 插入的数据应与字段的数据类型相同
-- 数据的长度应在列的规定范围内，例如：不能将一个长度为80的字符串加入到长度为40的列中
-- 在values中列出的数据位置必须与被加入的列的排列位置相对应
-- 字符和日期型数据应包含在单引号中
INSERT INTO student(`name`, `score`)
VALUES ('张三', 100);
-- 列可以插入空值[前提是该字段允许为空]，insert into table value(null)
-- insert into tab_name(列名..) values(),(),() 形式添加多条记录
INSERT INTO student(`name`, `score`)
VALUES ('时尚', 100),
       ('三代', 100);
-- 如果是给表中的所有字段添加数据，可以不写前面的字段名称
SELECT *
from student;
INSERT INTO student
VALUES (4, '受伤', 100);
-- 默认值的使用，当不给某个字段值时，如果有默认值就会添加默认值，否则报错

SELECT *
from student;
-- UPDATE
-- 将姓名为 张三 的学生的成绩 改为100分
UPDATE student
set score = 100
where name = '张三';

-- 将姓名为 三代 的学生的成绩 减10分
UPDATE student
set score = score - 10
where name = '三代';

-- 细节
-- 1.UPDATE语法可以用新值更新原有表行中的各列。
-- 2.SET子句指示要修改哪些列和要给予哪些值。
-- 3.WHERE子句指定应更新哪些行。如没有WHERE子句，则更新所有的行（记录），提醒一定小心。
-- 4.如果需要修改多个字段，可以通过 set 字段1=值1，字段2=值2……

-- DELETE
DELETE
from student
where id = 2;

-- 细节
-- 1. 如果不使用where子句，将删除表中所有数据。
-- 2. Delete语句不能删除某一列的值（可使用update 设为null或者 '' ）
-- 3. 使用delete语句仅删除记录，不删除表本身。如要删除表，使用drop table语句。drop table 表名；
SELECT *
from student;

-- SELECT
RENAME TABLE student to student_test;

CREATE TABLE student
(
    id      INT         NOT NULL DEFAULT 1,
    NAME    VARCHAR(20) NOT NULL DEFAULT '',
    chinese FLOAT       NOT NULL DEFAULT 0.0,
    english FLOAT       NOT NULL DEFAULT 0.0,
    math    FLOAT       NOT NULL DEFAULT 0.0
);
INSERT INTO student (id, NAME, chinese, english, math)
VALUES (1, '韩顺平', 89, 78, 90);
INSERT INTO student (id, NAME, chinese, english, math)
VALUES (2, '张飞', 67, 98, 56);
INSERT INTO student (id, NAME, chinese, english, math)
VALUES (3, '宋江', 87, 78, 77);
INSERT INTO student (id, NAME, chinese, english, math)
VALUES (4, '关羽', 88, 98, 90);
INSERT INTO student (id, NAME, chinese, english, math)
VALUES (5, '赵云', 82, 84, 67);
INSERT INTO student (id, NAME, chinese, english, math)
VALUES (6, '欧阳锋', 55, 85, 45);
INSERT INTO student (id, NAME, chinese, english, math)
VALUES (7, '黄蓉', 75, 65, 30);
INSERT INTO student (id, NAME, chinese, english, math)
VALUES (8, '韩信', 45, 65, 99);


-- 1.查询表中所有学生的信息。
SELECT *
from student;
-- 2.查询表中所有学生的姓名和对应的英语成绩。
SELECT `name`, english
from student;
-- 3.过滤表中重复数据distinct。
SELECT distinct *
from student;
-- 4.要查询的记录，每个字段都相同，才会去重
SELECT distinct name, english
from student;

-- 使用表达式对查询的列进行运算
SELECT name, (english + math + chinese) totalScore
from student;
-- 在select语句中可使用as语句
SELECT id, name studentName, english
from student;

-- 1.统计每个学生的总分
-- 2.在所有学生总分加10分的情况
-- 3.使用别名表示学生分数。
SELECT name, (english + math + chinese + 10) totalScore
from student;

-- ■在赵云的总分上增加60%
SELECT name,
       (english + math + chinese)                                                sourceScore,
       ROUND(((english + math + chinese) + (english + math + chinese) * 0.6), 2) targetScore
from student
where name = '赵云';
-- ■统计关羽的总分。
SELECT name, (english + math + chinese) score
from student
where name = '关羽';
-- ■使用别名表示学生的数学分数。

-- 在where子句中经常使用的运算符
-- 1.查询姓名为赵云的学生成绩
SELECT *
from student
where `name` = '赵云';
-- 2.查询英语成绩大于90分的同学
SELECT *
from student
where english > 90;
-- 3.查询总分大于200分的所有同学
SELECT *
from student
where (english + math + chinese) > 200;

-- ■查询math大于60 并且 id大于4的学生成绩
SELECT name, (english + math + chinese) score
from student
where math > 60
  and id > 4;
-- ■查询英语成绩大于语文成绩的同学
SELECT *
from student
where english > math;
-- ■查询总分大于200分并且数学成绩小于语文成绩，的姓韩的学生.
SELECT *, (english + math + chinese) score
from student
where (english + math + chinese) > 200
  and math < chinese
  and `name` like '韩%';

-- 1.查询英语分数在80-90之间的同学。
-- 2.查询数学分数为89，90，91的同学。
-- 3.查询所有姓李的学生成绩。
-- 4.查询数学分>80，语文分>80的同学。

SELECT *
from student
where english BETWEEN 80 and 90;
SELECT *
from student
where math BETWEEN 89 and 91;
SELECT *
from student
where name like '李%';
SELECT *
from student
where math > 80
  and chinese > 80;

/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

-- 1.查询语文分数在70-80之间的同学。
-- 2.查询总分为189，190，191的同学。
-- 3.查询所有姓李或者姓宋的学生成绩。
-- 4.查询数学比语文多30分的同学。
SELECT *
from student
where chinese BETWEEN 70 and 80;
SELECT *
from student
where (english + math + chinese) BETWEEN 189 and 191;
SELECT *
from student
where name like '李%'
   or name like '宋%';
SELECT *
from student
where math - chinese = 30;

-- order by
-- 对数学成绩排序后输出【升序】。
-- ■对总分按从高到低的顺序输出
-- ■对姓李的学生成绩排序输出（升序）
SELECT *
from student
order by math;
SELECT any_value(`id`),
       any_value(`name`),
       any_value(`english`),
       any_value(`math`) math,
       any_value(`chinese`)
-- ( english + math + chinese ) score 
FROM student
GROUP BY `name`
ORDER BY math DESC;

SELECT *, (english + math + chinese) score
from student
where `name` like '韩%'
order by score;


SELECT id, `name`
from student
GROUP BY id;

