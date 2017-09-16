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
import com.lhh.ktv.model.dao.IReserveDao;
import com.lhh.ktv.model.entity.Reserve;

public class ReserveDaoImpl implements IReserveDao {

	Long id;

	// 增
	@Override
	public void saveReserve(Reserve reserve, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String selectSQL = "select k_reserve_reserve_id_seq.nextval from dual";
		jt.query(selectSQL, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					id = rs.getLong(1);
				}
			}
		});

		// String sqlFormat = "select to_date(?,'yyyy-mm-dd
		// hh24:mi'),to_date(?,'yyyy-mm-dd hh24:mi') from dual";
		// jt.query(sqlFormat, new PreparedStatementSetter() {
		//
		// @Override
		// public void setValues(PreparedStatement pstmt) throws SQLException {
		// pstmt.setString(1, "2017-6-19 11:30");
		// pstmt.setString(2, "2017-6-19 11:50");
		// }
		// }, new RowCallBackHandler() {
		//
		// @Override
		// public void processRow(ResultSet rs) throws SQLException {
		// if(rs.next()){
		// d1=rs.getDate(1);
		// d2=rs.getDate(2);
		// }
		// }
		// });
		// new java.sql.Date(reserve.getResTime().getTime())
		String insertSQL = "insert into k_reserve values(?,?,?,?,?,?,?)";
		jt.update(insertSQL, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
				pstmt.setLong(2, reserve.getRoom().getRoomId());
				pstmt.setString(3, reserve.getResTime());// 获取时间戳
				pstmt.setString(4, reserve.getResendTime());
				pstmt.setString(5, reserve.getResPname());
				pstmt.setLong(6, reserve.getResmemId());
				pstmt.setString(7, reserve.getResPhone());
			}
		});
	}

	// 删
	@Override
	public void deleteReserve(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("deletesaveEember id=" + id);
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "delete from k_reserve where reserve_id = ?";
		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		});
	}

	// 改
	@Override
	public void updatReserve(Reserve reserve, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "update k_reserve " + "set reserve_room_id = ?,reserve_date = ?,reserve_enddate = ?,reserve_pname = ?,reserve_mem_id = ?,reserve_phone = ? "
				+ "where reserve_id = ?";

		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				pstmt.setLong(1, reserve.getRoom().getRoomId());
				pstmt.setString(2, reserve.getResTime());
				pstmt.setString(3, reserve.getResendTime());
				pstmt.setString(4, reserve.getResPname());
				pstmt.setLong(5, reserve.getResmemId());
				pstmt.setString(6, reserve.getResPhone());
				pstmt.setLong(8, reserve.getResId());
				
			}
		});
	}

	// 查ID
	@Override
	public Reserve findReserve(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		final Reserve reserve = new Reserve();
		String sql = "select reserve_room_id,reserve_date,reserve_enddate,reserve_pname,reserve_mem_id,reserve_phone from k_reserve where reserve_id = ?";
		jt.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		}, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				if (rs.next()) {
					
					reserve.setResId(rs.getLong(1));
					reserve.getRoom().setRoomId(rs.getLong(2));
					reserve.setResTime(rs.getString(3));
					reserve.setResendTime(rs.getString(4));
					reserve.setResPname(rs.getString(5));
					reserve.setResmemId(rs.getLong(6));
					reserve.setResPhone(rs.getString(7));
				}
			}
		});

		return reserve;
	}

	// 批量查询
	@Override
	public List<Reserve> findReserve(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		final List<Reserve> list = new ArrayList<Reserve>();
		String sql = "select * from k_reserve";
		jt.query(sql, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Reserve reserve = new Reserve();
					reserve.setResId(rs.getLong(1));
					reserve.getRoom().setRoomId(rs.getLong(2));
					reserve.setResTime(rs.getString(3));
					reserve.setResendTime(rs.getString(4));
					reserve.setResPname(rs.getString(5));
					reserve.setResmemId(rs.getLong(6));
					reserve.setResPhone(rs.getString(7));
					list.add(reserve);
				}
			}
		});
		return list;
	}

	// 动态 模糊查询
	@Override
	public List<Reserve> findReserve(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
		JdbcTemplate jt = new JdbcTemplate(conn);
		List<Reserve> list = new ArrayList<Reserve>();
		String sql = "select * " + "from k_reserve " + "where 1 = 1";
		if (conditions.size() > 0) {
			for (String condition : conditions) {
				sql += " and ";
				sql += condition;
			}
		}
		System.out.println(sql);
		jt.query(sql, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				while (rs.next()) {
					Reserve reserve = new Reserve();
					reserve.setResId(rs.getLong(1));
					reserve.getRoom().setRoomId(rs.getLong(2));
					reserve.setResTime(rs.getString(3));
					reserve.setResendTime(rs.getString(4));
					reserve.setResPname(rs.getString(5));
					reserve.setResmemId(rs.getLong(6));
					reserve.setResPhone(rs.getString(7));
					list.add(reserve);
				}
			}
		});
		return list;
	}

}
