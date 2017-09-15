package com.lhh.ktv.model.service.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.common.DBUtils;
import com.lhh.ktv.common.JdbcTransaction;
import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.dao.IMemberDao;
import com.lhh.ktv.model.dao.impl.MemberDaoImpl;
import com.lhh.ktv.model.entity.Member;
import com.lhh.ktv.model.service.IMemberService;

public class MemberServiceImpl implements IMemberService{

	private IMemberDao memberDao = new MemberDaoImpl();
	
	//添加会员
	@Override
	public void addMem(Member member) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			memberDao.saveEember(member, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
//			throw new ServiceException("添加员工失败！");
			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, conn);
		}
	}

	@Override
	public void delMem(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMem(Member member) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member findMem(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findMem(Member member) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findMem(List<String> conditions) {
		// TODO Auto-generated method stub
		return null;
	}

}
