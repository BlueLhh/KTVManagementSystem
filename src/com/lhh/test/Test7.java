package com.lhh.test;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.impl.EmployeeDaoImpl;
import com.lhh.ktv.model.entity.Employee;

/**
 * 
 * 测试从数据库中查询
 * 
 * @author 46512
 *
 */

public class Test7 {
	public static void main(String[] args) {
		Connection conn = ConnectionFactory.getConnection();
		EmployeeDaoImpl test = new EmployeeDaoImpl();
		try {
			List<Employee> list = test.login(conn);
			for (Employee employee : list) {
				System.out.println(employee);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
