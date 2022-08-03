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
    id int primary key comment '班级编号', -- 必须具有主键约束或是unique约束
    name varchar(30) not null default ''
);
# 从表
create table classStudent
(
    id            int primary key comment '学生编号',
    class_room_id int,
    name          varchar(30),
    -- 指定外键关系
    foreign key (class_room_id) references classRoom (id)
);

-- 测试数据
insert into classRoom values (1,'java'),(2,'web');
insert into classStudent values (1,1,'tom'),(2,2,'jerry');
insert into classStudent values (3,1,'smith');
-- fail because classRoom 3 is not find
insert into classStudent values (3,3,'jack');
select * from classStudent;

-- 细节说明
# 1.外键指向的表的字段，要求是 primary key 或者是 unique
# 2.表的类型是innodb，这样的表才支持外键
# 3.外键字段的类型要和主键字段的类型一致（长度可以不同）
-- classStudent class_room_id int --> classRoom id int primary key
-- foreign key (class_room_id) references classRoom (id)
# 4.外键字段的值，必须在主键字段中出现过，或者为null【前提是外键字段允许为null】
# 5.一旦建立主外键的关系，数据不能随意删除了
-- 已经使用了的外键不可以随意删除或者更新 主表
-- Cannot delete or update a parent row: a foreign key constraint fails
delete from classRoom where id=1;
-- 从表 success
delete from classStudent where id=3;

desc classRoom;
desc classStudent;

# check 强制约束
-- 注意
# oracle 和 sql server均支持check，但是mysql5.7 目前还不支持check，只做语法校验，但不会生效
# 在mysql中实现check的功能，一般是在程序中控制，或者通过触发器完成

-- 建立测试表
create table `check_user`(
  id int primary key ,
  name varchar(30),
  sex char(5) comment 'man/woman' check ( sex in ('man','woman')),
  salary double comment '薪水' check ( salary between 1000 and 2000)
);
# 基本语法 列名 类型 check （check条件）
# 用于强制行数据必须满足的条件，假定在sal列上定义了check约束，并要求sal列值在1000~2000之间如果不再1000~2000之间就会提示出错
-- Check constraint 'check_user_chk_1' is violated
insert into check_user values (1,'student','cat',500); -- fail
-- Check constraint 'check_user_chk_2' is violated fail
insert into check_user values (1,'student','man',500);
-- success
insert into check_user values (1,'student','man',1111);

select * from check_user;

-- exercise
-- require
# 现有一个商店的数据库shop_db，记录客户及其购物情况，由下面三个表组成
# 商品goods（商品号goods_id，商品名goods_name，单价unit_price，商品类别category，供应商provider）
# 客户customer（客户号customer_id，姓名name，住址address，电邮email,性别sex，身份证card_id）
# 购买purchase（购买订单号order_id，客户号customer_id，商品号goods_id，购买数量nums）
# 客户的性别【男【女】 单价unit_price在1.0-9999.99之间
create database shop_db;

create table `goods`(
  goods_id int primary key comment '商品号',
  goods_name varchar(30) not null comment '商品名',
  unit_prise double check ( unit_prise between 1.0 and 9999.99) comment '单价',
  category int not null default 0 comment '商品类别',
  provider varchar(32) not null default '' comment '供应商'
) comment '商品';

create table `customer`(
    customer_id int primary key comment '客户号',
    name varchar(32) not null default '' comment '姓名',
    address varchar(64) comment '地址',
    email varchar(64) unique comment '电子邮件',
    sex char(1) check ( sex in ('男','女')) comment '性别',
    card_id char(18) comment '身份证'
) comment '客户';
# 购买订单号order_id，客户号customer_id，商品号goods_id，购买数量nums
create table `purchase`
(
    order_id int primary key comment '订单号',
    customer_id        int  comment '客户号',
    goods_id     int comment '商品号',
    nums       varchar(64) comment '购买数量',
    -- 建立外键
    foreign key (customer_id) references customer(customer_id),
    foreign key (goods_id) references goods(goods_id)
) comment '购买';

-- 查看
desc goods;
desc customer;
desc purchase;

-- 自增长
# auto_increment

-- 创建测试表
create table `auto_table`(
  id int primary key auto_increment,
  name varchar(32) not null default ''
);

desc auto_table;

insert into auto_table values (null,'auto_table1');
select * from auto_table;

# 详情
# 1.一般来说自增长是和primary key 配合使用的
# 2.自增长也可以单独使用【但是需要配合一个unique】
# 3.自增长修饰的字段为整数型的（虽然小数也可以但是非常非常少这样使用）
# 4.自增长默认从1开始，可以通过如下命令修改alter table 表名 auto_increment=新的开始值；
alter table auto_table auto_increment=5;
# 5.如果你添加数据时，给自增长字段（列）指定的有值，则以指定的值为准，如果指定了自增长，一般来说，就按照自增长的规则来添加数据.


