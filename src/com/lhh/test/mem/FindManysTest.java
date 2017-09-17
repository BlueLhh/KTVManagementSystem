package com.lhh.test.mem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lhh.ktv.model.entity.Member;
import com.lhh.ktv.model.service.impl.MemberServiceImpl;

public class FindManysTest {
	public static void main(String[] args) {
		String name;
		//String phone;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("查询的姓名："); 
		name = sc.next();
		//System.out.println("查询的手机号码：");
		//phone = sc.next();
		MemberServiceImpl mem = new MemberServiceImpl();
		List<String> conditions = new ArrayList<String>();
		conditions.add("mem_name like '%" + name + "%'");
		//conditions.add("mem_phone like '%" + phone + "%'");
		List<Member> list = mem.findMem(conditions);
		System.out.println(list);
	}
}
