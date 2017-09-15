package com.lhh.ktv.model.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.IRoomDao;
import com.lhh.ktv.model.entity.Room;

public class RoomDaoImpl implements IRoomDao{

	@Override
	public void saveRoom(Room room, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoom(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatRoom(Room room, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Room findRoom(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> findRoom(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> findRoom(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
