package com.lhh.test.mem;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.service.IMemberService;
import com.lhh.ktv.model.service.impl.MemberServiceImpl;

public class DelMemTest {
	
	public static void main(String[] args) {
		
		IMemberService mem = new MemberServiceImpl();
		
		try {
			mem.delMem(3L);
			System.out.println("删除会员成功！");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
