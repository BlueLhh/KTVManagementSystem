package com.lhh.ktv.model.dao;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.entity.EOrder;

public interface IEOrderDao {
	// 增
	void saveEOrder(EOrder eOrder, Connection conn) throws DataAccessException;

	// 删
	void deleteEOrder(Long id, Connection conn) throws DataAccessException;

	// 修
	void updatEOrder(EOrder eOrder, Connection conn) throws DataAccessException;

	// 查
	EOrder findEOrder(Long id, Connection conn) throws DataAccessException;

	// 批量查找
	List<EOrder> findEOrder(Connection conn) throws DataAccessException;

	// 动态查询
	List<EOrder> findEOrder(List<String> conditions, Connection conn) throws DataAccessException;
}
