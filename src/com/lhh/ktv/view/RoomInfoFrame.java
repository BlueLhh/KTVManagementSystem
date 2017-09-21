package com.lhh.ktv.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Room;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.model.service.impl.RoomServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;
import javax.swing.JLabel;
import java.awt.Font;

public class RoomInfoFrame {

	private static JFrame frame;

	public static Long getRoomID;
	public static String getRoomType;
	public static double getRoomPrice;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// RoomInfoFrame window = new RoomInfoFrame();
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
	public RoomInfoFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 406, 605);
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
		closebtn.setBounds(376, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);

		JLabel roominfotitlelabel = new JLabel("包厢信息");
		roominfotitlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		roominfotitlelabel.setBounds(169, 55, 98, 55);
		frame.getContentPane().add(roominfotitlelabel);

		JLabel roomIDlabel = new JLabel("房间号：");
		roomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		roomIDlabel.setBounds(77, 146, 82, 46);
		frame.getContentPane().add(roomIDlabel);

		JLabel roomtypelabel = new JLabel("房间类型：");
		roomtypelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		roomtypelabel.setBounds(59, 228, 100, 46);
		frame.getContentPane().add(roomtypelabel);

		JLabel roompricelabel = new JLabel("包房费用：");
		roompricelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		roompricelabel.setBounds(59, 306, 100, 46);
		frame.getContentPane().add(roompricelabel);

		JLabel roomstatuslabel = new JLabel("包房状态：");
		roomstatuslabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		roomstatuslabel.setBounds(59, 385, 100, 46);
		frame.getContentPane().add(roomstatuslabel);

		JLabel showroomIDlabel = new JLabel("");
		showroomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showroomIDlabel.setBounds(174, 146, 82, 46);
		frame.getContentPane().add(showroomIDlabel);

		JLabel showroomtypelabel = new JLabel("");
		showroomtypelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showroomtypelabel.setBounds(173, 228, 123, 46);
		frame.getContentPane().add(showroomtypelabel);

		JLabel showroomoricelabel = new JLabel("");
		showroomoricelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showroomoricelabel.setBounds(173, 306, 82, 46);
		frame.getContentPane().add(showroomoricelabel);

		JLabel showroomstatuslabel = new JLabel("");
		showroomstatuslabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showroomstatuslabel.setBounds(174, 385, 122, 46);
		frame.getContentPane().add(showroomstatuslabel);

		JButton reserveroombtn = new JButton("预定");
		reserveroombtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new ReserveFrame();

			}
		});
		reserveroombtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		reserveroombtn.setBounds(59, 478, 111, 55);
		frame.getContentPane().add(reserveroombtn);

		JButton openroombtn = new JButton("开厢");
		openroombtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new OpenRoomFrame();

			}
		});
		openroombtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		openroombtn.setBounds(229, 478, 111, 55);
		frame.getContentPane().add(openroombtn);

		/**
		 * 
		 * 获取数据库的信息
		 * 
		 */
		RoomServiceImpl roomService = new RoomServiceImpl();
		Room room = new Room();
		try {

			if ("经理".equals(EmployeeServiceImpl.getPost())) {
				room = roomService.findRoom(MainFrame.roomID);
			} else {
				room = roomService.findRoom(EmpMainFrame.roomID);
			}
			// room = roomService.findRoom(MainFrame.roomID);

			showroomIDlabel.setText(String.valueOf(room.getRoomId()));// Long强转String
			showroomtypelabel.setText(room.getRoomType());
			showroomoricelabel.setText(String.valueOf(room.getRoomPrice()));
			showroomstatuslabel.setText(room.getRoomStatus());

			// 获取在房间信息的ID 和房间的类型
			getRoomID = Long.parseLong(showroomIDlabel.getText());// 获取到的ID
																	// 将在预定面板那边使用
			getRoomType = showroomtypelabel.getText();
			getRoomPrice = Double.parseDouble(showroomoricelabel.getText());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("在房间信息的面板获取的房间ID："+getRoomID);
		// System.out.println("在房间信息的面板获取的类型："+getRoomType);

	}

	static public void closeRoomInfoFrame() {
		frame.setVisible(false);
		frame.dispose();
	}

}
