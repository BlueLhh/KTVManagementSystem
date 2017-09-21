package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Member;
import com.lhh.ktv.model.entity.Order;
import com.lhh.ktv.model.entity.Room;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.model.service.impl.MemberServiceImpl;
import com.lhh.ktv.model.service.impl.OrderServiceImpl;
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

public class AddGoodsOrCheckout {

	private static JFrame frame;
	private JPanel contentPane;
	public static String cusName;// 客户的名字
	public static double money;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// AddGoodsOrCheckout window = new AddGoodsOrCheckout();
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
	public AddGoodsOrCheckout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 279, 355);
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
		closebtn.setBounds(249, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);

		JLabel tishilabel = new JLabel("请选择你的操作");
		tishilabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		tishilabel.setBounds(61, 44, 150, 45);
		frame.getContentPane().add(tishilabel);

		JButton addgoodsbtn = new JButton("商品服务");
		addgoodsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO 商品服务是客户服务
				// 先遍历订单，查询的基础是根据房间号来进行查询，以及判断该订单是状态是0
				// 未结账的订单便是此时的包房订单
				// 条件查询订单列表
				// 通过查询订单表获取客户的名字
				Long roomID = MainFrame.roomID;
				String ordStatus = "0";// 查询订单的状态是未结账的
				OrderServiceImpl orderService = new OrderServiceImpl();
				List<String> conditions = new ArrayList<String>();
				conditions.add("order_room_id like '%" + roomID + "%'");
				conditions.add("order_status like '%" + ordStatus + "%'");
				List<Order> list = orderService.findOrder(conditions);
				// TODO 子订单的费用在子订单算出来只有会在这里实现
				for (Order order : list) {
					cusName = order.getOrpName();
				}
				new AddGoodsServiceFrame();

			}
		});
		addgoodsbtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		addgoodsbtn.setBounds(80, 118, 112, 45);
		frame.getContentPane().add(addgoodsbtn);

		JButton checkoutbtn = new JButton("结账");
		checkoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long orderID = null;
				Long roomId = MainFrame.roomID;
				String orpName = null;// 消费者姓名
				String ordOpentime = null;// 开包时间
				String ordEndtime;// 结账时间
				double ordrmPrice = 0;// 包房费用
				double ordAmtall = 0;// 商品消费总额
				double ordAllamtall;// 消费合计
				String ordStatus = "0";// 订单状态。0是初始状态，为未结账。1为已经结账
				String operator;// 操作人员

				// 房间的属性状态
				String roomType = null;// 包房种类
				double roomPrice = 0;// 包房费用
				String roomStatus = "未使用";// 包房状态，未使用，已预订，使用中

				// 先查询房间，获取房间的当前状态
				RoomServiceImpl roomService = new RoomServiceImpl();
				Room room = new Room();
				try {
					room = roomService.findRoom(roomId);

					roomType = room.getRoomType();

					roomPrice = room.getRoomPrice();

				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 获取当前的工作人员名称
				operator = EmployeeServiceImpl.getName();
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
				ordEndtime = year + "-" + month + "-" + day + " " + hour + ":" + min;

				// 先通过房间号和订单状态查询该房间的信息
				OrderServiceImpl orderService = new OrderServiceImpl();
				List<String> conditions = new ArrayList<String>();
				conditions.add("order_room_id like '%" + roomId + "%'");
				conditions.add("order_status like '%" + ordStatus + "%'");
				List<Order> list = orderService.findOrder(conditions);
				// TODO 子订单的费用在子订单算出来只有会在这里实现
				for (Order order : list) {
					orderID = order.getOrderId();
					orpName = order.getOrpName();
					ordOpentime = order.getOrdOpentime();
					ordrmPrice = order.getOrdrmPrice();
					ordAmtall = order.getOrdAmtall();
				}

				// TODO 通过订单ID 查询在子订单里的金额

				ordAllamtall = ordrmPrice + ordAmtall;

				// TODO 根据订单中的名字进行会员的模糊查询，如果找到，则证明是会员，总消费将会进行打折
				// 打9.5折
				MemberServiceImpl memberService = new MemberServiceImpl();
				List<String> memconditions = new ArrayList<String>();
				memconditions.add("mem_name like '%" + orpName + "%'");
				// conditions.add("order_status like '%" + ordStatus + "%'");
				List<Member> memlist = memberService.findMem(memconditions);
				// 如果找到，则证明是会员，将进行打折
				boolean isMember = false;
				for (Member member : memlist) {
					if (orpName.equals(member.getMemName())) {
						ordAllamtall = ordAllamtall * 0.95;
						isMember = true;
						break;
					}
				}

				money = ordAllamtall;
				
				Order order = new Order();

				order.getRoomId().setRoomId(roomId);
				order.setOrpName(orpName);
				order.setOrdOpentime(ordOpentime);
				order.setOrdEndtime(ordEndtime);
				order.setOrdrmPrice(ordrmPrice);
				order.setOrdAmtall(ordAmtall);
				order.setOrdAllamtall(ordAllamtall);
				order.setOrdStatus("1");
				order.setOperator(operator);
				order.setOrderId(orderID);

				try {
					orderService.updateOrder(order);
					if (isMember) {
						JOptionPane.showMessageDialog(
								contentPane, "尊敬的" + orpName + "客户，您本次共计消费" + (ordrmPrice + ordAmtall)
										+ "，会员打9.5折，最终消费为：" + ordAllamtall + "元",
								"提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contentPane, "尊敬的" + orpName + "客户，您本次共计消费" + ordAllamtall + "元",
								"提示", JOptionPane.INFORMATION_MESSAGE);
					}
					JOptionPane.showMessageDialog(contentPane, "结账成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					// 更新房间的状态
					room.setRoomType(roomType);
					room.setRoomPrice(roomPrice);
					room.setRoomStatus(roomStatus);
					room.setRoomId(roomId);
					roomService.updateRoom(room);
					closeAddGoodsOrCheckoutFrame();
				} catch (ServiceException E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}

			}
		});
		checkoutbtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		checkoutbtn.setBounds(90, 262, 93, 45);
		frame.getContentPane().add(checkoutbtn);

		JButton orderbtn = new JButton("消费详情");
		orderbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new RoomOrderFrame();

			}
		});
		orderbtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		orderbtn.setBounds(80, 191, 112, 45);
		frame.getContentPane().add(orderbtn);
	}

	public static void closeAddGoodsOrCheckoutFrame() {
		frame.setVisible(false);
		frame.dispose();
	}
}
