package com.lhh.ktv.util;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;

/**
 * 
 * 删除信息类
 * 
 * @author 46512
 *
 */

public class DelInformation {

	public static void delEmpInfo(Long id) {

		EmployeeServiceImpl emp = new EmployeeServiceImpl();
		
		try {
			emp.delEmployee(id);
			System.out.println("删除成功！");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
