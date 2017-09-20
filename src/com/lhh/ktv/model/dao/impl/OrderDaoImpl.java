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
import com.lhh.ktv.model.dao.IOrderDao;
import com.lhh.ktv.model.entity.Order;

public class OrderDaoImpl implements IOrderDao {

	Long id;

	// 增
	@Override
	public void saveOrder(Order order, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String selectSQL = "select k_order_order_id_seq.nextval from dual";
		jt.query(selectSQL, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					id = rs.getLong(1);
				}
			}
		});

		String insertSQL = "insert into k_order values(?,?,?,?,?,?,?,?,?,?)";
		jt.update(insertSQL, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
				pstmt.setLong(2, order.getRoomId().getRoomId());
				pstmt.setString(3, order.getOrpName());
				pstmt.setString(4, order.getOrdOpentime());
				pstmt.setString(5, order.getOrdEndtime());
				pstmt.setDouble(6, order.getOrdrmPrice());
				pstmt.setDouble(7, order.getOrdAmtall());
				pstmt.setDouble(8, order.getOrdAllamtall());
				pstmt.setString(9, order.getOrdStatus());
				pstmt.setString(10, order.getOperator());
			}
		});
	}

	// 删
	@Override
	public void deleteOrder(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("deletesaveEember id=" + id);
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "delete from k_order where order_id = ?";
		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		});
	}

	// 改
	@Override
	public void updatOrder(Order order, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "update k_order "
				+ "set order_room_id = ?,order_reserve_pname = ?,order_opendate = ?,order_enddate = ?,order_room_price = ?,order_amtall = ?,order_allamtall = ?,order_status = ?,order_emp_name = ? "
				+ "where order_id = ?";

		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {

				pstmt.setLong(1, order.getRoomId().getRoomId());
				pstmt.setString(2, order.getOrpName());
				pstmt.setString(3, order.getOrdOpentime());
				pstmt.setString(4, order.getOrdEndtime());
				pstmt.setDouble(5, order.getOrdrmPrice());
				pstmt.setDouble(6, order.getOrdAmtall());
				pstmt.setDouble(7, order.getOrdAllamtall());
				pstmt.setString(8, order.getOrdStatus());
				pstmt.setString(9, order.getOperator());
				pstmt.setLong(10, order.getOrderId());
			}
		});
	}

	// 查ID
	@Override
	public Order findOrder(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		final Order order = new Order();
		String sql = "select * from k_order where order_id = ?";
		jt.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		}, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				if (rs.next()) {

					order.setOrderId(rs.getLong(1));
					order.getRoomId().setRoomId(rs.getLong(2));
					order.setOrpName(rs.getString(3));
					order.setOrdOpentime(rs.getString(4));
					order.setOrdEndtime(rs.getString(5));
					order.setOrdrmPrice(rs.getDouble(6));
					order.setOrdAmtall(rs.getDouble(7));
					order.setOrdAllamtall(rs.getDouble(8));
					order.setOrdStatus(rs.getString(9));
					order.setOperator(rs.getString(10));

				}
			}
		});
		return order;
	}

	// 批量查询
	@Override
	public List<Order> findOrder(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		final List<Order> list = new ArrayList<Order>();
		String sql = "select * from k_order";
		jt.query(sql, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getLong(1));
					order.getRoomId().setRoomId(rs.getLong(2));
					order.setOrpName(rs.getString(3));
					order.setOrdOpentime(rs.getString(4));
					order.setOrdEndtime(rs.getString(5));
					order.setOrdrmPrice(rs.getDouble(6));
					order.setOrdAmtall(rs.getDouble(7));
					order.setOrdAllamtall(rs.getDouble(8));
					order.setOrdStatus(rs.getString(9));
					order.setOperator(rs.getString(10));
					list.add(order);
				}
			}
		});
		return list;
	}

	// 动态查询
	@Override
	public List<Order> findOrder(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		List<Order> list = new ArrayList<Order>();
		String sql = "select * " + "from k_order " + "where 1 = 1";
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
					Order order = new Order();
					order.setOrderId(rs.getLong(1));
					order.getRoomId().setRoomId(rs.getLong(2));
					order.setOrpName(rs.getString(3));
					order.setOrdOpentime(rs.getString(4));
					order.setOrdEndtime(rs.getString(5));
					order.setOrdrmPrice(rs.getDouble(6));
					order.setOrdAmtall(rs.getDouble(7));
					order.setOrdAllamtall(rs.getDouble(8));
					order.setOrdStatus(rs.getString(9));
					order.setOperator(rs.getString(10));
					list.add(order);
				}
			}
		});
		return list;
	}

}
