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
import com.lhh.ktv.model.dao.IRoomDao;
import com.lhh.ktv.model.entity.Room;

public class RoomDaoImpl implements IRoomDao {

	Long id;

	@Override
	public void saveRoom(Room room, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String selectSQL = "select k_room_room_id_seq.nextval from dual";
		jt.query(selectSQL, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					id = rs.getLong(1);
				}
			}
		});

		String insertSQL = "insert into k_room values(?,?,?,?)";
		jt.update(insertSQL, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
				pstmt.setString(2, room.getRoomType());
				pstmt.setDouble(3, room.getRoomPrice());
				pstmt.setString(4, room.getRoomStatus());
			}
		});
	}

	// 删除
	@Override
	public void deleteRoom(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("deletesaveEember id=" + id);
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "delete from k_room where room_id = ?";
		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		});
	}

	// 更新
	@Override
	public void updatRoom(Room room, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "update k_room " + "set room_type = ?,room_price = ?,room_status = ? " + "where room_id = ?";

		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, room.getRoomType());
				pstmt.setDouble(2, room.getRoomPrice());
				pstmt.setString(3, room.getRoomStatus());
				pstmt.setLong(4, room.getRoomId());
			}
		});
	}

	// 查询ID
	@Override
	public Room findRoom(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		final Room room = new Room();
		String sql = "select room_id,room_type,room_price,room_status from k_room where room_id = ?";
		jt.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		}, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				if (rs.next()) {
					room.setRoomId(rs.getLong(1));
					room.setRoomType(rs.getString(2));
					room.setRoomPrice(rs.getDouble(3));
					room.setRoomStatus(rs.getString(4));
				}
			}
		});
		return room;
	}

	// 批量查询
	@Override
	public List<Room> findRoom(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		final List<Room> list = new ArrayList<Room>();
		String sql = "select room_id,room_type,room_price,room_status from k_room";
		jt.query(sql, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Room room = new Room();
					room.setRoomId(rs.getLong(1));
					room.setRoomType(rs.getString(2));
					room.setRoomPrice(rs.getDouble(3));
					room.setRoomStatus(rs.getString(4));
					list.add(room);
				}
			}
		});
		return list;
	}

	//动态查询 模糊查询
	@Override
	public List<Room> findRoom(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
		JdbcTemplate jt = new JdbcTemplate(conn);
		List<Room> list = new ArrayList<Room>();
		String sql = "select room_id,room_type,room_price,room_status " + "from k_room " + "where 1 = 1";
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
					Room room = new Room();
					room.setRoomId(rs.getLong(1));
					room.setRoomType(rs.getString(2));
					room.setRoomPrice(rs.getDouble(3));
					room.setRoomStatus(rs.getString(4));
					list.add(room);
				}
			}
		});
		return list;
	}

}
