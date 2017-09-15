package com.lhh.ktv.model.dao;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.entity.Order;

public interface IOrderDao {
	// 增
	void saveOrder(Order order, Connection conn) throws DataAccessException;

	// 删
	void deleteOrder(Long id, Connection conn) throws DataAccessException;

	// 修
	void updatOrder(Order order, Connection conn) throws DataAccessException;

	// 查
	Order findOrder(Long id, Connection conn) throws DataAccessException;

	// 批量查找
	List<Order> findOrder(Connection conn) throws DataAccessException;

	// 动态查询
	List<Order> findOrder(List<String> conditions, Connection conn) throws DataAccessException;
}
