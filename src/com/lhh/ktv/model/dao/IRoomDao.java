package com.lhh.ktv.model.dao;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.entity.Room;

public interface IRoomDao {
	// 增
	void saveRoom(Room room, Connection conn) throws DataAccessException;

	// 删
	void deleteRoom(Long id, Connection conn) throws DataAccessException;

	// 修
	void updatRoom(Room room, Connection conn) throws DataAccessException;

	// 查
	Room findRoom(Long id, Connection conn) throws DataAccessException;

	// 批量查找
	List<Room> findRoom(Connection conn) throws DataAccessException;

	// 动态查询
	List<Room> findRoom(List<String> conditions, Connection conn) throws DataAccessException;
}
