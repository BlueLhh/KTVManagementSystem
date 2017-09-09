package com.lhh.ktv.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lhh.ktv.exception.DataAccessException;

public class JdbcTemplate {
	private Connection conn;

	public JdbcTemplate(Connection conn) {
		this.conn = conn;
	}

	// R(Retrieve)
	public void query(String sql, RowCallBackHandler handler) throws DataAccessException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (handler != null)
				handler.processRow(rs);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			DBUtils.close(rs, stmt);
		}
	}

	// R(Retrieve)
	public void query(String sql, PreparedStatementSetter setter, RowCallBackHandler handler)
			throws DataAccessException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (setter != null)
				setter.setValues(pstmt);
			rs = pstmt.executeQuery();
			if (handler != null)
				handler.processRow(rs);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			DBUtils.close(rs, pstmt);
		}
	}

	// C(Create) U(Update) D(Delete)
	public void update(String sql, PreparedStatementSetter setter) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (setter != null)
				setter.setValues(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			DBUtils.close(null, pstmt);
		}
	}
}
