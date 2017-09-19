package com.lhh.ktv.util;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.lhh.ktv.model.entity.Order;

/**
 * 
 * TableModel通过数据源（List集合）生成对应的图形界面
 * 
 * @author 46512
 *
 */
public class MyOrderTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5493846832514984421L;

	private List<Order> orderlist;// 通过构造函数传入数据源

	// 订单列表名
	private String[] empColumnNames = { "订单编号", "包房编号", "顾客姓名", "开厢时间", "退厢时间", "包房费用", "商品消费总额", "消费合计", "订单状态",
			"操作人员" };

	// 无参构造函数
	public MyOrderTableModel() {

	}

	public void updateList(List<Order> orderlist) {
		this.orderlist = orderlist;
	}

	// 有参构造函数
	public MyOrderTableModel(List<Order> orderlist) {
		super();
		this.orderlist = orderlist;
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
		if (orderlist == null) {
			return 0;
		}
		return orderlist.size();
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
		Order order = orderlist.get(rowIndex);
		// 先判断列,列数决定了调用Student中的哪一个getter方法
		String status;// 订单状态
		switch (columnIndex) {
		case 0:
			return order.getOrderId();
		case 1:
			return order.getRoomId().getRoomId();
		case 2:
			return order.getOrpName();
		case 3:
			return order.getOrdOpentime();
		case 4:
			return order.getOrdEndtime();
		case 5:
			return order.getOrdrmPrice();
		case 6:
			return order.getOrdAmtall();
		case 7:
			return order.getOrdAllamtall();
		case 8:
			status = order.getOrdStatus();
			if (status.equals("0")) {
				status = "未结账";
			} else {
				status = "已结账";
			}
			return status;
		case 9:
			return order.getOperator();
		default:
			return "";
		}

	}

}
