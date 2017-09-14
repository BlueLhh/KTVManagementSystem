package com.lhh.ktv.model.service.impl;

import java.sql.Connection;
import java.util.List;

import com.lhh.ktv.common.ConnectionFactory;
import com.lhh.ktv.common.DBUtils;
import com.lhh.ktv.common.JdbcTransaction;
import com.lhh.ktv.exception.DataAccessException;
import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.dao.IEmployeeDao;
import com.lhh.ktv.model.dao.impl.EmployeeDaoImpl;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {

	private IEmployeeDao employeeDao = new EmployeeDaoImpl();

	//要想获取该界面的数据，在该界面定义静态变量。在需要此数据的地方使用类点出数据
	static String post;
	static String name;
	static String num;

	public static String getNum() {
		return num;
	}

	public static void setNum(String num) {
		EmployeeServiceImpl.num = num;
	}

	public static String getPost() {
		return post;
	}

	public static void setPost(String post) {
		EmployeeServiceImpl.post = post;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		EmployeeServiceImpl.name = name;
	}

	// 添加员工
	@Override
	public void addEmployee(Employee employee) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			employeeDao.saveEmployee(employee, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
//			throw new ServiceException("添加员工失败！");
			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, conn);
		}

	}

	// 登录
	@Override
	public boolean login(String username, String password) throws ServiceException {
		// TODO Auto-generated method stub
		boolean ifright = false;// 初始为错误
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			List<Employee> list = employeeDao.login(conn);
			for (Employee employee : list) {
				if (employee.getUsername().trim().equals(username)) {
					ifright = true;
					if (employee.getPassword().trim().equals(password)) {
						ifright = true;
						name = employee.getEmpName();
						System.out.println("当前用户为：" + name);
						if (employee.getEmpPost().trim().equals("0")) {
							post = "经理";
						} else {
							post = "前台";
						}
						System.out.println("当前用户职位为：" + post);
						break;
					} else {
						ifright = false;
						continue;
					}
				} else {
					ifright = false;
					continue;
				}
			}
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("登录失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		return ifright;
	}

	// 删除员工
	@Override
	public void delEmployee(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			employeeDao.deleteEmployee(id, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("删除员工失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
	}

	// 修改员工信息
	@Override
	public void updateEmployee(Employee employee) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;

		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			employeeDao.updateEmployee(employee, conn);
			// 提交事务
			trans.commit(conn);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			// 回滚事务
			trans.rollback(conn);
			//throw new ServiceException("更新员工失败！");
			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, conn);
		}
	}

	// 单个查询
	@Override
	public Employee findEmployee(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		Employee employee = new Employee();
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			employee = employeeDao.findEmployee(id, conn);
			// 提交事务
			trans.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("查找失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		return employee;
	}

	// 批量查询
	@Override
	public List<Employee> findEmployee(Employee employee) throws ServiceException {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<Employee> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = employeeDao.findEmployees(conn);
			for (Employee emp : list) {
				System.out.println(emp);
			}
			// 提交事务
			trans.commit(conn);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			throw new ServiceException("查询失败失败！");
		} finally {
			DBUtils.close(null, null, conn);
		}
		return list;
	}

	// 动态查询
	@Override
	public List<Employee> findEmployee(List<String> conditions) {
		// TODO Auto-generated method stub
		JdbcTransaction trans = new JdbcTransaction();
		Connection conn = null;
		List<Employee> list = null;
		try {
			// 启动事务
			conn = ConnectionFactory.getConnection();
			trans.beginTransaction(conn);
			list = employeeDao.findEmployees(conditions, conn);
			System.out.println("动态查询成功！");
			// 提交事务
			trans.commit(conn);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// 回滚事务
			trans.rollback(conn);
			try {
				throw new ServiceException("动态查询失败失败！");
			} catch (ServiceException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
		} finally {
			DBUtils.close(null, null, conn);
		}
		return list;
	}

}
