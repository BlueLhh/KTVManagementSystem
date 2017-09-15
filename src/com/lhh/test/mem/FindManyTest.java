package com.lhh.test.mem;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.impl.MemberDaoImpl;
import com.lhh.ktv.model.entity.Member;

public class FindManyTest {
	public static void main(String[] args) {
		Connection conn = ConnectionFactory.getConnection();
		MemberDaoImpl memDao = new MemberDaoImpl();
		
		List<Member> list;
		try {
			list = memDao.findsaveEember(conn);
			System.out.println("查找全部会员成功！");
			for (Member member : list) {
				System.out.println(member);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
