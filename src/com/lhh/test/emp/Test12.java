package com.lhh.test.emp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;

/**
 * 
 * 动态查询
 * 
 * @author 46512
 *
 */
public class Test12 {
	public static void main(String[] args) {
		String name;
		String num;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("查询的姓名：");
		name = sc.next();
		System.out.println("查询的账号：");
		num = sc.next();
		EmployeeServiceImpl emp = new EmployeeServiceImpl();
		List<String> conditions = new ArrayList<String>();
		conditions.add("emp_name = '" + name + "'");
		conditions.add("emp_username = '" + num + "'");
		List<Employee> list = emp.findEmployee(conditions);
		System.out.println(list);
	}
}
