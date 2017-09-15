package com.lhh.ktv.model.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.IGoodsDao;
import com.lhh.ktv.model.entity.Goods;

public class GoodsDaoImpl implements IGoodsDao{

	@Override
	public void saveGoods(Goods goods, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGoods(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatGoods(Goods goods, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Goods findGoods(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findGoods(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findGoods(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
