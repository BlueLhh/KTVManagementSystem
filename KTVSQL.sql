--2017年9月8日
--创建一个名为：ktv 的用户，在Oracle中，用户即为一个库的名称
--登录超级用户
conn / as sysdba
--在超级用户下创建一个名为ktv的用户，密码为ktv
create user ktv identified by ktv;
--授予连接数据库和获取资源的权限
grant connect,resource to ktv;

--创建员工表
--员工：员工编号，员工姓名，员工性别，员工年龄，联系方式，员工职别（前台，经理（经理拥有最高职权））
--员工职位 0表示经理，1表示前台
create table ktv.k_emp(
	emp_id number(10)
		constraint k_emp_emp_id_pk primary key,
	emp_name varchar2(25)
		constraint k_emp_emp_name_nn not null,
	emp_gender varchar2(2)
		constraint k_emp_emp_gender_check check(emp_gender in('男','女')),
	emp_age number(3),
	emp_phone varchar2(11)
		constraint k_emp_emp_phone_uk unique,
	emp_post varchar2(1)
		constraint k_emp_emp_post_check check(emp_post in('0','1'))
);

--创建会员表
--会员：会员编号，会员姓名，会员性别，会员年龄，联系方式
create table ktv.k_mem(
	mem_id number(10)
		constraint k_mem_mem_id_pk primary key,
	mem_name varchar2(25)
		constraint k_mem_mem_name_nn not null,
	mem_gender varchar2(4)
		constraint k_mem_mem_gender_check check(mem_gender in('男','女')),
	mem_age number(3),
	mem_phone varchar(11)
		constraint k_mem_mem_phone_uk unique
);

--2017年9月9日
--创建营业额表
--用于管理包房信息
--包房：包房编号，包房种类，包房费用
create table ktv.k_room(
	room_id number(10)
		constraint k_room_room_id_pk primary key,
	room_kind varchar2(8),
	room_price number(6,2)
);

--创建商品表
--用于管理商品
--商品：商品编号，商品名称，商品价格，商品库存
create table ktv.k_goods(
	goods_id number(10)
		constraint k_goods_goods_id_pk primary key,
	goods_name varchar2(20),
	goods_price number(6,2),
	goods_count number(5)
);

--创建订单表
--订单：订单编号，包房编号，消费者名字，开包时间，结账时间，包房费用，商品消费总额，消费合计
create table ktv.k_order(
	order_id number(10)
		constraint k_order_order_id_pk primary key,
	order_room_id number(10)
		constraint k_order_order_room_id_fk
		references k_room(room_id),
	order_reserve_pname varchar2(20),
	order_opendate date default to_char(sysdate+'','yyyy-mm-dd HH24:MI:SS')
		constraint k_order_order_opendate_nn not null,
	order_enddate date default to_char(sysdate+'','yyyy-mm-dd HH24:MI:SS')
		constraint k_order_order_enddate_nn not null,
	order_room_price number(8,2),
	order_amtall number(8,2),
	order_allamtall number(8,2)
);

--创建子订单
--子订单：子订单编号，订单编号，商品编号，商品名称，数量，单价，金额
create table ktv.k_eorder(
	eorder_id number(10)
		constraint k_eorder_eorder_id_pk primary key,
	eorder_order_id number(10)
		constraint k_eorder_eorder_order_id_fk
		references k_order(order_id),
	eorder_goods_id number(10)
		constraint k_eorder_eorder_goods_id_fk
		references k_goods(goods_id),
	eorder_goods_name varchar2(20),
	eorder_count number(5),
	eorder_goods_price number(6,2),
	eorder_money number(6,2)
);

--创建预定信息表
--预定信息：预定信息编号，包房编号，预定时间，保留时间，预定人姓名，会员ID（空），预订人电话
create table ktv.k_reserve(
	reserve_id number(10)
		constraint k_reserve_reserve_id_pk primary key,
	reserve_room_id number(10)
		constraint k_reserve_reserve_room_id_fk
		references k_room(room_id),
	reserve_date date default to_char(sysdate+'','yyyy-mm-dd HH24:MI:SS')
		constraint k_reserve_reserve_date_nn not null,
	reserve_enddate date default to_char(sysdate+2/24+'','yyyy-mm-dd HH24:MI:SS')
		constraint k_reserve_reserve_enddate_nn not null,
	reserve_pname varchar2(20),
	reserve_mem_id number(10),
	reserve_phone number(11)
);



