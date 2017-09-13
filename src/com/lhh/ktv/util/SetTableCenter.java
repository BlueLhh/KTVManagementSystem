package com.lhh.ktv.util;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * 设置表格中的内容居中显示
 * 
 * @author 46512
 *
 */

public class SetTableCenter {

	public static void setTableCenter(JTable jTable) {
		DefaultTableCellRenderer dtr = new DefaultTableCellRenderer();// 设置表格的内容居中显示
		dtr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, dtr);// 设置表格中内容居中
	}

}
