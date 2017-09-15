package com.lhh.ktv.util;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.lhh.ktv.model.entity.Employee;

/**
 * 
 * TableModel通过数据源（List集合）生成对应的图形界面
 * 
 * @author 46512
 *
 */
public class MyEmpTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5493846832514984421L;

	private List<Employee> emplist;// 通过构造函数传入数据源

	// 员工列表名
	private String[] empColumnNames = { "员工编号", "员工姓名", "员工性别", "员工年龄", "联系方式", "员工职别", "员工账号", "员工密码" };

	// 无参构造函数
	public MyEmpTableModel() {

	}

	public void updateList(List<Employee> emplist) {
		this.emplist = emplist;
	}

	// 有参构造函数
	public MyEmpTableModel(List<Employee> emplist) {
		super();
		this.emplist = emplist;
	}

	public String getColumnName(int column) {
		return empColumnNames[column];
	}

	// 获取行数
	/**
	 * 获取表格的行数，跟list的size一致 TableModel里的所有方法不需要手动调用
	 * 
	 * 这些方法会自动调用并生成对应的图形界面
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (emplist == null) {
			return 0;
		}
		return emplist.size();
	}

	// 获取列数
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return empColumnNames.length;
	}

	/**
	 * 生成单元格内容，调用次数为：行数*列数
	 * 
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Employee employee = emplist.get(rowIndex);
		String posrt;
		// 先判断列,列数决定了调用Student中的哪一个getter方法
		switch (columnIndex) {
		case 0:
			return employee.getEmpId();
		case 1:
			return employee.getEmpName();
		case 2:
			return employee.getEmpGender();
		case 3:
			return employee.getEmpAge();
		case 4:
			return employee.getEmpPhone();
		case 5:
			posrt = employee.getEmpPost();
			if (posrt.equals("0")) {
				posrt = "经理";
			} else {
				posrt = "前台";
			}
			return posrt;
		case 6:
			return employee.getUsername();
		case 7:
			return employee.getPassword();
		default:
			return "";
		}

	}

}
