package com.lhh.ktv.model.service;

import java.util.List;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Reserve;

public interface IReserveService {
	// 增加功能
	public void addReserve(Reserve reserve) throws ServiceException;

	// 删除功能
	public void delReserve(Long id) throws ServiceException;

	// 修改功能
	public void updateReserve(Reserve reserve) throws ServiceException;

	// 查找功能
	public Reserve findReserve(Long id) throws ServiceException;

	// 批量查找
	List<Reserve> findReserve(Reserve reserve) throws ServiceException;

	// 动态查询
	List<Reserve> findReserve(List<String> conditions);
}
