/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/*部门表*/
CREATE TABLE dept
(
    deptno MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 comment '部门编号',
    dname  VARCHAR(20)        NOT NULL DEFAULT '' comment '部门名字',
    loc    VARCHAR(13)        NOT NULL DEFAULT ''
);
INSERT INTO dept
VALUES (10, 'ACCOUNTING', 'NEW YORK'),
       (20, 'RESEARCH', 'DALLAS'),
       (30, 'SALES', 'CHICAGO'),
       (40, 'OPERATIONS', 'BOSTON');
-- 员工表
CREATE TABLE emp
(
    empno    MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 comment '编号',
    ename    VARCHAR(20)        NOT NULL DEFAULT '' comment '名字',
    job      VARCHAR(9)         NOT NULL DEFAULT '' comment '工作',
    mgr      MEDIUMINT UNSIGNED comment '上级编号',
    hiredate DATE               NOT NULL comment '入职时间',
    sal      DECIMAL(7, 2)      NOT NULL comment '薪水',
    comm     DECIMAL(7, 2) comment '红利 奖金',
    deptno   MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 comment '部门编号'
);

-- 添加测试数据
INSERT INTO emp
VALUES (7369, 'SMITH', 'CLERK', 7902, '1990-12-17', 800.00, NULL, 20),
       (7499, 'ALLEN', 'SALESMAN', 7698, '1991-2-20', 1600.00, 300.00, 30),
       (7521, 'WARD', 'SALESMAN', 7698, '1991-2-22', 1250.00, 500.00, 30),
       (7566, 'JONES', 'MANAGER', 7839, '1991-4-2', 2975.00, NULL, 20),
       (7654, 'MARTIN', 'SALESMAN', 7698, '1991-9-28', 1250.00, 1400.00, 30),
       (7698, 'BLAKE', 'MANAGER', 7839, '1991-5-1', 2850.00, NULL, 30),
       (7782, 'CLARK', 'MANAGER', 7839, '1991-6-9', 2450.00, NULL, 10),
       (7788, 'SCOTT', 'ANALYST', 7566, '1997-4-19', 3000.00, NULL, 20),
       (7839, 'KING', 'PRESIDENT', NULL, '1991-11-17', 5000.00, NULL, 10),
       (7844, 'TURNER', 'SALESMAN', 7698, '1991-9-8', 1500.00, NULL, 30),
       (7900, 'JAMES', 'CLERK', 7698, '1991-12-3', 950.00, NULL, 30),
       (7902, 'FORD', 'ANALYST', 7566, '1991-12-3', 3000.00, NULL, 20),
       (7934, 'MILLER', 'CLERK', 7782, '1992-1-23', 1300.00, NULL, 10);

#工资级别表
CREATE TABLE salgrade
(
    grade MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 comment '工资级别',
    losal DECIMAL(17, 2)     NOT NULL comment '该级别的最低工资',
    hisal DECIMAL(17, 2)     NOT NULL comment '该级别的最高工资'
);
INSERT INTO salgrade
VALUES (1, 700, 1200);
INSERT INTO salgrade
VALUES (2, 1201, 1400);
INSERT INTO salgrade
VALUES (3, 1401, 2000);
INSERT INTO salgrade
VALUES (4, 2001, 3000);
INSERT INTO salgrade
VALUES (5, 3001, 9999);

-- student
CREATE TABLE `student`
(
    `id`      int         NOT NULL DEFAULT '1',
    `NAME`    varchar(20) NOT NULL DEFAULT '',
    `chinese` float       NOT NULL DEFAULT '0',
    `english` float       NOT NULL DEFAULT '0',
    `math`    float       NOT NULL DEFAULT '0'
) ENGINE = InnoDB comment '学生表';


INSERT INTO `student`
VALUES (1, '韩顺平', 89, 78, 90),
       (2, '张飞', 67, 98, 56),
       (3, '宋江', 87, 78, 77),
       (4, '关羽', 88, 98, 90),
       (5, '赵云', 82, 84, 67),
       (6, '欧阳锋', 55, 85, 45),
       (7, '黄蓉', 75, 65, 30),
       (8, '韩信', 45, 65, 99);

