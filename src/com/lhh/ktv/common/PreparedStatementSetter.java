package com.lhh.ktv.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	// 通过PreparedStatement来设置一些值来替换占位符"?"
	void setValues(PreparedStatement pstmt) throws SQLException;
}
