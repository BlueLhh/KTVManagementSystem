package com.lhh.ktv.model.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.IEmployeeDao;
import com.lhh.ktv.model.entity.Employee;

public class EmployeeDaoImpl implements IEmployeeDao{

	@Override
	public void saveEmployee(Employee employee, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployee(Employee employee, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee findEmployee(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findEmployees(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
