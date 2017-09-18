package com.lhh.test.mem;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Member;
import com.lhh.ktv.model.service.impl.MemberServiceImpl;

public class FindOneMemTest {
	public static void main(String[] args) {
		MemberServiceImpl mem = new MemberServiceImpl();
		Member member = new Member();
		try {
			member = mem.findMem(2L);
			System.out.println(member);
			System.out.println(member.getMemName());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
