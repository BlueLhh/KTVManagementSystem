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
import com.lhh.ktv.model.dao.IGoodsDao;
import com.lhh.ktv.model.entity.Goods;

public class GoodsDaoImpl implements IGoodsDao {

	Long id;

	// 增
	@Override
	public void saveGoods(Goods goods, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String selectSQL = "select k_goods_goods_id_seq.nextval from dual";
		jt.query(selectSQL, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					id = rs.getLong(1);
				}
			}
		});

		String insertSQL = "insert into k_goods values(?,?,?,?)";
		jt.update(insertSQL, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
				pstmt.setString(2, goods.getGoodsName());
				pstmt.setDouble(3, goods.getGoodsPrice());
				pstmt.setInt(4, goods.getGoodsCount());
			}
		});
	}

	// 删
	@Override
	public void deleteGoods(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "delete from k_goods where goods_id = ?";
		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		});
	}

	// 改
	@Override
	public void updatGoods(Goods goods, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "update k_goods " + "set goods_name = ?,goods_price = ?,goods_count = ? " + "where goods_id = ?";

		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, goods.getGoodsName());
				pstmt.setDouble(2, goods.getGoodsPrice());
				pstmt.setInt(3, goods.getGoodsCount());
				pstmt.setLong(4, goods.getGoodsId());
			}
		});
	}

	// 查ID
	@Override
	public Goods findGoods(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		final Goods goods = new Goods();
		String sql = "select goods_id,goods_name,goods_price,goods_count from k_goods where goods_id = ?";
		jt.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		}, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				if (rs.next()) {
					goods.setGoodsId(rs.getLong(1));
					goods.setGoodsName(rs.getString(2));
					goods.setGoodsPrice(rs.getDouble(3));
					goods.setGoodsCount(rs.getInt(4));
				}
			}
		});

		return goods;
	}

	// 批量查询
	@Override
	public List<Goods> findGoods(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		final List<Goods> list = new ArrayList<Goods>();
		String sql = "select goods_id,goods_name,goods_price,goods_count from k_goods";
		jt.query(sql, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Goods goods = new Goods();
					goods.setGoodsId(rs.getLong(1));
					goods.setGoodsName(rs.getString(2));
					goods.setGoodsPrice(rs.getDouble(3));
					goods.setGoodsCount(rs.getInt(4));
					list.add(goods);
				}
			}
		});
		return list;
	}

	// 动态查询（模糊查询）
	@Override
	public List<Goods> findGoods(List<String> conditions, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub

		JdbcTemplate jt = new JdbcTemplate(conn);
		List<Goods> list = new ArrayList<Goods>();
		String sql = "select goods_id,goods_name,goods_price,goods_count " + "from k_goods " + "where 1 = 1";
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
					Goods goods = new Goods();
					goods.setGoodsId(rs.getLong(1));
					goods.setGoodsName(rs.getString(2));
					goods.setGoodsPrice(rs.getDouble(3));
					goods.setGoodsCount(rs.getInt(4));
					list.add(goods);
				}
			}
		});
		return list;
	}

}
