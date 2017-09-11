package com.lhh.test;

import java.util.Scanner;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.IEmployeeService;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;

/**
 * 
 * 测试将数据插入数据库
 * 
 * @author 46512
 *
 */
public class Test5 {
	public static void main(String[] args) {
	
		String name;
		String gender;
		byte age;
		String phone;
		String post;
		String username;
		String password;
		IEmployeeService employeeService = new EmployeeServiceImpl();
		Employee employee = new Employee();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("请输入员工姓名：");
		name = input.next();
		System.out.println("请输入员工性别：");
		gender = input.next();
		System.out.println("请输入员工年龄：");
		age = input.nextByte();
		System.out.println("请输入联系方式：");
		phone = input.next();
		System.out.println("请输入员工职别(0或1)：");
		post = input.next();
		System.out.println("请输入员工账号：");
		username = input.next();
		System.out.println("请输入账号密码：");
		password = input.next();
		employee.setEmpName(name);
		employee.setEmpGender(gender);
		employee.setEmpAge(age);
		employee.setEmpPhone(phone);
		employee.setEmpPost(post);
		employee.setUsername(username);
		employee.setPassword(password);
		try {
			employeeService.addEmployee(employee);
			System.out.println("添加员工成功！");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
