package com.lhh.ktv.model.service.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.common.DBUtils;
import com.lhh.ktv.common.JdbcTransaction;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.dao.IEOrderDao;
import com.lhh.ktv.model.dao.impl.EOrderDaoImpl;
import com.lhh.ktv.model.entity.EOrder;
import com.lhh.ktv.model.service.IEOrderService;

public class EOrderServiceImpl implements IEOrderService{

	private IEOrderDao eOrderDao = new EOrderDaoImpl(); 
	
	//增
	@Override
	public void addIEorder(EOrder eOrder) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			eOrderDao.saveEOrder(eOrder, conn);
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

	//删
	@Override
	public void delIEorder(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			eOrderDao.deleteEOrder(id, conn);
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

	//改
	@Override
	public void updIEorder(EOrder eOrder) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;

		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			eOrderDao.updatEOrder(eOrder, conn);
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

	//查ID
	@Override
	public EOrder findIEorder(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		EOrder eOrder = new EOrder();
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			eOrder = eOrderDao.findEOrder(id, conn);
			
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
		return eOrder;
	}

	//批量查询
	@Override
	public List<EOrder> findIEorder(EOrder eOrder) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<EOrder> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = eOrderDao.findEOrder(conn);
			for (EOrder eOd : list) {
				System.out.println(eOd);
			}
			// 提交事务
			trans.commit(conn);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("查询失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		return list;
	}
	
	//动态查询
	@Override
	public List<EOrder> findIEorder(List<String> conditions) {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<EOrder> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = eOrderDao.findEOrder(conditions, conn);
			System.out.println("动态查询成功！");
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
