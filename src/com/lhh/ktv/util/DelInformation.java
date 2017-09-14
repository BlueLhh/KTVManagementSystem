package com.lhh.ktv.util;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.view.DelEmpFrame;

/**
 * 
 * 删除信息类
 * 
 * @author 46512
 *
 */

public class DelInformation {

	public static void delEmpInfo() {

		Long id;
		
		EmployeeServiceImpl emp = new EmployeeServiceImpl();

		id = DelEmpFrame.getGetDelID();
		
		try {
			emp.delEmployee(id);
			System.out.println("删除成功！");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
