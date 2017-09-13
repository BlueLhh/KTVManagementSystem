package com.lhh.ktv.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * 按钮事件
 * 
 * @author 46512
 *
 */

public class BtnEvent {

	// 关闭按钮
	public static void btnExit(JButton jButton) {
		// 获取照片路径
		ImageIcon exit1 = new ImageIcon("src/images/exit.png");
		ImageIcon exit2 = new ImageIcon("src/images/exit_entered.png");
		ImageIcon exit3 = new ImageIcon("src/images/exit_pressed.png");

		jButton.setIcon(exit1);
		jButton.setRolloverIcon(exit2);// 滑过的状态
		jButton.setPressedIcon(exit3);// 点击时候的状态

	}

	// 取消按钮
	public static void btnClose(JButton jButton) {
		ImageIcon cicon1 = new ImageIcon("src/images/cancel.png");
		ImageIcon cicon2 = new ImageIcon("src/images/cancel_entered.png");
		ImageIcon cicon3 = new ImageIcon("src/images/cancel_pressed.png");
		jButton.setIcon(cicon1);
		jButton.setRolloverIcon(cicon2);// 滑过的状态
		jButton.setPressedIcon(cicon3);// 点击时候的状态
	}

	// 登录按钮
	public static void btnLogin(JButton jButton) {
		ImageIcon eicon1 = new ImageIcon("src/images/ensure.png");
		ImageIcon eicon2 = new ImageIcon("src/images/ensure_entered.png");
		ImageIcon eicon3 = new ImageIcon("src/images/ensure_pressed.png");
		jButton.setIcon(eicon1); // 平时状态
		jButton.setRolloverIcon(eicon2);// 滑过的状态
		jButton.setPressedIcon(eicon3);// 点击时候的状态
	}

	// 首页
	public static void btnFrist(JButton jButton) {
		ImageIcon img1 = new ImageIcon("src/images/frist1.jpg");
		ImageIcon img2 = new ImageIcon("src/images/frist2.jpg");
		jButton.setIcon(img1); // 平时状态
		jButton.setRolloverIcon(img2);// 滑过的状态
	}

	// 包房管理
	public static void btnRoom(JButton jButton) {
		ImageIcon img1 = new ImageIcon("src/images/room1.jpg");
		ImageIcon img2 = new ImageIcon("src/images/room2.jpg");
		jButton.setIcon(img1); // 平时状态
		jButton.setRolloverIcon(img2);// 滑过的状态
	}

	// 会员管理
	public static void btnMem(JButton jButton) {
		ImageIcon img1 = new ImageIcon("src/images/mem1.jpg");
		ImageIcon img2 = new ImageIcon("src/images/mem2.png");
		jButton.setIcon(img1); // 平时状态
		jButton.setRolloverIcon(img2);// 滑过的状态
	}

	// 商品管理
	public static void btnGoods(JButton jButton) {
		ImageIcon img1 = new ImageIcon("src/images/goods1.jpg");
		ImageIcon img2 = new ImageIcon("src/images/goods2.png");
		jButton.setIcon(img1); // 平时状态
		jButton.setRolloverIcon(img2);// 滑过的状态
	}

	// 员工管理
	public static void btnEmp(JButton jButton) {
		ImageIcon img1 = new ImageIcon("src/images/emp1.jpg");
		ImageIcon img2 = new ImageIcon("src/images/emp2.png");
		jButton.setIcon(img1); // 平时状态
		jButton.setRolloverIcon(img2);// 滑过的状态
	}

	// 营业查询
	public static void btnBus(JButton jButton) {
		ImageIcon img1 = new ImageIcon("src/images/bus1.jpg");
		ImageIcon img2 = new ImageIcon("src/images/bus2.jpg");
		jButton.setIcon(img1); // 平时状态
		jButton.setRolloverIcon(img2);// 滑过的状态
	}

}
