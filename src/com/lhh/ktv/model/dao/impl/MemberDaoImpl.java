package com.lhh.ktv.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lhh.ktv.common.JdbcTemplate;
import com.lhh.ktv.common.PreparedStatementSetter;
import com.lhh.ktv.common.RowCallBackHandler;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.model.dao.IMemberDao;
import com.lhh.ktv.model.entity.Member;

public class MemberDaoImpl implements IMemberDao {

	// 全局变量id
	Long id = 0L;

	//添加会员
	@Override
	public void saveEember(Member member, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String selectSQL = "select k_mem_mem_id_seq.nextval from dual";
		jt.query(selectSQL, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					id = rs.getLong(1);
				}
			}
		});

		String insertSQL = "insert into k_mem values(?,?,?,?,?)";
		jt.update(insertSQL, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
				pstmt.setString(2, member.getMemName());
				pstmt.setString(3, member.getMemGender());
				pstmt.setByte(4, member.getMemAge());
				pstmt.setString(5, member.getMemPhone());
			}
		});
	}

	@Override
	public void deletesaveEember(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatesaveEember(Member member, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Member findsaveEember(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findsaveEember(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findsaveEember(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
