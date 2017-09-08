--2017年9月8日
--创建一个名为：ktv 的用户，在Oracle中，用户即为一个库的名称
--登录超级用户
conn / as sysdba
--在超级用户下创建一个名为ktv的用户，密码为ktv
create user ktv identified by ktv;
--授予连接数据库和获取资源的权限
grant connect,resource to ktv;