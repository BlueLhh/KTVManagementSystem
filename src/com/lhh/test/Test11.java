package com.lhh.test;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.impl.EmployeeDaoImpl;
import com.lhh.ktv.model.entity.Employee;

/**
 * 
 * 查找全部员工
 * 
 * @author 46512
 *
 */

public class Test11 {
	public static void main(String[] args) {
		Connection conn = ConnectionFactory.getConnection();
		EmployeeDaoImpl test = new EmployeeDaoImpl();

		List<Employee> list;
		try {
			list = test.findEmployees(conn);
			for (Employee employee : list) {
				System.out.println(employee);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
