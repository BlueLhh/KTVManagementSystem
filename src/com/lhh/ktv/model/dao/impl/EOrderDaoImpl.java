package com.lhh.ktv.model.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.IEOrderDao;
import com.lhh.ktv.model.entity.EOrder;

public class EOrderDaoImpl implements IEOrderDao{

	@Override
	public void saveEOrder(EOrder eOrder, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEOrder(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatEOrder(EOrder eOrder, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EOrder findEOrder(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EOrder> findEOrder(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EOrder> findEOrder(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
