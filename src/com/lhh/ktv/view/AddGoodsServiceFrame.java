package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.EOrder;
import com.lhh.ktv.model.entity.Goods;
import com.lhh.ktv.model.entity.Order;
import com.lhh.ktv.model.service.IEOrderService;
import com.lhh.ktv.model.service.IGoodsService;
import com.lhh.ktv.model.service.impl.EOrderServiceImpl;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.model.service.impl.GoodsServiceImpl;
import com.lhh.ktv.model.service.impl.OrderServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.MyGoodsOnRoomTableModel;
import com.lhh.ktv.util.WindowMove;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * 
 * 商品服务
 * 
 * @author 46512
 *
 */

public class AddGoodsServiceFrame {

	private static JFrame frame;
	private JTextField addgoodsIDtxt;
	private JTextField addgoodsnametxt;
	private JTextField addgoodscounttxt;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// AddGoodsServiceFrame window = new AddGoodsServiceFrame();
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
	public AddGoodsServiceFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 398, 575);
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
		closebtn.setBounds(368, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);

		JLabel goodsservicelabel = new JLabel("商品服务");
		goodsservicelabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		goodsservicelabel.setBounds(136, 40, 108, 61);
		frame.getContentPane().add(goodsservicelabel);

		JLabel roomIDlabel = new JLabel("包房编号：");
		roomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		roomIDlabel.setBounds(42, 120, 108, 49);
		frame.getContentPane().add(roomIDlabel);

		Long roomIDshow;
		if ("经理".equals(EmployeeServiceImpl.getPost())) {
			roomIDshow = MainFrame.roomID;
		} else {
			roomIDshow = Test.roomID;
		}
		JLabel showroomIDlabel = new JLabel(String.valueOf(roomIDshow));
		showroomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showroomIDlabel.setBounds(162, 120, 108, 49);
		frame.getContentPane().add(showroomIDlabel);

		JLabel cusnamelabel = new JLabel("客户名字：");
		cusnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		cusnamelabel.setBounds(42, 182, 108, 49);
		frame.getContentPane().add(cusnamelabel);

		// 获取客户的名字
		JLabel showcusnamelabel = new JLabel(AddGoodsOrCheckout.cusName);
		showcusnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showcusnamelabel.setBounds(162, 182, 108, 49);
		frame.getContentPane().add(showcusnamelabel);

		JLabel goodsIDlabel = new JLabel("商品编号：");
		goodsIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		goodsIDlabel.setBounds(42, 244, 108, 49);
		frame.getContentPane().add(goodsIDlabel);

		JLabel goodsnamelabel = new JLabel("商品名称：");
		goodsnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		goodsnamelabel.setBounds(42, 311, 108, 49);
		frame.getContentPane().add(goodsnamelabel);

		JLabel addcountlabel = new JLabel("添加数量：");
		addcountlabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		addcountlabel.setBounds(42, 373, 108, 49);
		frame.getContentPane().add(addcountlabel);

		JButton addgoodsnobtn = new JButton("取消");
		addgoodsnobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 关闭该窗口
				closeAddGoodsSericeFrame();
			}
		});
		addgoodsnobtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodsnobtn.setBounds(225, 455, 81, 50);
		frame.getContentPane().add(addgoodsnobtn);

		addgoodsIDtxt = new JTextField();
		addgoodsIDtxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		addgoodsIDtxt.setBounds(146, 244, 140, 49);
		frame.getContentPane().add(addgoodsIDtxt);
		addgoodsIDtxt.setColumns(10);

		addgoodsnametxt = new JTextField();
		addgoodsnametxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		addgoodsnametxt.setColumns(10);
		addgoodsnametxt.setBounds(146, 306, 140, 49);
		frame.getContentPane().add(addgoodsnametxt);

		addgoodscounttxt = new JTextField();
		addgoodscounttxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		addgoodscounttxt.setColumns(10);
		addgoodscounttxt.setBounds(146, 373, 140, 49);
		frame.getContentPane().add(addgoodscounttxt);

		JButton addgoodsokbtn = new JButton("确认");
		addgoodsokbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long goodsID;
				if ("".equals(addgoodsIDtxt.getText())) {
					goodsID = -1L;
				} else {
					goodsID = Long.parseLong(addgoodsIDtxt.getText());
				}

				String goodsName = addgoodsnametxt.getText();
				int addgoodsCount = Integer.parseInt(addgoodscounttxt.getText());
				int goodsCount = 0; // 商品的库存
				@SuppressWarnings("unused")
				Long getGoodsID;// 万一没有输入要查询的商品ID，则需要通过名称查询，再获取商品的ID
				double goodsPrice = 0;// 商品的价格

				// 先根据商品的编号进行查询商品。如果商品存在并且商品的数量不小于0，则可以添加
				GoodsServiceImpl goodsService = new GoodsServiceImpl();
				List<String> goodsconditions = new ArrayList<String>();
				if (goodsID == -1L) {
					goodsconditions.add("goods_name like '%" + goodsName + "%'");
				} else if (goodsID != -1 && !("".equals(goodsName))) {
					goodsconditions.add("goods_id like '%" + goodsID + "%'");
					goodsconditions.add("goods_name like '%" + goodsName + "%'");
				} else if (goodsID != -1) {
					goodsconditions.add("goods_id = '" + goodsID + "'");
				} else {
					JOptionPane.showMessageDialog(contentPane, "温馨提醒，建议商品编号不为空", "提示", JOptionPane.INFORMATION_MESSAGE);
					goodsconditions.add("goods_id like '%" + goodsID + "%'");
					goodsconditions.add("goods_name like '%" + goodsName + "%'");
				}

				// conditions.add("order_status like '%" + ordStatus + "%'");
				List<Goods> goodslist = goodsService.findGoods(goodsconditions);
				for (Goods goods : goodslist) {
					if (goodsName.equals(goods.getGoodsName()) || (goodsID == goods.getGoodsId())) {
						getGoodsID = goods.getGoodsId();
						goodsName = goods.getGoodsName();
						goodsPrice = goods.getGoodsPrice();
						goodsCount = goods.getGoodsCount();
					}
					break;
				}

				if (goodsCount < 0) {
					// TODO 弹出该商品没有了的信息
					JOptionPane.showMessageDialog(contentPane,
							"商品：" + goodsName + "库存为：" + goodsCount + "，没有这件商品啦，快去叫经理添加吧！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (goodsCount < addgoodsCount) {
						JOptionPane.showMessageDialog(contentPane, "该商品不足，你只能添加" + goodsCount + "件该商品", "提示",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						// 查询订单的信息
						// 根据房间号和客户的名字确定订单的名称
						// 查询订单，获取订单的信息，后期完成之后添加到订单
						Long orderID = null;
						Long roomId;
						if ("经理".equals(EmployeeServiceImpl.getPost())) {
							roomId = MainFrame.roomID;
						} else {
							roomId = Test.roomID;
						}
						String orpName = showcusnamelabel.getText();// 消费者姓名
						String ordOpentime = null;// 开包时间
						String ordEndtime = null;// 结账时间
						double ordrmPrice = 0;// 包房费用
						double ordAmtall = 0;// 商品消费总额
						double ordAllamtall = 0;// 消费合计
						String ordStatus = "0";// 订单状态。0是初始状态，为未结账。1为已经结账
						String operator = null;// 操作人员

						// 根据添加查询的订单信息 并且获取这条数据的信息
						OrderServiceImpl orderService = new OrderServiceImpl();
						List<String> conditions = new ArrayList<String>();
						conditions.add("order_room_id like '%" + roomId + "%'");
						conditions.add("order_status like '%" + ordStatus + "%'");
						List<Order> list = orderService.findOrder(conditions);

						for (Order order : list) {
							orderID = order.getOrderId();
							// orpName = order.getOrpName();
							ordOpentime = order.getOrdOpentime();
							ordEndtime = order.getOrdEndtime();
							ordrmPrice = order.getOrdrmPrice();
							operator = order.getOperator();
						}

						// 生成一条子订单，将现在的订单ID插入到子订单中
						IEOrderService eOrderService = new EOrderServiceImpl();
						EOrder eOrder = new EOrder();

						double eOrderMoney = addgoodsCount * (goodsPrice + MyGoodsOnRoomTableModel.addMoney);// 子订单的金钱

						// goodsPrice +=
						// MyGoodsOnRoomTableModel.addMoney;//添加的价钱

						eOrder.getOrder().setOrderId(orderID);// 订单编号
						eOrder.setEgName(goodsName);// 商品名称
						eOrder.seteCount(addgoodsCount);// 添加的数量
						eOrder.setEgPrice(goodsPrice);// 添加商品的单价
						eOrder.seteMoney(eOrderMoney);

						// 生成子订单
						try {
							eOrderService.addIEorder(eOrder);
							// 生成订单成功。

							// 更改商品库存
							IGoodsService gsi = new GoodsServiceImpl();
							Goods goods = new Goods();
							goodsCount = goodsCount - addgoodsCount;
							goods.setGoodsName(goodsName);
							goods.setGoodsPrice(goodsPrice);
							goods.setGoodsCount(goodsCount);
							goods.setGoodsId(goodsID);
							gsi.updateGoods(goods);
							// 更新商品库存成功！

							// 遍历子订单，以订单的ID去查询，然后获取金额。
							// EOrderServiceImpl orderService = new
							// EOrderServiceImpl();
							List<String> eOrderconditions = new ArrayList<String>();
							eOrderconditions.add("eorder_order_id = '" + orderID + "'");
							// conditions.add("order_status like '%" + ordStatus
							// +
							// "%'");
							List<EOrder> eOrderlist = eOrderService.findIEorder(eOrderconditions);

							for (EOrder eOd : eOrderlist) {
								ordAmtall += eOd.geteMoney();
							}
							System.out.println("子订单的消费情况：" + ordAmtall);

							// 更新订单内容
							Order order = new Order();
							ordAllamtall = ordAmtall + ordrmPrice;
							order.getRoomId().setRoomId(roomId);
							order.setOrpName(orpName);
							order.setOrdOpentime(ordOpentime);
							order.setOrdEndtime(ordEndtime);
							order.setOrdrmPrice(ordrmPrice);
							order.setOrdAmtall(ordAmtall);// 消费的商品
							order.setOrdAllamtall(ordAllamtall);
							order.setOrdStatus(ordStatus);
							order.setOperator(operator);
							order.setOrderId(orderID);
							orderService.updateOrder(order);
							// 更新订单金额成功！
							// 弹出提示框
							int result = JOptionPane.showConfirmDialog(contentPane,
									"添加" + addgoodsCount + "件" + goodsName + "成功，是否要继续添加？", "提示",
									JOptionPane.OK_CANCEL_OPTION);
							if (result == 0) {
								closeAddGoodsSericeFrame();
								new AddGoodsServiceFrame();
								if("经理".equals(EmployeeServiceImpl.getPost())){
									MainFrame.roomGoodsFresh();
								}else{
									Test.roomGoodsFresh();
								}
								
							} else {
								if("经理".equals(EmployeeServiceImpl.getPost())){
									MainFrame.roomGoodsFresh();
								}else{
									Test.roomGoodsFresh();
								}
								closeAddGoodsSericeFrame();
							}
						} catch (ServiceException E) {
							// TODO Auto-generated catch block
							E.printStackTrace();
						}
					} // 添加判断
				}

			}
		});
		addgoodsokbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodsokbtn.setBounds(72, 455, 81, 50);
		frame.getContentPane().add(addgoodsokbtn);

	}

	public static void closeAddGoodsSericeFrame() {

		frame.setVisible(false);
		frame.dispose();

	}

}
