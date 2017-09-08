--2017年9月8日
--创建一个名为：ktv 的用户，在Oracle中，用户即为一个库的名称
--登录超级用户
conn / as sysdba
--在超级用户下创建一个名为ktv的用户，密码为ktv
create user ktv identified by ktv;
--授予连接数据库和获取资源的权限
grant connect,resource to ktv;

--创建员工表
--员工职位 0表示经理，1表示前台
create table ktv.t_emp(
	emp_id number(10)
		constraint t_emp_emp_id_pk primary key,
	emp_name varchar2(25)
		constraint t_emp_emp_name_nn not null,
	emp_gender varchar2(2)
		constraint t_emp_emp_gender_check check(emp_gender in('男','女')),
	emp_age number(3),
	emp_phone varchar2(11)
		constraint t_emp_emp_phone_uk unique,
	emp_post varchar2(1)
		constraint t_emp_emp_post_check check(emp_post in('0','1'))
);

--创建会员表
--会员：会员编号，会员姓名，会员性别，会员年龄，联系方式
create table ktv.t_mem(
	mem_id number(10)
		constraint t_mem_mem_id_pk primary key,
	mem_name varchar2(25)
		constraint t_mem_mem_name_nn not null,
	mem_gender varchar2(4)
		constraint t_mem_mem_gender_check check(mem_gender in('男','女')),
	mem_age number(3),
	mem_phone varchar(11)
		constraint t_mem_mem_phone_uk unique
);