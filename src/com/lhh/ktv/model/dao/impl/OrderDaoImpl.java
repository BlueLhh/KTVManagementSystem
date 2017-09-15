package com.lhh.ktv.model.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.IOrderDao;
import com.lhh.ktv.model.entity.Order;

public class OrderDaoImpl implements IOrderDao{

	@Override
	public void saveOrder(Order order, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatOrder(Order order, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order findOrder(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findOrder(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findOrder(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
