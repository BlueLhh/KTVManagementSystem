package com.lhh.ktv.model.service.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.common.DBUtils;
import com.lhh.ktv.common.JdbcTransaction;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.dao.IRoomDao;
import com.lhh.ktv.model.dao.impl.RoomDaoImpl;
import com.lhh.ktv.model.entity.Room;
import com.lhh.ktv.model.service.IRoomService;

public class RoomServiceImpl implements IRoomService{

	private IRoomDao roomDao = new RoomDaoImpl();
	
	@Override
	public void addRoom(Room room) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			roomDao.saveRoom(room, conn);
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
	public void delRoom(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			roomDao.deleteRoom(id, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("删除包厢失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		
	}

	//更新
	@Override
	public void updateRoom(Room room) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;

		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			roomDao.updatRoom(room, conn);
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

	//ID查询
	@Override
	public Room findRoom(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		Room room = new Room();
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			room = roomDao.findRoom(id, conn);
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
		return room;
	}

	//批量查询
	@Override
	public List<Room> findReserve(Room room) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<Room> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = roomDao.findRoom(conn);
			for (Room rm : list) {
				System.out.println(rm);
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

	//模糊查询要在添加中添加查询的方法
	@Override
	public List<Room> findReserve(List<String> conditions) {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<Room> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = roomDao.findRoom(conditions, conn);
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
