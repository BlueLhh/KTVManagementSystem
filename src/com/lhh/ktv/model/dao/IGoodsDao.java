package com.lhh.ktv.model.dao;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.entity.Goods;

public interface IGoodsDao {
	// 增
	void saveGoods(Goods goods, Connection conn) throws DataAccessException;

	// 删
	void deleteGoods(Long id, Connection conn) throws DataAccessException;

	// 修
	void updatGoods(Goods goods, Connection conn) throws DataAccessException;

	// 查
	Goods findGoods(Long id, Connection conn) throws DataAccessException;

	// 批量查找
	List<Goods> findGoods(Connection conn) throws DataAccessException;

	// 动态查询
	List<Goods> findGoods(List<String> conditions, Connection conn) throws DataAccessException;
}
