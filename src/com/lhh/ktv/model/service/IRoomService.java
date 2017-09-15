package com.lhh.ktv.model.service;

import java.util.List;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Room;

public interface IRoomService {

	// 增加功能
	public void addRoom(Room room) throws ServiceException;

	// 删除功能
	public void delRoom(Long id) throws ServiceException;

	// 修改功能
	public void updateRoom(Room room) throws ServiceException;

	// 查找功能
	public Room findRoom(Long id) throws ServiceException;

	// 批量查找
	List<Room> findReserve(Room room) throws ServiceException;

	// 动态查询
	List<Room> findReserve(List<String> conditions);

}
