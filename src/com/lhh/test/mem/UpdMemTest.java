package com.lhh.test.mem;

import java.util.Scanner;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Member;
import com.lhh.ktv.model.service.IMemberService;
import com.lhh.ktv.model.service.impl.MemberServiceImpl;

public class UpdMemTest {
	public static void main(String[] args) {
		IMemberService mem = new MemberServiceImpl();
		Member member = new Member();
		
		String name;
		String gender;
		byte age;
		String phone;
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
		System.out.println("ID：");
		id = sc.nextLong();
		
		member.setMemName(name);
		member.setMemGender(gender);
		member.setMemAge(age);
		member.setMemPhone(phone);
		member.setMemId(id);
		
		try {
			mem.updateMem(member);
			System.out.println("更新会员信息成功！");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
