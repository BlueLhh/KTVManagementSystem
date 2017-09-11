package com.lhh.test;


import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;

/**
 * 
 * 删除测试
 * 
 * @author 46512
 *
 */
public class Test8 {
	public static void main(String[] args) {
		
		EmployeeServiceImpl emp = new EmployeeServiceImpl();
		try {
			emp.delEmployee(3L);
			System.out.println("删除成功！");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
