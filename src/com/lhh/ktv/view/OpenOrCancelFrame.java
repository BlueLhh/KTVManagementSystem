package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Order;
import com.lhh.ktv.model.entity.Reserve;
import com.lhh.ktv.model.entity.Room;
import com.lhh.ktv.model.service.IOrderService;
import com.lhh.ktv.model.service.IReserveService;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.model.service.impl.OrderServiceImpl;
import com.lhh.ktv.model.service.impl.ReserveServiceImpl;
import com.lhh.ktv.model.service.impl.RoomServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;

/**
 * 
 * 这是预定之后打开的面板，功能是开箱或者是取消订单
 * 
 * @author 46512
 *
 */

public class OpenOrCancelFrame {

	private static JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// OpenOrCancelFrame window = new OpenOrCancelFrame();
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
	public OpenOrCancelFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 305, 291);
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
		closebtn.setBounds(275, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);

		JLabel tishixinxilabel = new JLabel("请选择你的操作");
		tishixinxilabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		tishixinxilabel.setBounds(63, 54, 160, 41);
		frame.getContentPane().add(tishixinxilabel);

		JButton openokbtn = new JButton("开箱");
		openokbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Long id;//

				if ("经理".equals(EmployeeServiceImpl.getPost())) {
					id = MainFrame.roomID;// 获取点击后的数据
				} else {
					id = Test.roomID;
				}

				String name = null;// 客户的名字
				Long resID = null;// 预订单的编号
				double price;// 包房费用
				// 获取当前系统时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
				// System.out.println(df.format(new Date()));// new
				// Date()为获取当前系统时间
				String time = df.format(new Date()).trim();
				String year = time.substring(0, 4);
				String month = time.substring(5, 7);
				String day = time.substring(8, 10);
				String hour = time.substring(11, 13);
				String min = time.substring(14, 16);

				// time = year + month + day + hour + min;
				time = year + "-" + month + "-" + day + " " + hour + ":" + min;

				// 增加一条订单
				IOrderService orderService = new OrderServiceImpl();
				Order order = new Order();

				// 根据房间的ID查询房间，并且获取其相关信息
				RoomServiceImpl roomService = new RoomServiceImpl();
				Room room = new Room();

				// 查找预定单中的信息。条件查询。根据时间来查询，获取姓名
				ReserveServiceImpl reserveService = new ReserveServiceImpl();
				// Reserve reserve = new Reserve();
				List<String> conditions = new ArrayList<String>();
				conditions.add("reserve_room_id like '%" + id + "%'");
				// conditions.add("mem_phone like '%" + phone + "%'");
				List<Reserve> list = reserveService.findReserve(conditions);
				for (Reserve reserve : list) {
					if (id.equals(reserve.getRoom().getRoomId())) {
						name = reserve.getResPname();
						resID = reserve.getResId();
						break;
					}
				}
				// System.out.println("name = " + name);

				try {
					room = roomService.findRoom(id);
					price = room.getRoomPrice();

					order.getRoomId().setRoomId(id);
					order.setOrpName(name);
					order.setOrdOpentime(time);
					order.setOrdrmPrice(price);
					order.setOrdStatus("0");
					orderService.addOrder(order);

					// System.out.println("添加成功！");
					// 更新包房的状态
					String status = "使用中";
					room.setRoomType(room.getRoomType());
					room.setRoomPrice(price);
					room.setRoomStatus(status);
					room.setRoomId(id);
					roomService.updateRoom(room);

					// 更新包房状态之后把原预定订单删除
					// 删除预定的订单
					reserveService.delReserve(resID);
					JOptionPane.showMessageDialog(contentPane, "开箱成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					closeOpenOrCancelFrame();
				} catch (ServiceException E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}

			}
		});
		openokbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		openokbtn.setBounds(27, 146, 84, 48);
		frame.getContentPane().add(openokbtn);

		JButton opennobtn = new JButton("取消预定");
		opennobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 取消订单，恢复房间为未使用状态，然后删除该预定订单。

				Long id;//

				if ("经理".equals(EmployeeServiceImpl.getPost())) {
					id = MainFrame.roomID;// 获取点击后的数据
				} else {
					id = Test.roomID;
				}
				Long resID = null;// 预订单的编号
				String status = "未使用";
				RoomServiceImpl roomService = new RoomServiceImpl();
				Room room = new Room();

				// 删除该预定订单
				// 先查找 根据房间ID进行查找到该订单。
				IReserveService reserveService = new ReserveServiceImpl();
				// Reserve
				List<String> conditions = new ArrayList<String>();
				conditions.add("reserve_room_id like '%" + id + "%'");
				// conditions.add("mem_phone like '%" + phone + "%'");
				List<Reserve> list = reserveService.findReserve(conditions);
				for (Reserve reserve : list) {
					if (id.equals(reserve.getRoom().getRoomId())) {
						resID = reserve.getResId();
						break;
					}
				}
				// System.out.println("resID = " + resID);

				try {

					room = roomService.findRoom(id);// 先查找

					room.setRoomType(room.getRoomType());
					room.setRoomPrice(room.getRoomPrice());
					room.setRoomStatus(status);
					room.setRoomId(id);
					roomService.updateRoom(room);// 后更改
					// 删除预定的订单
					reserveService.delReserve(resID);
					JOptionPane.showMessageDialog(contentPane, "取消订单成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					closeOpenOrCancelFrame();
				} catch (ServiceException E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}

			}
		});
		opennobtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		opennobtn.setBounds(138, 146, 121, 48);
		frame.getContentPane().add(opennobtn);
	}

	public static void closeOpenOrCancelFrame() {
		frame.setVisible(false);
		frame.dispose();
	}

}
