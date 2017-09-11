package com.lhh.test;

import java.util.Scanner;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.IEmployeeService;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;

/**
 * 
 * 测试连接数据库实现获取账号，密码，实现登录
 * 
 * @author 46512
 *
 */

public class Test6 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String name;
		String pass;
		IEmployeeService employeeService = new EmployeeServiceImpl();
		Employee employee = new Employee();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入账号：");
		name = sc.next();
		System.out.println("请输入密码：");
		pass = sc.next();
		try {
			if (employeeService.login(name, pass)) {
				System.out.println("登录成功！");
			}else{
				System.out.println("登录失败！");
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
