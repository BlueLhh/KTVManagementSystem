package com.lhh.ktv.model.service.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.common.DBUtils;
import com.lhh.ktv.common.JdbcTransaction;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.dao.IGoodsDao;
import com.lhh.ktv.model.dao.impl.GoodsDaoImpl;
import com.lhh.ktv.model.entity.Goods;
import com.lhh.ktv.model.service.IGoodsService;

public class GoodsServiceImpl implements IGoodsService{

	private IGoodsDao goodsDao = new GoodsDaoImpl();
	
	
	//增
	@Override
	public void addGoods(Goods goods) throws ServiceException { 
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			goodsDao.saveGoods(goods, conn);
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

	//删
	@Override
	public void delGoods(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			goodsDao.deleteGoods(id, conn);
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
	public void updateGoods(Goods goods) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;

		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			goodsDao.updatGoods(goods, conn);
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
	public Goods findGoods(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		Goods goods = new Goods();
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			goods = goodsDao.findGoods(id, conn);
			System.out.println("查找房间成功！");
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("查找房间失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		return goods;
	}

	//批量查询
	@Override
	public List<Goods> findGoods(Goods goods) throws ServiceException {
		// TODO Auto-generated method stub
		
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<Goods> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = goodsDao.findGoods(conn);
			for (Goods gds : list) {
				System.out.println(gds);
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

	//动态查询（模糊查询）
	@Override
	public List<Goods> findGoods(List<String> conditions) {
		// TODO Auto-generated method stub
		
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<Goods> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = goodsDao.findGoods(conditions, conn);
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
