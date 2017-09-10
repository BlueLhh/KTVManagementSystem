package com.lhh.ktv.model.service;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;

/**
 * 
 * 员工服务类
 * 实现登录和增加的功能
 * @author 46512
 *
 */
public interface IEmployeeService {
	//增加员工功能
	public void addEmployee(Employee employee) throws ServiceException;
	//登录功能
	public void login(String username, String password) throws ServiceException;
	//删除功能
}
