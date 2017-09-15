package com.lhh.ktv.model.dao;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.entity.Member;

public interface IMemberDao {
		// 增加会员
		void saveEember(Member member, Connection conn) throws DataAccessException;

		// 删除会员
		void deletesaveEember(Long id, Connection conn) throws DataAccessException;

		// 修改会员
		void updatesaveEember(Member member, Connection conn) throws DataAccessException;

		// 查找单个会员
		Member findsaveEember(Long id, Connection conn) throws DataAccessException;

		// 批量查找会员
		List<Member> findsaveEember(Connection conn) throws DataAccessException;

		// 动态查询会员
		List<Member> findsaveEember(List<String> conditions,Connection conn) throws DataAccessException;
}
