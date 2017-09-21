package com.lhh.ktv.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.lhh.ktv.model.entity.EOrder;
import com.lhh.ktv.model.entity.Order;
import com.lhh.ktv.model.service.impl.EOrderServiceImpl;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.model.service.impl.OrderServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.MyEOrderTableModel;
import com.lhh.ktv.util.SetTableCenter;
import com.lhh.ktv.util.WindowMove;

public class RoomOrderFrame {

	static private JFrame frame;
	private double money;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// RoomOrderFrame window = new RoomOrderFrame();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public RoomOrderFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		BorderHide.setJFrameHide(frame);// 设置边框隐藏
		new WindowMove().install(frame);// 边框隐藏之后可以移动
		frame.setVisible(true);
		frame.setResizable(false);

		JButton closebtn = new JButton();
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		closebtn.setBounds(500, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);

		JLabel orderlabel = new JLabel("消费详情");
		orderlabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		orderlabel.setBounds(206, 34, 100, 61);
		frame.getContentPane().add(orderlabel);

		JLabel roomnamelabel = new JLabel("房间号：");
		roomnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		roomnamelabel.setBounds(57, 107, 78, 44);
		frame.getContentPane().add(roomnamelabel);

		JLabel roomIDlabel = new JLabel("");
		roomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		roomIDlabel.setBounds(137, 108, 78, 44);
		frame.getContentPane().add(roomIDlabel);

		JLabel custnamelabel = new JLabel("客户名字：");
		custnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		custnamelabel.setBounds(229, 108, 100, 44);
		frame.getContentPane().add(custnamelabel);

		JLabel getcustnamelabel = new JLabel("");
		getcustnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getcustnamelabel.setBounds(331, 107, 100, 44);
		frame.getContentPane().add(getcustnamelabel);

		JPanel orderdatapanel = new JPanel();
		orderdatapanel.setBounds(14, 169, 494, 398);
		frame.getContentPane().add(orderdatapanel);
		orderdatapanel.setLayout(null);

		// 条件查询
		String status = "0";
		String name = null;
		Long roomID;

		if ("经理".equals(EmployeeServiceImpl.getPost())) {
			roomID = MainFrame.roomID;
		} else {
			roomID = EmpMainFrame.roomID;
		}

		// 123
		Long orderID = null;
		// 条件查询ORDER订单表 获取ID和名字
		OrderServiceImpl orderService = new OrderServiceImpl();
		List<String> conditions = new ArrayList<String>();
		conditions.add("order_room_id = '" + roomID + "'");
		conditions.add("order_status = '" + status + "'");
		List<Order> list = orderService.findOrder(conditions);
		// TODO 子订单的费用在子订单算出来只有会在这里实现
		for (Order order : list) {
			name = order.getOrpName();
			orderID = order.getOrderId();
		}
		System.out.println("订单ID=" + orderID);
		roomIDlabel.setText(String.valueOf(roomID));
		getcustnamelabel.setText(name);

		// 写表格
		try {

			EOrderServiceImpl eOrderSimpl = new EOrderServiceImpl();
			List<String> eodconditions = new ArrayList<String>();

			eodconditions.add("eorder_order_id = '" + orderID + "'");

			List<EOrder> eodList = eOrderSimpl.findIEorder(eodconditions);
			for (EOrder eOrder : eodList) {
				money += eOrder.geteMoney();
			}
			JTable eordTable = new JTable();
			SetTableCenter.setTableCenter(eordTable);
			MyEOrderTableModel model = new MyEOrderTableModel(eodList);
			eordTable.setModel(model);

			JScrollPane scrollPane = new JScrollPane(eordTable);
			scrollPane.setBounds(0, 0, 494, 398);
			orderdatapanel.add(scrollPane);

			JLabel nowmonylabel = new JLabel("当前消费金额为：");
			nowmonylabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
			nowmonylabel.setBounds(14, 589, 120, 36);
			frame.getContentPane().add(nowmonylabel);

			JLabel getnowmonylabel = new JLabel(String.valueOf(money));
			getnowmonylabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			getnowmonylabel.setBounds(134, 585, 84, 45);
			frame.getContentPane().add(getnowmonylabel);

			JLabel yuanlabel = new JLabel("元");
			yuanlabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
			yuanlabel.setBounds(217, 589, 30, 36);
			frame.getContentPane().add(yuanlabel);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static public void closeRoomOrderFrame() {
		frame.setVisible(false);
		frame.dispose();
	}

}
