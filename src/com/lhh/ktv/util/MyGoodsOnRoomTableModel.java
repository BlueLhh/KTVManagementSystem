package com.lhh.ktv.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import com.lhh.ktv.model.entity.Goods;
/**
 * 
 * TableModel通过数据源（List集合）生成对应的图形界面
 * 
 * @author 46512
 *
 */
public class MyGoodsOnRoomTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5493846832514984421L;

	private List<Goods> goodslist;// 通过构造函数传入数据源

	// 员工列表名
	private String[] empColumnNames = { "商品编号", "商品名称", "商品价格", "商品库存" };
	public static double addMoney = 5;//定义添加的价格
	// 无参构造函数
	public MyGoodsOnRoomTableModel() {

	}

	public void updateList(List<Goods> goodslist) {
		this.goodslist = goodslist;
	}

	// 有参构造函数
	public MyGoodsOnRoomTableModel(List<Goods> goodslist) {
		super();
		this.goodslist = goodslist;
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
		if (goodslist == null) {
			return 0;
		}
		return goodslist.size();
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
		Goods goods = goodslist.get(rowIndex);
		// 先判断列,列数决定了调用Student中的哪一个getter方法
		switch (columnIndex) {
		case 0:
			return goods.getGoodsId();
		case 1:
			return goods.getGoodsName();
		case 2:
			return goods.getGoodsPrice() + addMoney;
		case 3:
			return goods.getGoodsCount();
		default:
			return "";
		}

	}

}
