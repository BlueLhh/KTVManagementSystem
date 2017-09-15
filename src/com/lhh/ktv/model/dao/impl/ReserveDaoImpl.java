package com.lhh.ktv.model.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.IReserveDao;
import com.lhh.ktv.model.entity.Reserve;

public class ReserveDaoImpl implements IReserveDao{

	@Override
	public void saveReserve(Reserve reserve, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReserve(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatReserve(Reserve reserve, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reserve findReserve(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserve> findReserve(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserve> findReserve(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
