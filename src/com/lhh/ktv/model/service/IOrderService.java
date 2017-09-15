package com.lhh.ktv.model.service;

import java.util.List;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Order;

public interface IOrderService {

	// 增加功能
	public void addOrder(Order order) throws ServiceException;

	// 删除功能
	public void delOrder(Long id) throws ServiceException;

	// 修改功能
	public void updateOrder(Order order) throws ServiceException;

	// 查找功能
	public Order findOrder(Long id) throws ServiceException;

	// 批量查找
	List<Order> findOrder(Order order) throws ServiceException;

	// 动态查询
	List<Order> findOrder(List<String> conditions);

}
