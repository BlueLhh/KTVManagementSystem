package com.lhh.test.emp;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;

/**
 * 
 * 测试查找功能
 * 
 * 
 * @author 46512
 *
 */
public class Test9 {
	public static void main(String[] args) {
		EmployeeServiceImpl emp = new EmployeeServiceImpl();
		Employee employee = new Employee();
		try {
			employee = emp.findEmployee(5L);
			System.out.println("查找成功！");
			System.out.println(employee);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
