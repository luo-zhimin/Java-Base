/*
 * Copyright (c) luoZhiMin 2022.7.31.11.47.0
 */

# 约束
# 约束用于确保数据库的数据满足特定的商业规则。
# 在mysql中，约束包括∶not null、unique，primary key，foreign key，和check 五种.

# primary key(主键)-基本使用
create table pk_table(
  id int primary key , -- ID表示主键
  name varchar(30),
  email varchar(30)
);

-- 多次执行 Duplicate entry '1' for key 'pk_table.PRIMARY'
insert into pk_table values (1,'firstKey','22@qq.com');

-- 细节说明
# 1.primary key不能重复而且不能为null。
-- Column 'id' cannot be null
insert into pk_table values (null,'firstKey','22@qq.com');
# 2.一张表最多只能有一个主键，但可以是复合主键
-- id name 组合成为复合主键
create table pk_many_table
(
    id    int,
    name  varchar(30),
    email varchar(30),
    primary key (`id`, `name`) -- 复合主键
);
insert into pk_many_table values (1,'22','22@qq.com');
insert into pk_many_table values (1,'33','22@qq.com');
-- Duplicate entry '1-33' for key 'pk_many_table.PRIMARY'
insert into pk_many_table values (1,'33','22@qq.com');

# 3.主键的指定方式有两种
#     直接在字段名后指定字段名 primary key
# id int primary key
#     在表定义最后写primary key（列名）；
-- primary key (`id`, `name`)
# 4.使用desc表名，可以看到primary key的情况
desc pk_table;
desc pk_many_table;
# 5.在实际开发中，每个表往往都会设计一个主键.

# not null(非空)
-- 修改前
INSERT INTO pk_table (id, name, email) VALUES (2, 'secondKey', null);
select * from pk_table;

-- 修改 pk_table email 为非空字段
-- 修改后
-- Data truncation: Invalid use of NULL value
-- update change null data
update pk_table set email ='' where email is null ;
alter table pk_table modify email varchar(30) not null;
--  Column 'email' cannot be null
INSERT INTO pk_table (id, name, email) VALUES (3, 'thirdKey', null);

# unique(唯一)
# 细节（注意）∶
# 1.如果没有指定 not null，则 unique 字段可以有多个null (唯一约束可以为空)
-- 加了not null 之后 使用效果和primary key 一样
# 2.一张表可以有多个unique字段
create table unique_table
(
    id    int unique ,
    name  varchar(30),
    email varchar(30)
);
INSERT INTO unique_table (id, name, email) VALUES (1, 'tom', 'baidu@@com');
--  Duplicate entry '1' for key 'unique_table.id'
INSERT INTO unique_table (id, name, email) VALUES (1, 'tom', 'baidu@@com');
desc unique_table;


# foreign key(外键)
# 用于定义主表和从表之间的关系∶外键约束要定义在从表上，主表则必须具有主键约束或是unique约束，
# 当定义外键约束后，要求外键列数据必须在主表的主键列存在或是为null

-- 创建测试表
# 主表
create table classRoom(
    id int primary key ,
    name varchar(30)
);
# 从表
create table classStudent
(
    id   int primary key,
    class_room_id int,
    name varchar(30)
);

-- 细节说明
# 1.外键指向的表的字段，要求是 primary key 或者是 unique
# 2.表的类型是innodb，这样的表才支持外键
# 3.外键字段的类型要和主键字段的类型一致（长度可以不同）
# 4.外键字段的值，必须在主键字段中出现过，或者为null【前提是外键字段允许为null】
# 5.一旦建立主外键的关系，数据不能随意删除了

# check