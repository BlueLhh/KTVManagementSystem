package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Member;
import com.lhh.ktv.model.entity.Reserve;
import com.lhh.ktv.model.entity.Room;
import com.lhh.ktv.model.service.IReserveService;
import com.lhh.ktv.model.service.IRoomService;
import com.lhh.ktv.model.service.impl.MemberServiceImpl;
import com.lhh.ktv.model.service.impl.ReserveServiceImpl;
import com.lhh.ktv.model.service.impl.RoomServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;

import javax.swing.JButton;

public class ReserveFrame {

	private static JFrame frame;
	private JPanel contentPane;
	private JTextField reserveyeartxt;
	private JTextField reservemothtxt;
	private JTextField reservedaytxt;
	private JTextField resevehourtxt;
	private JTextField resevemintxt;
	private JTextField retainyeartxt;
	private JTextField retainmonthtxt;
	private JTextField retaindaytxt;
	private JTextField retainhourtxt;
	private JTextField retainmintxt;
	private JTextField reservepnametxt;
	private JTextField resevephonetxt;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// ReserveFrame window = new ReserveFrame();
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
	public ReserveFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 734, 571);
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
		closebtn.setBounds(704, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);

		JLabel reserveinfotitlelabel = new JLabel("预定信息");
		reserveinfotitlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		reserveinfotitlelabel.setBounds(309, 36, 98, 60);
		frame.getContentPane().add(reserveinfotitlelabel);

		JLabel reserveroomIDlabel = new JLabel("预定包房编号：");
		reserveroomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		reserveroomIDlabel.setBounds(71, 121, 133, 55);
		frame.getContentPane().add(reserveroomIDlabel);

		JLabel roomIDlabel = new JLabel(String.valueOf(RoomInfoFrame.getRoomID));
		roomIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		roomIDlabel.setBounds(207, 121, 70, 55);
		frame.getContentPane().add(roomIDlabel);

		JLabel resesrveroomtypelabel = new JLabel("预定包房类型：");
		resesrveroomtypelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		resesrveroomtypelabel.setBounds(357, 121, 133, 55);
		frame.getContentPane().add(resesrveroomtypelabel);

		JLabel roomTypelabel = new JLabel(RoomInfoFrame.getRoomType);
		roomTypelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		roomTypelabel.setBounds(493, 121, 114, 55);
		frame.getContentPane().add(roomTypelabel);

		JLabel resrveropentimelabel = new JLabel("预定时间：");
		resrveropentimelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		resrveropentimelabel.setBounds(71, 196, 90, 55);
		frame.getContentPane().add(resrveropentimelabel);

		reserveyeartxt = new JTextField();
		reserveyeartxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		reserveyeartxt.setBounds(163, 196, 98, 55);
		frame.getContentPane().add(reserveyeartxt);
		reserveyeartxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("-");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(265, 216, 12, 18);
		frame.getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("-");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(338, 216, 12, 18);
		frame.getContentPane().add(label);

		reservemothtxt = new JTextField();
		reservemothtxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		reservemothtxt.setColumns(10);
		reservemothtxt.setBounds(275, 196, 59, 55);
		frame.getContentPane().add(reservemothtxt);

		reservedaytxt = new JTextField();
		reservedaytxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		reservedaytxt.setColumns(10);
		reservedaytxt.setBounds(348, 196, 59, 55);
		frame.getContentPane().add(reservedaytxt);

		resevehourtxt = new JTextField();
		resevehourtxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		resevehourtxt.setColumns(10);
		resevehourtxt.setBounds(421, 196, 59, 55);
		frame.getContentPane().add(resevehourtxt);

		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(490, 214, 12, 18);
		frame.getContentPane().add(label_1);

		resevemintxt = new JTextField();
		resevemintxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		resevemintxt.setColumns(10);
		resevemintxt.setBounds(503, 196, 59, 55);
		frame.getContentPane().add(resevemintxt);

		JLabel retaintimelabel = new JLabel("保留时间：");
		retaintimelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		retaintimelabel.setBounds(71, 274, 90, 55);
		frame.getContentPane().add(retaintimelabel);

		retainyeartxt = new JTextField();
		retainyeartxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		retainyeartxt.setColumns(10);
		retainyeartxt.setBounds(163, 274, 98, 55);
		frame.getContentPane().add(retainyeartxt);

		JLabel label_3 = new JLabel("-");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(265, 294, 12, 18);
		frame.getContentPane().add(label_3);

		retainmonthtxt = new JTextField();
		retainmonthtxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		retainmonthtxt.setColumns(10);
		retainmonthtxt.setBounds(275, 274, 59, 55);
		frame.getContentPane().add(retainmonthtxt);

		JLabel label_4 = new JLabel("-");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(338, 294, 12, 18);
		frame.getContentPane().add(label_4);

		retaindaytxt = new JTextField();
		retaindaytxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		retaindaytxt.setColumns(10);
		retaindaytxt.setBounds(348, 274, 59, 55);
		frame.getContentPane().add(retaindaytxt);

		retainhourtxt = new JTextField();
		retainhourtxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		retainhourtxt.setColumns(10);
		retainhourtxt.setBounds(421, 274, 59, 55);
		frame.getContentPane().add(retainhourtxt);

		JLabel label_5 = new JLabel(":");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_5.setBounds(490, 292, 12, 18);
		frame.getContentPane().add(label_5);

		retainmintxt = new JTextField();
		retainmintxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		retainmintxt.setColumns(10);
		retainmintxt.setBounds(503, 274, 59, 55);
		frame.getContentPane().add(retainmintxt);

		JLabel resevepnamelabel = new JLabel("预定人姓名：");
		resevepnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		resevepnamelabel.setBounds(71, 360, 108, 55);
		frame.getContentPane().add(resevepnamelabel);

		reservepnametxt = new JTextField();
		reservepnametxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		reservepnametxt.setColumns(10);
		reservepnametxt.setBounds(177, 360, 133, 55);
		frame.getContentPane().add(reservepnametxt);

		JLabel reservephonelabel = new JLabel("预定人电话：");
		reservephonelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		reservephonelabel.setBounds(348, 360, 108, 55);
		frame.getContentPane().add(reservephonelabel);

		resevephonetxt = new JTextField();
		resevephonetxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		resevephonetxt.setColumns(10);
		resevephonetxt.setBounds(454, 360, 172, 55);
		frame.getContentPane().add(resevephonetxt);

		JButton reserveokbtn = new JButton("确认预定");
		reserveokbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 先查询会员列表，查找是否是会员。
				Long memID = null;
				String name = reservepnametxt.getText();
				String phone = resevephonetxt.getText();
				MemberServiceImpl mem = new MemberServiceImpl();
				List<String> conditions = new ArrayList<String>();
				conditions.add("mem_name like '%" + name + "%'");
				conditions.add("mem_phone like '%" + phone + "%'");
				List<Member> list = mem.findMem(conditions);
				for (Member member : list) {
					if (name.equals(member.getMemName()) && phone.equals(member.getMemPhone())) {
						memID = member.getMemId();
					}
				}

				if (memID == null) {
					memID = 0L;
				}

				System.out.println("--------预定单里的会员编号：" + memID);
				// 获取系统时间---------------------------------------------------------------------------------
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
				String time = df.format(new Date()).trim();
				System.out.println(time);
				// 截取时间
				String year = time.substring(0, 4);
				String month = time.substring(5, 7);
				String day = time.substring(8, 10);
				String hour = time.substring(11, 13);
				String min = time.substring(14, 16);

				String sysTime = year + month + day + hour + min;
				System.out.println("sysTime=" + sysTime);
				// 预定时间
				String resyeartxt = reserveyeartxt.getText();
				String resmonthtxt = reservemothtxt.getText();
				String resdaytxt = reservedaytxt.getText();
				String reshourtxt = resevehourtxt.getText();
				String resmintxt = resevemintxt.getText();

				if (resyeartxt.equals("")) {
					resyeartxt = year;
				}
				if (resmonthtxt.equals("")) {
					resmonthtxt = month;
				}
				if (resdaytxt.equals("")) {
					resdaytxt = day;
				}
				if (reshourtxt.equals("")) {
					reshourtxt = hour;
				}
				if (resmintxt.equals("")) {
					resmintxt = min;
				}
				if (resmonthtxt.length() < 2) {
					resmonthtxt = "0" + resmonthtxt;
				}

				String resTime = resyeartxt + resmonthtxt + resdaytxt + reshourtxt + resmintxt;
				System.out.println("------resTime=" + resTime);
				// 保留时间
				String retyeartxt = retainyeartxt.getText();
				String retmonthtxt = retainmonthtxt.getText();
				String retdaytxt = retaindaytxt.getText();
				String rethourtxt = retainhourtxt.getText();
				String retmintxt = retainmintxt.getText();

				if (retyeartxt.equals("")) {
					retyeartxt = year;
				}
				if (retmonthtxt.equals("")) {
					retmonthtxt = month;
				}
				if (retdaytxt.equals("")) {
					retdaytxt = day;
				}
				if (rethourtxt.equals("")) {
					rethourtxt = hour;
				}
				if (retmintxt.equals("")) {
					retmintxt = min;
				}
				if (retmonthtxt.length() < 2) {
					retmonthtxt = "0" + retmonthtxt;
				}

				String retTime = retyeartxt + retmonthtxt + retdaytxt + rethourtxt + retmintxt;

				System.out.println("------retTime=" + retTime);

				// if ((Integer.parseInt(sysTime) < Integer.parseInt(resTime)))
				// {
				// JOptionPane.showMessageDialog(contentPane,
				// "预定时间必须大于或等于当前时间！", "提示",
				// JOptionPane.INFORMATION_MESSAGE);
				// } else if (Integer.parseInt(resTime) <
				// Integer.parseInt(retTime)) {
				// JOptionPane.showMessageDialog(contentPane, "保留时间必须大于预定的时间！",
				// "提示", JOptionPane.INFORMATION_MESSAGE);
				// }

				resTime = resyeartxt + "-" + resmonthtxt + "-" + resdaytxt + " " + reshourtxt + ":" + resmintxt;
				retTime = retyeartxt + "-" + retmonthtxt + "-" + retdaytxt + " " + rethourtxt + ":" + retmintxt;

				System.out.println(resTime);
				System.out.println(retTime);

				IReserveService reserveService = new ReserveServiceImpl();
				Reserve reserve = new Reserve();

				reserve.getRoom().setRoomId(Long.parseLong(roomIDlabel.getText()));
				reserve.setResTime(resTime);
				reserve.setResendTime(retTime);
				// reserve.setResPname(reservepnametxt.getText());
				reserve.setResPname(name);
				reserve.setResmemId(memID);
				// reserve.setResPhone(resevephonetxt.getText());
				reserve.setResPhone(phone);

				try {
					reserveService.addReserve(reserve);
					JOptionPane.showMessageDialog(contentPane, "预定成功！，房间号为：" + roomIDlabel.getText(), "提示",
							JOptionPane.INFORMATION_MESSAGE);

					// 下单成功后，更改数据库包房的状态
					IRoomService roomService = new RoomServiceImpl();
					Room room = new Room();
					String status = "已预定";
					room.setRoomType(roomTypelabel.getText());
					room.setRoomPrice(RoomInfoFrame.getRoomPrice);
					room.setRoomStatus(status);
					room.setRoomId(Long.parseLong(roomIDlabel.getText()));

					roomService.updateRoom(room);
					// 预定成功，关闭该窗口
					closeReserveFrame();
					RoomInfoFrame.closeRoomInfoFrame();
				} catch (ServiceException ee) {
					ee.printStackTrace();
				}

			}
		});
		reserveokbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		reserveokbtn.setBounds(177, 450, 144, 53);
		frame.getContentPane().add(reserveokbtn);

		JButton reservenobtn = new JButton("取消预定");
		reservenobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 取消预定，关闭该窗口
				closeReserveFrame();
				RoomInfoFrame.closeRoomInfoFrame();

			}
		});
		reservenobtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		reservenobtn.setBounds(403, 450, 144, 53);
		frame.getContentPane().add(reservenobtn);

	}

	static public void closeReserveFrame() {
		frame.setVisible(false);
		frame.dispose();
	}

}
