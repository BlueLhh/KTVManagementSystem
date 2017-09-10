package com.lhh.ktv.model.service.impl;

import java.sql.Connection;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.common.DBUtils;
import com.lhh.ktv.common.JdbcTransaction;
import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.dao.IEmployeeDao;
import com.lhh.ktv.model.dao.impl.EmployeeDaoImpl;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService{

	private IEmployeeDao employeeDao = new EmployeeDaoImpl();
	
	@Override
	public void addEmployee(Employee employee) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try{
			//启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			employeeDao.saveEmployee(employee, conn);
			//提交事务
			trans.commit(conn);
		}catch(Exception e){
			//回滚事务
			trans.rollback(conn);
			throw new ServiceException("添加员工失败！");
		}finally{
			DBUtils.close(null, null, conn);
		}
		
	}

	@Override
	public void login(String userId, String password) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
