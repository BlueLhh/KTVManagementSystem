package com.lhh.test;

import java.util.Scanner;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;

/**
 * 
 * 测试更新功能
 * 
 * @author 46512
 *
 */
public class Test10 {
	public static void main(String[] args) {
		EmployeeServiceImpl emp = new EmployeeServiceImpl();
		Employee employee = new Employee();
		String name;
		String gender;
		byte age;
		String phone;
		String post;
		String username;
		String password;
		Long id;
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("姓名：");
		name = sc.next();
		System.out.println("性别：");
		gender = sc.next();
		System.out.println("年龄：");
		age = sc.nextByte();
		System.out.println("手机：");
		phone = sc.next();
		System.out.println("职位：");
		post = sc.next();
		System.out.println("账号：");
		username = sc.next();
		System.out.println("密码：");
		password = sc.next();
		System.out.println("ID：");
		id = sc.nextLong();
		
		employee.setEmpName(name);
		employee.setEmpGender(gender);
		employee.setEmpAge(age);
		employee.setEmpPhone(phone);
		employee.setEmpPost(post);
		employee.setUsername(username);
		employee.setPassword(password);
		employee.setEmpId(id);
		
		try {
			emp.updateEmployee(employee);
			System.out.println("更改成功！");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
