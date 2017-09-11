package com.lhh.ktv.model.dao;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.entity.Employee;

public interface IEmployeeDao {
	// 登录
	List<Employee> login(Connection conn) throws DataAccessException;

	// 增加员工
	void saveEmployee(Employee employee, Connection conn) throws DataAccessException;

	// 删除员工
	void deleteEmployee(Long id, Connection conn) throws DataAccessException;

	// 修改员工
	void updateEmployee(Employee employee, Connection conn) throws DataAccessException;

	// 查找单个员工
	Employee findEmployee(Long id, Connection conn) throws DataAccessException;

	// 批量查找员工
	List<Employee> findEmployees(Connection conn) throws DataAccessException;

	// 动态查询员工
	List<Employee> findEmployees(List<String> conditions,Connection conn) throws DataAccessException;
}
