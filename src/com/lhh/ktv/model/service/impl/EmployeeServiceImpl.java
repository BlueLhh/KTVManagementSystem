package com.lhh.ktv.model.service.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.common.DBUtils;
import com.lhh.ktv.common.JdbcTransaction;
import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.dao.IEmployeeDao;
import com.lhh.ktv.model.dao.impl.EmployeeDaoImpl;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {

	private IEmployeeDao employeeDao = new EmployeeDaoImpl();

	// 添加员工
	@Override
	public void addEmployee(Employee employee) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			employeeDao.saveEmployee(employee, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("添加员工失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}

	}

	// 登录
	@Override
	public boolean login(String username, String password) throws ServiceException {
		// TODO Auto-generated method stub
		boolean ifright = false;// 初始为错误
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			List<Employee> list = employeeDao.login(conn);
			for (Employee employee : list) {
				if (employee.getUsername().trim().equals(username)) {
					ifright = true;
					if (employee.getPassword().trim().equals(password)) {
						ifright = true;
						break;
					} else {
						ifright = false;
						continue;
					}
				} else {
					ifright = false;
					continue;
				}
			}
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("登录失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		return ifright;
	}
}
