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
import com.lhh.ktv.model.dao.IEmployeeDao;
import com.lhh.ktv.model.entity.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {

	Long id = 0L;

	@Override
	public void saveEmployee(Employee employee, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String selectSQL = "select k_emp_emp_id_seq.nextval from dual";
		jt.query(selectSQL, new RowCallBackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if (rs.next()) {
					id = rs.getLong(1);
				}
			}
		});

		String insertSQL = "insert into k_emp values(?,?,?,?,?,?,?,?)";
		jt.update(insertSQL, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
				pstmt.setString(2, employee.getEmpName());
				pstmt.setString(3, employee.getEmpGender());
				pstmt.setByte(4, employee.getEmpAge());
				pstmt.setString(5, employee.getEmpPhone());
				pstmt.setString(6, employee.getEmpPost());
				pstmt.setString(7, employee.getUsername());
				pstmt.setString(8, employee.getPassword());
			}
		});
	}

	@Override
	public void deleteEmployee(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "delete from k_emp where id = ?";
		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		});
	}

	@Override
	public void updateEmployee(Employee employee, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		String sql = "update k_emp "
				+ "set emp_name = ?,emp_gender = ?,emp_age = ?,emp_phone = ?,emp_post= ?,emp_username = ?,emp_password = ? "
				+ "where id = ?";

		jt.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, employee.getEmpName());
				pstmt.setString(2, employee.getEmpGender());
				pstmt.setByte(3, employee.getEmpAge());
				pstmt.setString(4, employee.getEmpPhone());
				pstmt.setString(5, employee.getEmpPost());
				pstmt.setString(6, employee.getUsername());
				pstmt.setString(7, employee.getPassword());
				pstmt.setLong(8, id);
			}
		});
	}

	@Override
	public Employee findEmployee(Long id, Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		final Employee employee = new Employee();
		String sql = "select emp_id,emp_name,emp_gender,emp_age,emp_phone,emp_post,emp_username,emp_password from k_emp where id = ?";
		jt.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
			}
		}, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				if (rs.next()) {
					employee.setEmpId(rs.getLong(1));
					employee.setEmpName(rs.getString(2));
					employee.setEmpGender(rs.getString(3));
					employee.setEmpAge(rs.getByte(4));
					employee.setEmpPhone(rs.getString(5));
					employee.setEmpPost(rs.getString(6));
					employee.setUsername(rs.getString(7));
					employee.setPassword(rs.getString(8));
				}
			}
		});
		return employee;
	}

	@Override
	public List<Employee> findEmployees(Connection conn) throws DataAccessException {
		// TODO Auto-generated method stub
		JdbcTemplate jt = new JdbcTemplate(conn);
		final List<Employee> list = new ArrayList<Employee>();
		String sql = "select emp_id,emp_name,emp_gender,emp_age,emp_phone,emp_post,emp_username,emp_password from k_emp";
		jt.query(sql, new RowCallBackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Employee employee = new Employee();
					employee.setEmpId(rs.getLong(1));
					employee.setEmpName(rs.getString(2));
					employee.setEmpGender(rs.getString(3));
					employee.setEmpAge(rs.getByte(4));
					employee.setEmpPhone(rs.getString(5));
					employee.setEmpPost(rs.getString(6));
					employee.setUsername(rs.getString(7));
					employee.setPassword(rs.getString(8));
					list.add(employee);
				}
			}
		});
		return list;
	}

}