-- 索引
-- 先执行creat_many_data.sql 生成数据 800w
-- 主键索引 唯一索引(unique) 普通索引(index) 全文索引
select COUNT(*) from tmp.emp;
select * from tmp.emp limit 1;
-- 没有索引  emp.ibd 文件大小 537.96 mb
-- 索引本身也会暂用空间
-- 创建索引 empno 后  emp.ibd 文件大小 672.1 mb
-- 创建索引 ename 后  emp.ibd 文件大小 856.7 mb
select * from tmp.emp where empno=123456; -- 2 s 286 ms

# empno_index 索引名称
# ON emp (ename) : 表示在 emp 表的 empno 列创建索引

-- 创建索引
create index empno_index on tmp.emp(empno);
-- 创建索引后
explain select * from tmp.emp where empno=1234568; -- 37ms

-- 创建索引前
select * from tmp.emp where ename='zIQisn'; -- 2 s 386 ms
-- 后
select * from tmp.emp where ename='zIQisn'; -- 40ms

-- 创建索引
create index ename_index on tmp.emp(ename);

-- 查看索引
show index in tmp.emp;

select ename,COUNT(*) count from tmp.emp group by ename having count>1 ;

-- 索引原理
# 没有索引为什么会慢因为全表扫描
# 使用索引为什么会快形成一个索引的数据结构，比如二叉树
# 索引的代价
#   磁盘占用
#   对dml（update delete insert）语句的效率影响

-- 索引类型
# 1.主键索引，主键自动的为主索引（类型Primary key）
# 2.唯一索引（UNIQUE）
# 3.普通索引（INDEX）
# 4.全文索引（FULLTEXT）【适用于MyISAM】
#     一般开发，不使用mysql自带的全文索引，而是使用∶全文搜索Solr和ElasticSearch（ES）
create table `primary_index`(
    id int primary key, -- 主键 同时也是索引 称为主键索引
    name varchar(32) unique -- 唯一约束 同时也是唯一索引
);

-- 索引使用
-- 查看
show indexes in tmp.emp;
-- 添加索引
-- 测试表
create table `index_table`(
    id int ,
    name varchar(32)
);
-- 查询是否有索引
show index from index_table;

-- 普通索引
# create [UNIQUE] index index_name on tbl_name (col name[(length)][ASCDESC],……);
# alter table table_name ADD INDEX[index_name](index col name,...)
-- 主键索引
# ALTER TABLE 表名 ADD PRIMARY KEY（列名，…）

# 添加唯一索引
create unique index id_unique on index_table(id);
# 添加普通索引
create index id_index on index_table(id);
-- 选择
# 如果某列的值是不会重复的 优先考虑唯一索引 否则 普通索引
alter table index_table add index id_index(id);
# 添加主键索引
alter table index_table add primary key (id);

-- 删除索引
-- index
# DROP INDEX index_name ON tbl_name;
# alter table table_name drop index index_name;
-- primary index
# alter table tb drop primary key;

-- index
drop index id_unique on index_table;
alter table index_table drop index id_index;
-- primary
alter table index_table drop primary key;

-- 修改索引 先删除 在创建

-- 查询索引
# show index(es) from table_name;
# show keys from table_name;
# desc table_name;
show index from index_table;
show indexes from index_table;
show keys from tmp.emp;
desc tmp.emp;

# index exercise
# require
# 创建一张订单表order（id号，商品名，订购人，数量）.要求id号为主键，请使用2种方式来创建主键
create table `order`(
    id int ,
    goods_name varchar(32) not null,
    buy_people varchar(20),
    number int,
    primary key (id)
);
alter table `order` add primary key (id);
# 创建一张特价菜谱表menu（id号，菜谱名，厨师，点餐人身份证，价格）.要求id号为主键，点餐人身份证是 unique 请使用两种方式来创建unique
create table `menu`(
    id int,
    name varchar(32),
    cook varchar(32),
    buy_id_card char(18) unique ,
    prise int
);
alter table menu add unique index card_unique(buy_id_card);
show index in menu;
# 创建一张运动员表sport_man （id号， 名字， 特长）. 要求id号为主键，名字为普通索引， 请使用2种方式来创建索引
create table `sport_man`(
    id int primary key ,
    name varchar(32) not null ,
    specialty varchar(32) comment '特长'
);
-- 普通索引
create index specialty_index on sport_man(specialty);
alter table sport_man add index specialty_index(specialty);
alter table sport_man add primary key (id);

show indexes in sport_man;

-- 哪些列上适合使用索引
# 1.较频繁的作为查询条件字段应该创建索引
# 2.唯一性太差的字段不适合单独创建索引，即使频繁作为查询条件
# 3.更新非常频繁的字段不适合创建索引
# 4.不会出现在WHERE子句中字段不该创建索引