package com.lhh.ktv.model.service;

import java.util.List;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Goods;

public interface IGoodsService {
	// 增加功能
	public void addGoods(Goods goods) throws ServiceException;

	// 删除功能
	public void delGoods(Long id) throws ServiceException;

	// 修改功能
	public void updateGoods(Goods goods) throws ServiceException;

	// 查找功能
	public Goods findGoods(Long id) throws ServiceException;

	// 批量查找
	List<Goods> findGoods(Goods goods) throws ServiceException;

	// 动态查询
	List<Goods> findGoods(List<String> conditions);
}
