package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Order;
import com.lhh.ktv.model.entity.Room;
import com.lhh.ktv.model.service.IOrderService;
import com.lhh.ktv.model.service.impl.OrderServiceImpl;
import com.lhh.ktv.model.service.impl.RoomServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class OpenRoomFrame {

	private static JFrame frame;
	private JTextField customernametxt;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// OpenRoomFrame window = new OpenRoomFrame();
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
	public OpenRoomFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 308, 322);
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
		closebtn.setBounds(278, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);

		JLabel lblNewLabel = new JLabel("开箱");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		lblNewLabel.setBounds(110, 25, 69, 50);
		frame.getContentPane().add(lblNewLabel);

		JLabel roomIDlabel = new JLabel("房间号：");
		roomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		roomIDlabel.setBounds(37, 92, 72, 43);
		frame.getContentPane().add(roomIDlabel);

		JLabel showRoomIDlabel = new JLabel(String.valueOf(RoomInfoFrame.getRoomID));
		showRoomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		showRoomIDlabel.setBounds(113, 92, 72, 43);
		frame.getContentPane().add(showRoomIDlabel);

		JLabel customernamelabel = new JLabel("客户姓名：");
		customernamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		customernamelabel.setBounds(37, 148, 95, 37);
		frame.getContentPane().add(customernamelabel);

		customernametxt = new JTextField();
		customernametxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		customernametxt.setBounds(123, 148, 133, 37);
		frame.getContentPane().add(customernametxt);
		customernametxt.setColumns(10);

		JButton okopenbtn = new JButton("确定");
		okopenbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double price;// 包房费用
				// 获取当前系统时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				String time = df.format(new Date()).trim();
				String year = time.substring(0, 4);
				String month = time.substring(5, 7);
				String day = time.substring(8, 10);
				String hour = time.substring(11, 13);
				String min = time.substring(14, 16);

				// time = year + month + day + hour + min;
				time = year + "-" + month + "-" + day + " " + hour + ":" + min;

				IOrderService orderService = new OrderServiceImpl();
				Order order = new Order();

				RoomServiceImpl roomService = new RoomServiceImpl();
				Room room = new Room();
				try {
					room = roomService.findRoom(Long.parseLong(showRoomIDlabel.getText()));
					price = room.getRoomPrice();

					order.getRoomId().setRoomId(Long.parseLong(showRoomIDlabel.getText()));
					order.setOrpName(customernametxt.getText());
					order.setOrdOpentime(time);
					order.setOrdrmPrice(price);
					order.setOrdStatus("0");
					orderService.addOrder(order);
					
					//System.out.println("添加成功！");
					// 更新包房的状态
					String status = "使用中";
					room.setRoomType(room.getRoomType());
					room.setRoomPrice(price);
					room.setRoomStatus(status);
					room.setRoomId(Long.parseLong(showRoomIDlabel.getText()));
					roomService.updateRoom(room);
					JOptionPane.showMessageDialog(contentPane, "开箱成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					closeOpenRoomFrame();
					RoomInfoFrame.closeRoomInfoFrame();
				} catch (ServiceException E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}

			}
		});
		okopenbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		okopenbtn.setBounds(48, 207, 72, 43);
		frame.getContentPane().add(okopenbtn);

		JButton closeopenbtn = new JButton("取消");
		closeopenbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 关闭这个窗口
				closeOpenRoomFrame();

			}
		});
		closeopenbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		closeopenbtn.setBounds(168, 207, 72, 43);
		frame.getContentPane().add(closeopenbtn);

		// 如果已经预定的话，就在面板上显示预定用户的名字
		// IReserveService reserveService = new ReserveServiceImpl();
		// Reserve reserve = new Reserve();
	}

	public static void closeOpenRoomFrame() {
		frame.setVisible(false);
		frame.dispose();
	}

}
