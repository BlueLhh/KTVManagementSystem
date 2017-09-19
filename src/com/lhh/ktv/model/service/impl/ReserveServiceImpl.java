package com.lhh.ktv.model.service.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.common.DBUtils;
import com.lhh.ktv.common.JdbcTransaction;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.dao.IReserveDao;
import com.lhh.ktv.model.dao.impl.ReserveDaoImpl;
import com.lhh.ktv.model.entity.Reserve;
import com.lhh.ktv.model.service.IReserveService;

public class ReserveServiceImpl implements IReserveService {

	private IReserveDao reserveDao = new ReserveDaoImpl();
	
	// 增
	@Override
	public void addReserve(Reserve reserve) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			reserveDao.saveReserve(reserve, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			// throw new ServiceException("添加员工失败！");
			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, conn);
		}
	}

	// 删
	@Override
	public void delReserve(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			reserveDao.deleteReserve(id, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("删除失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
	}

	// 改
	@Override
	public void updateReserve(Reserve reserve) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;

		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			reserveDao.updatReserve(reserve, conn);
			// 提交事务
			trans.commit(conn);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			// 回滚事务
			trans.rollback(conn);
			//throw new ServiceException("更新员工失败！");
			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, conn);
		}
	}

	// 查ID
	@Override
	public Reserve findReserve(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		Reserve reserve = new Reserve();
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			reserve = reserveDao.findReserve(id, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("查找会员失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		return reserve;
	}

	// 批量
	@Override
	public List<Reserve> findReserve(Reserve reserve) throws ServiceException {
		// TODO Auto-generated method stub
		
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<Reserve> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = reserveDao.findReserve(conn);
			for (Reserve res : list) {
				System.out.println(res);
			}
			// 提交事务
			trans.commit(conn);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("查询失败失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		return list;
	}

	// 动态
	@Override
	public List<Reserve> findReserve(List<String> conditions) {
		// TODO Auto-generated method stub
		
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<Reserve> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = reserveDao.findReserve(conditions, conn);
			// 提交事务
			trans.commit(conn);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			try {
				throw new ServiceException("动态查询失败失败！");
			} catch (ServiceException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
		} finally {
			DBUtils.close(null, null, conn);
		}
		
		return list;
	}

}
