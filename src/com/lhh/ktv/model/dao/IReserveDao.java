package com.lhh.ktv.model.dao;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.entity.Reserve;

public interface IReserveDao {
	// 增
	void saveReserve(Reserve reserve, Connection conn) throws DataAccessException;

	// 删
	void deleteReserve(Long id, Connection conn) throws DataAccessException;

	// 修
	void updatReserve(Reserve reserve, Connection conn) throws DataAccessException;

	// 查
	Reserve findReserve(Long id, Connection conn) throws DataAccessException;

	// 批量查找
	List<Reserve> findReserve(Connection conn) throws DataAccessException;

	// 动态查询
	List<Reserve> findReserve(List<String> conditions, Connection conn) throws DataAccessException;
}
