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
import com.lhh.ktv.model.dao.IEOrderDao;
import com.lhh.ktv.model.entity.EOrder;

public class EOrderDaoImpl implements IEOrderDao{

	Long id;
	
	//增
	@Override
	public void saveEOrder(EOrder eOrder, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String selectSQL = "select k_eorder_eorder_id_seq.nextval from dual";
		jt.query(selectSQL, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					id = rs.getLong(1);
				}
			}
		});
		
		String insertSQL = "insert into k_eorder values(?,?,?,?,?,?)";
		jt.update(insertSQL, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
				pstmt.setLong(2, eOrder.getOrder().getOrderId());
				pstmt.setString(3, eOrder.getEgName());
				pstmt.setInt(4, eOrder.geteCount());
				pstmt.setDouble(5, eOrder.getEgPrice());
				pstmt.setDouble(6, eOrder.geteMoney());
			}
		});
	}

	//刪
	@Override
	public void deleteEOrder(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("deletesaveEember id=" + id);
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "delete from k_eorder where eorder_id = ?";
		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		});
	}

	//改
	@Override
	public void updatEOrder(EOrder eOrder, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "update k_eorder " + "set eorder_order_id = ?,eorder_goods_name = ?,eorder_count = ?,eorder_goods_price = ?,eorder_money = ? "
				+ "where eorder_id = ?";

		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, eOrder.getOrder().getOrderId());
				pstmt.setString(2, eOrder.getEgName());
				pstmt.setInt(3, eOrder.geteCount());
				pstmt.setDouble(4, eOrder.getEgPrice());
				pstmt.setDouble(5, eOrder.geteMoney());
				pstmt.setLong(6, eOrder.geteId());
			}
		});
	}

	//查ID
	@Override
	public EOrder findEOrder(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
		JdbcTemplate jt = new JdbcTemplate(conn);
		final EOrder eOrder = new EOrder();
		String sql = "select * from k_eorder where eorder_id = ?";
		jt.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		}, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				if (rs.next()) {
					
					eOrder.seteId(rs.getLong(1));
					eOrder.getOrder().setOrderId(rs.getLong(2));
					eOrder.setEgName(rs.getString(3));
					eOrder.seteCount(rs.getInt(4));
					eOrder.setEgPrice(rs.getDouble(5));
					eOrder.seteMoney(rs.getDouble(6));
				}
			}
		});
		return eOrder;
	}

	//批量查询
	@Override
	public List<EOrder> findEOrder(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		
		JdbcTemplate jt = new JdbcTemplate(conn);
		final List<EOrder> list = new ArrayList<EOrder>();
		String sql = "select * from k_eorder";
		jt.query(sql, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					EOrder eOrder = new EOrder();
					eOrder.seteId(rs.getLong(1));
					eOrder.getOrder().setOrderId(rs.getLong(2));
					eOrder.setEgName(rs.getString(3));
					eOrder.seteCount(rs.getInt(4));
					eOrder.setEgPrice(rs.getDouble(5));
					eOrder.seteMoney(rs.getDouble(6));
					list.add(eOrder);
				}
			}
		});
		return list;
		
	}

	//动态查询 模糊查询
	@Override
	public List<EOrder> findEOrder(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		List<EOrder> list = new ArrayList<EOrder>();
		String sql = "select * " + "from k_eorder " + "where 1 = 1";
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
					EOrder eOrder = new EOrder();
					eOrder.seteId(rs.getLong(1));
					eOrder.getOrder().setOrderId(rs.getLong(2));
					eOrder.setEgName(rs.getString(3));
					eOrder.seteCount(rs.getInt(4));
					eOrder.setEgPrice(rs.getDouble(5));
					eOrder.seteMoney(rs.getDouble(6));
					list.add(eOrder);
				}
			}
		});
		return list;
	}

}
