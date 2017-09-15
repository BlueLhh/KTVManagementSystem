package com.lhh.ktv.model.service;

import java.util.List;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.EOrder;

public interface IEOrderService {

		// 增加功能
		public void addIEorder(EOrder eOrder) throws ServiceException;

		// 删除功能
		public void delIEorder(Long id) throws ServiceException;

		// 修改功能
		public void updIEorder(EOrder eOrder) throws ServiceException;

		// 查找功能
		public EOrder findIEorder(Long id) throws ServiceException;

		// 批量查找
		List<EOrder> findIEorder(EOrder eOrder) throws ServiceException;

		// 动态查询
		List<EOrder> findIEorder(List<String> conditions);
	
}
