package com.lhh.ktv.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	// 添加会员
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

	// 删除数据
	@Override
	public void deletesaveEember(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("deletesaveEember id=" + id);
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "delete from k_mem where mem_id = ?";
		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		});
	}

	// 更改会员信息
	@Override
	public void updatesaveEember(Member member, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "update k_mem " + "set mem_name = ?,mem_gender = ?,mem_age = ?,mem_phone = ? "
				+ "where mem_id = ?";

		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemName());
				pstmt.setString(2, member.getMemGender());
				pstmt.setByte(3, member.getMemAge());
				pstmt.setString(4, member.getMemPhone());
				pstmt.setLong(5, member.getMemId());
			}
		});
	}

	// 按ID查找会员
	@Override
	public Member findsaveEember(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		final Member member = new Member();
		String sql = "select mem_id,mem_name,mem_gender,mem_age,mem_phone from k_mem where mem_id = ?";
		jt.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		}, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				if (rs.next()) {
					member.setMemId(rs.getLong(1));
					member.setMemName(rs.getString(2));
					member.setMemGender(rs.getString(3));
					member.setMemAge(rs.getByte(4));
					member.setMemPhone(rs.getString(5));
				}
			}
		});

		return member;
	}

	// 批量查询会员
	@Override
	public List<Member> findsaveEember(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		final List<Member> list = new ArrayList<Member>();
		String sql = "select mem_id,mem_name,mem_gender,mem_age,mem_phone from k_mem";
		jt.query(sql, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Member member = new Member();
					member.setMemId(rs.getLong(1));
					member.setMemName(rs.getString(2));
					member.setMemGender(rs.getString(3));
					member.setMemAge(rs.getByte(4));
					member.setMemPhone(rs.getString(5));
					list.add(member);
				}
			}
		});
		return list;
	}

	// 动态查询会员(可以当成是模糊查询)
	@Override
	public List<Member> findsaveEember(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		List<Member> list = new ArrayList<Member>();
		String sql = "select mem_id,mem_name,mem_gender,mem_age,mem_phone " + "from k_mem " + "where 1 = 1";
		if (conditions.size() > 0) {
			for (String condition : conditions) {
				sql += " and ";
				sql += condition;
			}
		}
		jt.query(sql, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				while (rs.next()) {
					Member member = new Member();
					member.setMemId(rs.getLong(1));
					member.setMemName(rs.getString(2));
					member.setMemGender(rs.getString(3));
					member.setMemAge(rs.getByte(4));
					member.setMemPhone(rs.getString(5));
					list.add(member);
				}
			}
		});
		return list;
	}

}
