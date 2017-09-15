package com.lhh.ktv.model.service;

import java.util.List;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Member;

/**
 * 
 * 会员服务接口类
 * 
 * @author 46512
 *
 */

public interface IMemberService {

	// 增加功能
	public void addMem(Member member) throws ServiceException;

	// 删除功能
	public void delMem(Long id) throws ServiceException;

	// 修改功能
	public void updateMem(Member member) throws ServiceException;

	// 查找功能
	public Member findMem(Long id) throws ServiceException;

	// 批量查找
	List<Member> findMem(Member member) throws ServiceException;

	// 动态查询
	List<Member> findMem(List<String> conditions);

}
