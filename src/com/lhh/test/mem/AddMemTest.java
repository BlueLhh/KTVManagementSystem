package com.lhh.test.mem;

import java.util.Scanner;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Member;
import com.lhh.ktv.model.service.IMemberService;
import com.lhh.ktv.model.service.impl.MemberServiceImpl;

public class AddMemTest {
	public static void main(String[] args) {
		String name;
		String gender;
		byte age;
		String phone;
		
		//建立服务接口类类  服务实现类
		IMemberService memberService = new MemberServiceImpl();
		Member member = new Member();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("请输入会员姓名：");
		name = input.next();
		System.out.println("请输入会员性别：");
		gender = input.next();
		System.out.println("请输入会员年龄：");
		age = input.nextByte();
		System.out.println("请输入联系方式：");
		phone = input.next();
		
		member.setMemName(name);
		member.setMemGender(gender);
		member.setMemAge(age);
		member.setMemPhone(phone);
		
		try {
			memberService.addMem(member);
			System.out.println("添加会员成功！");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
