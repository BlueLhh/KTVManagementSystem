package com.lhh.ktv.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.entity.Goods;
import com.lhh.ktv.model.entity.Member;
import com.lhh.ktv.model.entity.Order;
import com.lhh.ktv.model.entity.Room;
import com.lhh.ktv.model.service.IGoodsService;
import com.lhh.ktv.model.service.IMemberService;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.model.service.impl.GoodsServiceImpl;
import com.lhh.ktv.model.service.impl.MemberServiceImpl;
import com.lhh.ktv.model.service.impl.OrderServiceImpl;
import com.lhh.ktv.model.service.impl.RoomServiceImpl;
import com.lhh.ktv.util.BGJPanel;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.JTableToExcel;
import com.lhh.ktv.util.MyEmpTableModel;
import com.lhh.ktv.util.MyGoodsOnRoomTableModel;
import com.lhh.ktv.util.MyGoodsTableModel;
import com.lhh.ktv.util.MyMemTableModel;
import com.lhh.ktv.util.MyOrderTableModel;
import com.lhh.ktv.util.SetTableCenter;
import com.lhh.ktv.util.WindowMove;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.swing.JRadioButton;

public class EmpMainFrame extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 43473320193261407L;
	private JPanel contentPane;
	// private CardLayout card;
	private JLabel getTime;

	private static JButton refreshbtn;

	private static JTextField querytxtName;
	private static JTextField querytxtNum;
	// 数据表的创建
	private static JTable dataTable;
	private static JTable memDataTable;
	private static JTable goodsDataTable;

	private static JTable allOrderDataTable;
	private  JTable histOrderDataTable;

	private static MyEmpTableModel model;
	private static MyMemTableModel memmodel;
	private static MyGoodsTableModel goodsmodel;
	private static MyOrderTableModel ordermodel;
	private static MyGoodsOnRoomTableModel goodsrmodel;

	// 装数据的panel要设置一个静态变量，和本身就有的无关！
	static JPanel dataPanel = new JPanel();

	static JPanel memdatapanel = new JPanel();

	static JPanel goodsdatatablepanel = new JPanel();

	// TODO 定义静态的ID方法
	static private Long getID;
	static private Long getmemID;
	static private Long getgoodsID;
	static private JTextField findmemnametxt;
	static private JLabel findnotmem;
	private JTextField addnametxt;
	private JTextField addagetxt;
	private JTextField addphonetxt;
	static private JTextField memnametxt;
	static private JTextField memagetxt;
	static private JTextField memphonetxt;

	static private JLabel memIDlabel;
	static private JLabel goodsidlabel;
	static private JTable goodsRoomDataTable;
	static private JRadioButton radmemman;
	static private JRadioButton radmemwom;
	static private JTextField findgoodstxt;
	private JTextField addgoodsnametxt;
	private JTextField addgoodspricetxt;
	private JTextField addgoodscounttxt;
	static private JTextField goodsnametxt;
	static private JTextField goodspricetxt;
	static private JTextField goodscounttxt;
	static public Long eorderDataID;

	// static private JLabel showallmoneylabel;
	private double allMoney = 0;

	public static Long roomID;
	private JTextField checkmonthtxt;

	public static Long getGetID() {
		return getID;
	}

	public static void setGetID(Long getID) {
		EmpMainFrame.getID = getID;
	}

	/**
	 * 
	 * 在这个类中，因为使用的是JFrame(创建的时候选择的是JFrame)，所以在拖拽的时候自动生成的方法是在构造函数中
	 * 由于使用了外部方法，在跳转窗口的时候，因为在新建的创建直接调用构造方法，所以导致使用的隐藏边框和设置时间
	 * 等方法失效（之前写在主函数中）。又因为在LoginFrame那边登录直接是new 出MainFrame界面，调用的是无参构造函数。
	 * 所以不能在无参构造函数中写之前在主函数的方法。不然会无限开启新的面板
	 * 因此给一个有参构造函数，有参构造函数中写之前主函数的方法。再在有参构造函数中new一个新的对象，新的对象默认使用
	 * 无参构造函数。再隐藏和销毁掉就好了
	 * 
	 * 
	 * @param jFrame
	 */
	public EmpMainFrame(JFrame jFrame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(false);
					dispose();
					EmpMainFrame frame = new EmpMainFrame();
					BorderHide.setJFrameHide(frame);// 隐藏边框
					new WindowMove().install(frame);// 边框隐藏之后可以移动
					new Thread(frame).start();// 获取系统时间
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmpMainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1370, 800);
		contentPane = new BGJPanel();
		((BGJPanel) contentPane).bigMainFrame();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BGJPanel topPanel = new BGJPanel();
		topPanel.topPanel();
		topPanel.setBounds(0, 0, 1370, 150);
		topPanel.setOpaque(false);// 设置JPanel为透明
		contentPane.add(topPanel);
		topPanel.setLayout(null);

		JButton btnExit = new JButton();
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		BtnEvent.btnExit(btnExit);
		btnExit.setBounds(1340, 0, 30, 30);
		topPanel.add(btnExit);
		BorderHide.setBtnBorderHide(btnExit);

		BGJPanel menuPanel = new BGJPanel();
		menuPanel.menBg();
		menuPanel.setBounds(0, 190, 300, 590);
		menuPanel.setOpaque(false);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		// TODO 菜单栏切换开始
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.PINK);
		mainPanel.setBounds(300, 190, 1000, 590);
		contentPane.add(mainPanel);
		CardLayout cardmain = new CardLayout(0, 0);
		mainPanel.setLayout(cardmain);

		BGJPanel frist = new BGJPanel();
		frist.fristBg();
		frist.setOpaque(false);
		mainPanel.add(frist, "frist");

		BGJPanel roomJpanel = new BGJPanel();
		roomJpanel.mainBG();
		mainPanel.add(roomJpanel, "room");
		roomJpanel.setLayout(null);

		BGJPanel roominfopanel = new BGJPanel();
		roominfopanel.roomsBG();
		roominfopanel.setBounds(0, 0, 630, 563);
		roomJpanel.add(roominfopanel);
		roominfopanel.setLayout(null);

		JLabel room168label = new JLabel("未使用");
		refRoomLabel(168L, room168label);
		room168label.setBounds(106, 181, 54, 18);
		roominfopanel.add(room168label);

		JLabel room178label = new JLabel("未使用");
		refRoomLabel(178L, room178label);
		room178label.setBounds(289, 181, 54, 18);
		roominfopanel.add(room178label);

		JLabel room188label = new JLabel("未使用");
		refRoomLabel(188L, room188label);
		room188label.setBounds(480, 181, 54, 18);
		roominfopanel.add(room188label);

		JLabel room268label = new JLabel("未使用");
		refRoomLabel(268L, room268label);
		room268label.setBounds(106, 349, 54, 18);
		roominfopanel.add(room268label);

		JLabel room278label = new JLabel("未使用");
		refRoomLabel(278L, room278label);
		room278label.setBounds(289, 349, 54, 18);
		roominfopanel.add(room278label);

		JLabel room288label = new JLabel("未使用");
		refRoomLabel(288L, room288label);
		room288label.setBounds(480, 349, 54, 18);
		roominfopanel.add(room288label);

		JLabel room368label = new JLabel("未使用");
		refRoomLabel(368L, room368label);
		room368label.setBounds(106, 517, 54, 18);
		roominfopanel.add(room368label);

		JLabel room378label = new JLabel("未使用");
		refRoomLabel(378L, room378label);
		room378label.setBounds(289, 517, 54, 18);
		roominfopanel.add(room378label);

		JLabel room388label = new JLabel("未使用");
		refRoomLabel(388L, room388label);
		room388label.setBounds(480, 517, 54, 18);
		roominfopanel.add(room388label);

		JButton room168btn = new JButton("168");
		// TODO 监听鼠标点击事件，解决双击房间号出现两个面板其中一个不能取消
		room168btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (e.getClickCount() == 1) {
					refRoomLabel(168L, room168label);
					// if("使用中".equals(room168label.getText())){
					//
					// }
				}
				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room168btn.getText());
					if (room168label.getText().equals("未使用")) {
						new RoomInfoFrame();
					}
					if (room168label.getText().equals("已预定")) {
						new OpenOrCancelFrame();
					}
					if (room168label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}

			}

		});
		room168btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room168btn.setBounds(73, 56, 112, 112);
		roominfopanel.add(room168btn);

		JButton room178btn = new JButton("178");
		room178btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				if (e.getClickCount() == 1) {
					refRoomLabel(178L, room178label);
					// if("使用中".equals(room178label.getText())){
					// //new RoomOrderFrame();
					// }
				}

				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room178btn.getText());
					refRoomLabel(178L, room178label);
					if (room178label.getText().equals("未使用")) {
						new RoomInfoFrame();
					} else if (room178label.getText().equals("已预定")) {
						new OpenOrCancelFrame();
					} else if (room178label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}
			}
		});
		room178btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room178btn.setBounds(258, 56, 112, 112);
		roominfopanel.add(room178btn);

		JButton room188btn = new JButton("188");
		room188btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				if (e.getClickCount() == 1) {
					refRoomLabel(188L, room188label);
				}

				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room188btn.getText());
					refRoomLabel(188L, room188label);
					if (room188label.getText().equals("未使用")) {
						new RoomInfoFrame();
					}
					if (room188label.getText().equals("已预定")) {
						new OpenOrCancelFrame();
					}
					if (room188label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}
			}
		});
		room188btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room188btn.setBounds(443, 56, 112, 112);
		roominfopanel.add(room188btn);

		JButton room268btn = new JButton("268");
		room268btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				if (e.getClickCount() == 1) {
					refRoomLabel(268L, room268label);
				}

				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room268btn.getText());
					if (room268label.getText().equals("未使用")) {
						new RoomInfoFrame();
					}
					if (room268label.getText().equals("已预定")) {
						new OpenOrCancelFrame();
					}
					if (room268label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}
			}
		});
		room268btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room268btn.setBounds(73, 224, 112, 112);
		roominfopanel.add(room268btn);

		JButton room278btn = new JButton("278");
		room278btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				if (e.getClickCount() == 1) {
					refRoomLabel(278L, room278label);
				}

				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room278btn.getText());
					if (room278label.getText().equals("未使用")) {
						new RoomInfoFrame();
					}
					if (room278label.getText().equals("已预定")) {
						new OpenOrCancelFrame();
					}
					if (room278label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}
			}
		});
		room278btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room278btn.setBounds(258, 224, 112, 112);
		roominfopanel.add(room278btn);

		JButton room288btn = new JButton("288");
		room288btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				if (e.getClickCount() == 1) {
					refRoomLabel(288L, room288label);
				}

				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room288btn.getText());
					if (room288label.getText().equals("未使用")) {
						new RoomInfoFrame();
					}
					if (room288label.getText().equals("已预定")) {
						new OpenOrCancelFrame();
					}
					if (room288label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}
			}
		});
		room288btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room288btn.setBounds(443, 224, 112, 112);
		roominfopanel.add(room288btn);

		JButton room368btn = new JButton("368");
		room368btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				if (e.getClickCount() == 1) {
					refRoomLabel(368L, room368label);
				}

				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room368btn.getText());
					if (room368label.getText().equals("未使用")) {
						new RoomInfoFrame();
					}
					if (room368label.getText().equals("已预定")) {
						new OpenOrCancelFrame();
					}
					if (room368label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}
			}
		});
		room368btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room368btn.setBounds(73, 392, 112, 112);
		roominfopanel.add(room368btn);

		JButton room378btn = new JButton("378");
		room378btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				if (e.getClickCount() == 1) {
					refRoomLabel(378L, room378label);
				}

				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room378btn.getText());
					if (room378label.getText().equals("未使用")) {
						new RoomInfoFrame();
					}
					if (room378label.getText().equals("已预定")) {
						new OpenOrCancelFrame();
					}
					if (room378label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}
			}
		});
		room378btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room378btn.setBounds(258, 392, 112, 112);
		roominfopanel.add(room378btn);

		JButton room388btn = new JButton("388");
		room388btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				if (e.getClickCount() == 1) {
					refRoomLabel(388L, room388label);
				}

				if (e.getClickCount() == 2) {
					roomID = Long.parseLong(room388btn.getText());
					if (room388label.getText().equals("未使用")) {
						new RoomInfoFrame();
					}
					if (room388label.getText().equals("已预定")) {
						new OpenOrCancelFrame();

					}
					if (room388label.getText().equals("使用中")) {
						new AddGoodsOrCheckout();
					}
				}
			}
		});
		room388btn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		room388btn.setBounds(443, 392, 112, 112);
		roominfopanel.add(room388btn);

		BGJPanel roomgoodsinfopanel = new BGJPanel();
		roomgoodsinfopanel.roomsrBG();
		roomgoodsinfopanel.setBounds(630, 0, 371, 563);
		roomJpanel.add(roomgoodsinfopanel);
		roomgoodsinfopanel.setLayout(null);

		JLabel goodslabel = new JLabel("商品清单表");
		goodslabel.setForeground(Color.WHITE);
		goodslabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		goodslabel.setBounds(134, 32, 113, 56);
		roomgoodsinfopanel.add(goodslabel);

		JPanel roomgoodsdatapanel = new JPanel();
		roomgoodsdatapanel.setBounds(0, 103, 371, 460);
		roomgoodsinfopanel.add(roomgoodsdatapanel);
		roomgoodsdatapanel.setLayout(null);

		BGJPanel memJpanel = new BGJPanel();
		memJpanel.mainBG();
		mainPanel.add(memJpanel, "mem");
		memJpanel.setLayout(null);

		BGJPanel findadd = new BGJPanel();
		findadd.memtopBG();
		findadd.setBounds(0, 0, 643, 139);
		memJpanel.add(findadd);
		findadd.setLayout(null);

		JLabel findnamelabel = new JLabel("名字查询：");
		findnamelabel.setForeground(Color.WHITE);
		findnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findnamelabel.setBounds(53, 44, 96, 51);
		findadd.add(findnamelabel);

		findmemnametxt = new JTextField();
		findmemnametxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findmemnametxt.setBounds(158, 44, 181, 51);
		findadd.add(findmemnametxt);
		findmemnametxt.setColumns(10);

		findnotmem = new JLabel("*没有这个人");
		findnotmem.setForeground(Color.RED);
		findnotmem.setBounds(53, 108, 90, 18);
		findnotmem.setVisible(false);// 设置不可见
		findadd.add(findnotmem);

		JPanel memdatapanel = new JPanel();
		memdatapanel.setBackground(Color.PINK);
		memdatapanel.setBounds(0, 140, 643, 424);
		memJpanel.add(memdatapanel);
		memdatapanel.setLayout(null);

		JPanel infopanel = new JPanel();
		infopanel.setBackground(Color.LIGHT_GRAY);
		infopanel.setBounds(641, 0, 359, 564);
		memJpanel.add(infopanel);
		CardLayout cardmeminfo = new CardLayout(0, 0);
		infopanel.setLayout(cardmeminfo);

		JPanel addpanel = new JPanel();
		addpanel.setBackground(Color.LIGHT_GRAY);
		infopanel.add(addpanel, "addpanel");
		addpanel.setLayout(null);

		JButton addmembtn = new JButton("注册会员");
		addmembtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cardmeminfo.show(infopanel, "addpanel");

			}
		});
		addmembtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addmembtn.setBounds(455, 44, 157, 51);
		findadd.add(addmembtn);

		JButton refreshmembtn = new JButton("刷新");
		refreshmembtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				memRefresh();

			}
		});
		refreshmembtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		refreshmembtn.setBounds(543, 104, 69, 27);
		findadd.add(refreshmembtn);

		BGJPanel infofirstpanel = new BGJPanel();
		infofirstpanel.infofirstpanelBG();
		infopanel.add(infofirstpanel, "infoshow");

		JLabel addnamelable = new JLabel("姓名：");
		addnamelable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addnamelable.setBounds(72, 163, 57, 43);
		addpanel.add(addnamelable);

		JLabel addgenderlable = new JLabel("性别：");
		addgenderlable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgenderlable.setBounds(72, 229, 57, 43);
		addpanel.add(addgenderlable);

		JLabel addagelable = new JLabel("年龄：");
		addagelable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addagelable.setBounds(72, 298, 57, 43);
		addpanel.add(addagelable);

		JLabel addphonelable = new JLabel("联系电话：");
		addphonelable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addphonelable.setBounds(39, 365, 90, 43);
		addpanel.add(addphonelable);

		JLabel lblNewLabel = new JLabel("注册会员");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		lblNewLabel.setBounds(122, 45, 115, 83);
		addpanel.add(lblNewLabel);

		addnametxt = new JTextField();
		addnametxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		addnametxt.setBounds(132, 163, 176, 43);
		addpanel.add(addnametxt);
		addnametxt.setColumns(10);

		JRadioButton radMan = new JRadioButton("男");
		radMan.setBackground(Color.LIGHT_GRAY);
		radMan.setSelected(true);
		radMan.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radMan.setBounds(122, 239, 47, 27);
		addpanel.add(radMan);

		JRadioButton radWom = new JRadioButton("女");
		radWom.setBackground(Color.LIGHT_GRAY);
		radWom.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radWom.setBounds(175, 239, 47, 27);
		addpanel.add(radWom);

		ButtonGroup addGroup = new ButtonGroup();
		addGroup.add(radMan);
		addGroup.add(radWom);

		addagetxt = new JTextField();
		addagetxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		addagetxt.setColumns(10);
		addagetxt.setBounds(132, 298, 176, 43);
		addpanel.add(addagetxt);

		addphonetxt = new JTextField();
		addphonetxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addphonetxt.setColumns(10);
		addphonetxt.setBounds(132, 365, 176, 43);
		addpanel.add(addphonetxt);

		/**
		 * TODO 会员注册成功！
		 * 
		 */
		JButton addokbtn = new JButton("确认");
		addokbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name;
				String gender;
				byte age;
				String phone;

				// 建立服务接口类类 服务实现类
				IMemberService memberService = new MemberServiceImpl();
				Member member = new Member();

				if ("".equals(addnametxt.getText()) || "".equals(addagetxt.getText())) {
					JOptionPane.showMessageDialog(contentPane, "请输入完整的信息！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {

					name = addnametxt.getText();

					if (radMan.isSelected()) {
						gender = "男";
					} else {
						gender = "女";
					}

					age = (byte) Integer.parseInt(addagetxt.getText());
					phone = addphonetxt.getText();

					member.setMemName(name);
					member.setMemGender(gender);
					member.setMemAge(age);
					member.setMemPhone(phone);

					try {
						memberService.addMem(member);
						JOptionPane.showMessageDialog(contentPane, "会员注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						memRefresh();
					} catch (ServiceException ee) {
						ee.printStackTrace();
					}

				}
			}
		});
		addokbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addokbtn.setBounds(68, 461, 90, 57);
		addpanel.add(addokbtn);

		// 取消重置所有选项
		JButton addclosebtn = new JButton("取消");
		addclosebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addnametxt.setText("");
				radMan.setSelected(true);
				addagetxt.setText("");
				addphonetxt.setText("");

			}
		});
		addclosebtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addclosebtn.setBounds(206, 461, 90, 57);
		addpanel.add(addclosebtn);

		JPanel delandupd = new JPanel();
		delandupd.setBackground(Color.LIGHT_GRAY);
		infopanel.add(delandupd, "delandupd");
		delandupd.setLayout(null);

		JLabel meminfolabel = new JLabel("会员信息");
		meminfolabel.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		meminfolabel.setBounds(122, 32, 114, 62);
		delandupd.add(meminfolabel);

		JLabel tishi = new JLabel("*编辑信息可以进行修改");
		tishi.setBounds(41, 101, 163, 18);
		delandupd.add(tishi);

		JLabel delupdIDLabel = new JLabel("会员编号：");
		delupdIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delupdIDLabel.setBounds(41, 147, 97, 47);
		delandupd.add(delupdIDLabel);

		memIDlabel = new JLabel("");
		memIDlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		memIDlabel.setBounds(152, 147, 66, 47);
		delandupd.add(memIDlabel);

		JLabel addupdlabel = new JLabel("会员姓名：");
		addupdlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addupdlabel.setBounds(41, 207, 97, 47);
		delandupd.add(addupdlabel);

		JLabel addupdgenderlabel = new JLabel("会员性别：");
		addupdgenderlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addupdgenderlabel.setBounds(41, 267, 97, 47);
		delandupd.add(addupdgenderlabel);

		JLabel addupdagelabel = new JLabel("会员年龄：");
		addupdagelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addupdagelabel.setBounds(41, 327, 97, 47);
		delandupd.add(addupdagelabel);

		JLabel addupdphonelabel = new JLabel("联系方式：");
		addupdphonelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addupdphonelabel.setBounds(41, 387, 97, 47);
		delandupd.add(addupdphonelabel);

		memnametxt = new JTextField();
		memnametxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		memnametxt.setBounds(137, 207, 163, 47);
		delandupd.add(memnametxt);
		memnametxt.setColumns(10);

		memagetxt = new JTextField();
		memagetxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		memagetxt.setColumns(10);
		memagetxt.setBounds(137, 327, 163, 47);
		delandupd.add(memagetxt);

		memphonetxt = new JTextField();
		memphonetxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		memphonetxt.setColumns(10);
		memphonetxt.setBounds(137, 387, 163, 47);
		delandupd.add(memphonetxt);

		radmemman = new JRadioButton("男");
		radmemman.setBackground(Color.LIGHT_GRAY);
		radmemman.setSelected(true);
		radmemman.setBounds(137, 273, 54, 39);
		delandupd.add(radmemman);

		radmemwom = new JRadioButton("女");
		radmemwom.setBackground(Color.LIGHT_GRAY);
		radmemwom.setBounds(194, 273, 54, 39);
		delandupd.add(radmemwom);

		ButtonGroup delupdmem = new ButtonGroup();
		delupdmem.add(radmemman);
		delupdmem.add(radmemwom);

		// TODO 会员修改
		JButton updmembtn = new JButton("修改");
		updmembtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				IMemberService mem = new MemberServiceImpl();
				Member member = new Member();

				String name;
				String gender;
				byte age;
				String phone;
				Long id;

				name = memnametxt.getText();
				if (radmemman.isSelected()) {
					gender = "男";
				} else {
					gender = "女";
				}
				age = (byte) Integer.parseInt(memagetxt.getText());
				phone = memphonetxt.getText();
				id = Long.parseLong(memIDlabel.getText());

				member.setMemName(name);
				member.setMemGender(gender);
				member.setMemAge(age);
				member.setMemPhone(phone);
				member.setMemId(id);

				try {
					mem.updateMem(member);
					System.out.println("更新会员信息成功！");
					JOptionPane.showMessageDialog(contentPane, "更新会员信息成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					memRefresh();
				} catch (ServiceException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}

			}
		});
		updmembtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		updmembtn.setBounds(33, 483, 75, 47);
		delandupd.add(updmembtn);

		// TODO 注销会员
		JButton delmembtn = new JButton("注销");
		delmembtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(contentPane, "是否注销此会员？", "提示", JOptionPane.OK_CANCEL_OPTION);

				if (result == 0) {

					IMemberService mem = new MemberServiceImpl();

					Long id;

					id = Long.parseLong(memIDlabel.getText());

					try {
						mem.delMem(id);
						JOptionPane.showMessageDialog(contentPane, "注销会员信息成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						memIDlabel.setText("");
						memnametxt.setText("");
						radmemman.setSelected(true);
						memagetxt.setText("");
						memphonetxt.setText("");
						memRefresh();
					} catch (ServiceException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
				}
			}
		});
		delmembtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delmembtn.setBounds(141, 483, 75, 47);
		delandupd.add(delmembtn);

		JButton rturumembtn = new JButton("返回");
		rturumembtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardmeminfo.show(infopanel, "infoshow");
			}
		});
		rturumembtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		rturumembtn.setBounds(249, 483, 75, 47);
		delandupd.add(rturumembtn);

		BGJPanel goodsJpanel = new BGJPanel();
		goodsJpanel.mainBG();
		mainPanel.add(goodsJpanel, "goods");
		goodsJpanel.setLayout(null);

		BGJPanel goodstoppanel = new BGJPanel();
		goodstoppanel.goodstopBG();
		goodstoppanel.setBounds(0, 0, 655, 138);
		goodsJpanel.add(goodstoppanel);
		goodstoppanel.setLayout(null);

		JLabel findgoodsnamelabel = new JLabel("商品名称：");
		findgoodsnamelabel.setForeground(Color.WHITE);
		findgoodsnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findgoodsnamelabel.setBounds(44, 54, 95, 38);
		goodstoppanel.add(findgoodsnamelabel);

		findgoodstxt = new JTextField();
		findgoodstxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findgoodstxt.setBounds(138, 54, 221, 38);
		goodstoppanel.add(findgoodstxt);
		findgoodstxt.setColumns(10);

		JButton refreshgoodsbtn = new JButton("刷新");
		refreshgoodsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 刷新商品信息
				goodsRefresh();

			}
		});
		refreshgoodsbtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		refreshgoodsbtn.setBounds(494, 105, 79, 27);
		goodstoppanel.add(refreshgoodsbtn);

		JPanel goodsdatatablepanel = new JPanel();
		goodsdatatablepanel.setBounds(0, 139, 655, 426);
		goodsJpanel.add(goodsdatatablepanel);
		goodsdatatablepanel.setLayout(null);

		JPanel goodsinfopanel = new JPanel();
		goodsinfopanel.setBounds(656, 0, 345, 565);
		goodsJpanel.add(goodsinfopanel);
		CardLayout cardGoods = new CardLayout(0, 0);
		goodsinfopanel.setLayout(cardGoods);

		JPanel addgoodspanel = new JPanel();
		addgoodspanel.setBackground(Color.LIGHT_GRAY);
		goodsinfopanel.add(addgoodspanel, "addgoodspanel");
		addgoodspanel.setLayout(null);

		JLabel addgoodstitlelabel = new JLabel("添加商品");
		addgoodstitlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		addgoodstitlelabel.setBounds(117, 83, 111, 58);
		addgoodspanel.add(addgoodstitlelabel);

		JLabel addgoodsnamelabel = new JLabel("商品名称：");
		addgoodsnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodsnamelabel.setBounds(39, 193, 93, 43);
		addgoodspanel.add(addgoodsnamelabel);

		JLabel addgoodspricelable = new JLabel("商品价格：");
		addgoodspricelable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodspricelable.setBounds(39, 262, 93, 43);
		addgoodspanel.add(addgoodspricelable);

		JLabel addgoodscountlabel = new JLabel("商品库存：");
		addgoodscountlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodscountlabel.setBounds(39, 335, 93, 43);
		addgoodspanel.add(addgoodscountlabel);

		addgoodsnametxt = new JTextField();
		addgoodsnametxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodsnametxt.setBounds(132, 193, 187, 43);
		addgoodspanel.add(addgoodsnametxt);
		addgoodsnametxt.setColumns(10);

		addgoodspricetxt = new JTextField();
		addgoodspricetxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodspricetxt.setColumns(10);
		addgoodspricetxt.setBounds(132, 262, 187, 43);
		addgoodspanel.add(addgoodspricetxt);

		addgoodscounttxt = new JTextField();
		addgoodscounttxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodscounttxt.setColumns(10);
		addgoodscounttxt.setBounds(132, 335, 187, 43);
		addgoodspanel.add(addgoodscounttxt);

		JButton addgoodsokbtn = new JButton("确认");
		addgoodsokbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 添加商品如果输入为空的话！
				String name;
				double price;
				int count;

				if ("".equals(addgoodsnametxt.getText()) || "".equals(addgoodspricetxt.getText())
						|| "".equals(addgoodscounttxt.getText())) {
					JOptionPane.showMessageDialog(contentPane, "请输入信息！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {

					// 建立服务接口类类 服务实现类
					IGoodsService goodsService = new GoodsServiceImpl();
					Goods goods = new Goods();

					name = addgoodsnametxt.getText();

					price = Double.parseDouble(addgoodspricetxt.getText());

					count = Integer.parseInt(addgoodscounttxt.getText());

					goods.setGoodsName(name);
					goods.setGoodsPrice(price);
					goods.setGoodsCount(count);

					try {
						goodsService.addGoods(goods);
						JOptionPane.showMessageDialog(contentPane, "商品添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						goodsRefresh();
					} catch (ServiceException ee) {
						ee.printStackTrace();
					}
				}
			}
		});
		addgoodsokbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodsokbtn.setBounds(70, 439, 93, 65);
		addgoodspanel.add(addgoodsokbtn);

		JButton addgoodsnobtn = new JButton("取消");
		addgoodsnobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addgoodsnametxt.setText("");

				addgoodspricetxt.setText("");

				addgoodscounttxt.setText("");

			}
		});
		addgoodsnobtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodsnobtn.setBounds(200, 439, 93, 65);
		addgoodspanel.add(addgoodsnobtn);

		JPanel delandupdgoodspanel = new JPanel();
		delandupdgoodspanel.setBackground(Color.LIGHT_GRAY);
		goodsinfopanel.add(delandupdgoodspanel, "delandupdgoodspanel");
		delandupdgoodspanel.setLayout(null);

		JLabel titlelabel = new JLabel("编号为");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		titlelabel.setBounds(40, 51, 83, 58);
		delandupdgoodspanel.add(titlelabel);

		JLabel goodsnamelabel = new JLabel("商品名称：");
		goodsnamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		goodsnamelabel.setBounds(30, 172, 93, 43);
		delandupdgoodspanel.add(goodsnamelabel);

		goodsnametxt = new JTextField();
		goodsnametxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		goodsnametxt.setColumns(10);
		goodsnametxt.setBounds(123, 172, 187, 43);
		delandupdgoodspanel.add(goodsnametxt);

		JLabel goodspricelabel = new JLabel("商品价格：");
		goodspricelabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		goodspricelabel.setBounds(30, 241, 93, 43);
		delandupdgoodspanel.add(goodspricelabel);

		goodspricetxt = new JTextField();
		goodspricetxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		goodspricetxt.setColumns(10);
		goodspricetxt.setBounds(123, 241, 187, 43);
		delandupdgoodspanel.add(goodspricetxt);

		goodscounttxt = new JTextField();
		goodscounttxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		goodscounttxt.setColumns(10);
		goodscounttxt.setBounds(123, 314, 187, 43);
		delandupdgoodspanel.add(goodscounttxt);

		JLabel goodscountlabel = new JLabel("商品库存：");
		goodscountlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		goodscountlabel.setBounds(30, 314, 93, 43);
		delandupdgoodspanel.add(goodscountlabel);

		JButton delgoodsbtn = new JButton("删除");
		delgoodsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(contentPane, "是否删除该信息？", "提示", JOptionPane.OK_CANCEL_OPTION);

				if (result == 0) {
					IGoodsService goodsService = new GoodsServiceImpl();

					Long id;

					id = Long.parseLong(goodsidlabel.getText());

					try {
						goodsService.delGoods(id);
						JOptionPane.showMessageDialog(contentPane, "删除商品成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						goodsidlabel.setText("");
						goodsnametxt.setText("");
						goodspricetxt.setText("");
						goodscounttxt.setText("");
						goodsRefresh();
					} catch (ServiceException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
				}

			}
		});
		delgoodsbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delgoodsbtn.setBounds(16, 418, 93, 65);
		delandupdgoodspanel.add(delgoodsbtn);

		/**
		 * 
		 * TODO 更新信息，库存发生改变的时候
		 * 
		 */
		JButton updgoodsbtn = new JButton("更改");
		updgoodsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				IGoodsService goodsService = new GoodsServiceImpl();
				Goods goods = new Goods();

				String name;
				Double price;
				int count;
				int oldcount;
				Long id;

				name = goodsnametxt.getText();
				price = Double.parseDouble(goodspricetxt.getText());
				count = Integer.parseInt(goodscounttxt.getText());
				id = Long.parseLong(goodsidlabel.getText());
				/**
				 * 
				 * TODO 查询信息 先找后插入
				 * 
				 */
				try {

					goods = goodsService.findGoods(id);
					// 获取原来数据库的库存
					oldcount = goods.getGoodsCount();

					if (count != oldcount) {
						int result = JOptionPane.showConfirmDialog(contentPane, "库存发生更改，是否要增加" + count + "件商品？", "提示",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == 0) {
							count = oldcount + count;
							/**
							 * 
							 * 更新库存成功
							 * 
							 */
							goods.setGoodsName(name);
							goods.setGoodsPrice(price);
							goods.setGoodsCount(count);
							goods.setGoodsId(id);
							goodsService.updateGoods(goods);
							JOptionPane.showMessageDialog(contentPane, "更新商品信息成功！", "提示",
									JOptionPane.INFORMATION_MESSAGE);
							goodsRefresh();
						}
					} else {
						// 库存没有发生更改，其他项发生改变
						goods.setGoodsName(name);
						goods.setGoodsPrice(price);
						goods.setGoodsCount(count);
						goods.setGoodsId(id);
						goodsService.updateGoods(goods);
						JOptionPane.showMessageDialog(contentPane, "更新商品信息成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						goodsRefresh();
					}

				} catch (ServiceException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
			}
		});
		updgoodsbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		updgoodsbtn.setBounds(125, 418, 93, 65);
		delandupdgoodspanel.add(updgoodsbtn);

		JButton rtugoodsbtn = new JButton("返回");
		rtugoodsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardGoods.show(goodsinfopanel, "goodsinfomainpanel");
			}
		});
		rtugoodsbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		rtugoodsbtn.setBounds(234, 418, 93, 65);
		delandupdgoodspanel.add(rtugoodsbtn);

		goodsidlabel = new JLabel("");
		goodsidlabel.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		goodsidlabel.setBounds(123, 51, 44, 58);
		delandupdgoodspanel.add(goodsidlabel);

		JLabel infonextlabel = new JLabel("的商品信息如下");
		infonextlabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		infonextlabel.setBounds(165, 51, 162, 58);
		delandupdgoodspanel.add(infonextlabel);

		JLabel tishixinxilabel = new JLabel("*编辑信息可以进行修改");
		tishixinxilabel.setBounds(40, 122, 178, 18);
		delandupdgoodspanel.add(tishixinxilabel);

		BGJPanel goodsinfomainpanel = new BGJPanel();
		goodsinfopanel.add(goodsinfomainpanel, "goodsinfomainpanel");
		goodsinfomainpanel.goodsInfopanelBG();
		goodsinfomainpanel.setLayout(null);

		JButton addgoodsbtn = new JButton("添加商品");
		addgoodsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cardGoods.show(goodsinfopanel, "addgoodspanel");

			}
		});
		addgoodsbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addgoodsbtn.setBounds(460, 54, 113, 38);
		goodstoppanel.add(addgoodsbtn);

		BGJPanel empJpanel = new BGJPanel();
		empJpanel.mainBG();
		mainPanel.add(empJpanel, "emp");
		empJpanel.setLayout(null);

		BGJPanel functionPanel = new BGJPanel();
		functionPanel.empTopBG();
		functionPanel.setBounds(0, 0, 1000, 78);
		empJpanel.add(functionPanel);
		functionPanel.setLayout(null);

		JLabel queryName = new JLabel("姓名：");
		queryName.setForeground(Color.WHITE);
		queryName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		queryName.setBounds(101, 13, 64, 40);
		functionPanel.add(queryName);

		querytxtName = new JTextField();
		querytxtName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		querytxtName.setBounds(165, 16, 172, 40);
		functionPanel.add(querytxtName);
		querytxtName.setColumns(10);

		JLabel queryNum = new JLabel("账号：");
		queryNum.setForeground(Color.WHITE);
		queryNum.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		queryNum.setBounds(388, 13, 64, 40);
		functionPanel.add(queryNum);

		querytxtNum = new JTextField();
		querytxtNum.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		querytxtNum.setColumns(10);
		querytxtNum.setBounds(454, 16, 217, 40);
		functionPanel.add(querytxtNum);

		BGJPanel dataPanel = new BGJPanel();
		dataPanel.empDateBg();
		dataPanel.setBounds(0, 91, 845, 470);
		empJpanel.add(dataPanel);
		dataPanel.setLayout(null);

		/**
		 * 
		 * 按条件查询健查询（动态查询）
		 * 
		 */
		// TODO 按条件查询员工
		JButton queryEmp = new JButton("查询员工");
		queryEmp.addActionListener(new ActionListener() {
			private List<Employee> empList;

			public void actionPerformed(ActionEvent e) {
				EmployeeServiceImpl empSimp = new EmployeeServiceImpl();
				List<String> conditions = new ArrayList<String>();
				conditions.add("emp_name like '%" + querytxtName.getText() + "%'");
				conditions.add("emp_username like '%" + querytxtNum.getText() + "%'");
				empList = new ArrayList<Employee>();
				empList = empSimp.findEmployee(conditions);

				if (empList.size() == 0) {
					JOptionPane.showMessageDialog(contentPane, "没有这个人！", "提示", JOptionPane.INFORMATION_MESSAGE);
					querytxtName.setText("");
					querytxtNum.setText("");
				} else {
					// TODO 查询刷新
					if ("".equals(querytxtName.getText()) && "".equals(querytxtNum.getText())) {
						JOptionPane.showMessageDialog(contentPane, "不输入信息默认查询全部！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contentPane, "查询成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					// System.out.println(empList);

					JTable queryTable = new JTable();
					queryTable.setVisible(true);
					SetTableCenter.setTableCenter(queryTable);// 设置表格中内容居中
					MyEmpTableModel model = new MyEmpTableModel(empList);
					queryTable.setModel(model);
					JScrollPane dataScrollPane = new JScrollPane(queryTable);
					// dataScrollPane.setOpaque(false);
					// dataScrollPane.getViewport().setOpaque(false);//JScrollPane
					// 透明
					dataScrollPane.setBounds(0, 0, 845, 470);
					dataPanel.add(dataScrollPane);

					queryTable.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							if (evt.getClickCount() == 2) {
								int row = dataTable.getSelectedRow();
								String data;
								data = String.valueOf(model.getValueAt(row, 0));

								System.out.println("data:" + data);
								for (Employee employee : empList) {
									// System.out.println(employee.getEmpId());
									if (employee.getEmpId().equals(Long.parseLong(data))) {
										getID = employee.getEmpId();
										new GetTableFrame();
										System.out.println("for循环中的getID:" + getID);
										break;
									} else {
										System.out.println("数据data为：" + data);
										getID = Long.parseLong(data);
										break;
									}
								}
								new GetTableFrame();
								System.out.println("getID:" + getID);
							}
						}

					});
				}
			}
		});
		queryEmp.setBounds(721, 23, 113, 27);
		functionPanel.add(queryEmp);

		// TODO 按名字模糊查询会员
		JButton findbtn = new JButton("查询");
		findbtn.addActionListener(new ActionListener() {

			private List<Member> memList;

			public void actionPerformed(ActionEvent e) {

				// String name = null;

				MemberServiceImpl memSimp = new MemberServiceImpl();

				List<String> conditions = new ArrayList<String>();
				conditions.add("mem_name like '%" + findmemnametxt.getText() + "%'");
				memList = new ArrayList<Member>();
				memList = memSimp.findMem(conditions);
				System.out.println(memList);

				if (memList.size() == 0) {

					JOptionPane.showMessageDialog(contentPane, "找不到相关信息！", "提示", JOptionPane.INFORMATION_MESSAGE);

					findnotmem.setVisible(true);// 设置可见

					findmemnametxt.setText("");
				} else {

					if (findmemnametxt.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "无查询信息，默认查询全部信息！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						JOptionPane.showMessageDialog(contentPane, "查询成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

						JTable memDataTable = new JTable();
						SetTableCenter.setTableCenter(memDataTable);
						memmodel = new MyMemTableModel(memList);
						memDataTable.setModel(memmodel);
						JScrollPane memscrollPane = new JScrollPane(memDataTable);
						memscrollPane.setBounds(0, 0, 643, 424);
						memdatapanel.add(memscrollPane);

						// TODO memDataTable 表鼠标监听事件
						memDataTable.addMouseListener(new MouseAdapter() {

							public void mouseClicked(MouseEvent evt) {

								if (evt.getClickCount() == 2) {
									cardmeminfo.show(infopanel, "delandupd");
									int row = memDataTable.getSelectedRow();
									String data;
									data = String.valueOf(memmodel.getValueAt(row, 0));

									for (Member member : memList) {

										if (member.getMemId().equals(Long.parseLong(data))) {
											getmemID = member.getMemId();
											break;
										} else {
											continue;
										}

									}

									meminfoPanel();

								}

							}
						});
					}
				}

			}
		});
		findbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findbtn.setBounds(358, 44, 83, 51);
		findadd.add(findbtn);

		// 按名字查询商品
		JButton findgoodsbtn = new JButton("查询");
		findgoodsbtn.addActionListener(new ActionListener() {
			private List<Goods> goodsList;

			public void actionPerformed(ActionEvent e) {

				// TODO 查询商品

				// String name = null;

				GoodsServiceImpl goodsSimp = new GoodsServiceImpl();

				List<String> conditions = new ArrayList<String>();
				conditions.add("goods_name like '%" + findgoodstxt.getText() + "%'");
				goodsList = new ArrayList<Goods>();
				goodsList = goodsSimp.findGoods(conditions);

				if (goodsList.size() == 0) {

					// TODO 没有找到这个商品，是否要添加！
					// System.out.println("在这里弹出一个对话框，提示是否添加这个商品！");
					int result = JOptionPane.showConfirmDialog(contentPane, "没有找到这个商品，是否要添加？", "提示",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == 0) {
						cardGoods.show(goodsinfopanel, "addgoodspanel");
					}

					findgoodstxt.setText("");
				} else {

					if (findgoodstxt.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "无查询信息，默认查询全部信息！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						JOptionPane.showMessageDialog(contentPane, "查询成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

						JTable goodsDataTable = new JTable();
						SetTableCenter.setTableCenter(goodsDataTable);// 设置表格中内容居中
						goodsmodel = new MyGoodsTableModel(goodsList);
						goodsDataTable.setModel(goodsmodel);

						JScrollPane goodsscrollPane = new JScrollPane(goodsDataTable);
						goodsscrollPane.setBounds(0, 0, 655, 426);
						goodsdatatablepanel.add(goodsscrollPane);

						// TODO memDataTable 表鼠标监听事件
						goodsDataTable.addMouseListener(new MouseAdapter() {

							public void mouseClicked(MouseEvent evt) {

								if (evt.getClickCount() == 2) {

									cardGoods.show(goodsinfopanel, "delandupdgoodspanel");

									int row = goodsDataTable.getSelectedRow();
									String data;
									data = String.valueOf(goodsmodel.getValueAt(row, 0));

									for (Goods goods : goodsList) {

										if (goods.getGoodsId().equals(Long.parseLong(data))) {
											getgoodsID = goods.getGoodsId();
											System.out.println("getgoodsID:" + getgoodsID);
											break;
										} else {
											continue;
										}

									}

									goodsinfoPanel();
								}

							}

						});
					}
				}

			}

		});
		findgoodsbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findgoodsbtn.setBounds(373, 54, 73, 38);
		goodstoppanel.add(findgoodsbtn);

		/**
		 * 
		 * TODO 会员查询数据表
		 * 
		 */
		try {
			Member mem = new Member();
			MemberServiceImpl memberSimp = new MemberServiceImpl();

			List<Member> memList = memberSimp.findMem(mem);

			memDataTable = new JTable();
			SetTableCenter.setTableCenter(memDataTable);
			memmodel = new MyMemTableModel(memList);
			memDataTable.setModel(memmodel);
			JScrollPane memscrollPane = new JScrollPane(memDataTable);
			memscrollPane.setBounds(0, 0, 643, 424);
			memdatapanel.add(memscrollPane);

			// TODO memDataTable 表鼠标监听事件
			memDataTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {

					if (evt.getClickCount() == 2) {
						cardmeminfo.show(infopanel, "delandupd");
						int row = memDataTable.getSelectedRow();
						String data;
						data = String.valueOf(memmodel.getValueAt(row, 0));

						for (Member member : memList) {

							if (member.getMemId().equals(Long.parseLong(data))) {
								getmemID = member.getMemId();
								System.out.println("getmemID:" + getmemID);
								break;
							} else {
								continue;
							}

						}

						meminfoPanel();
					}

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 
		 * TODO 查询全部的商品，把数据显示在表格中显示出来
		 * 
		 * 
		 */
		try {

			Goods goods = new Goods();
			GoodsServiceImpl goodsSimp = new GoodsServiceImpl();

			List<Goods> goodsList = goodsSimp.findGoods(goods);

			goodsDataTable = new JTable();
			SetTableCenter.setTableCenter(goodsDataTable);// 设置表格中内容居中
			goodsmodel = new MyGoodsTableModel(goodsList);
			goodsDataTable.setModel(goodsmodel);

			JScrollPane goodsscrollPane = new JScrollPane(goodsDataTable);
			goodsscrollPane.setBounds(0, 0, 655, 426);
			goodsdatatablepanel.add(goodsscrollPane);

			// TODO goodsDataTable 表鼠标监听事件
			goodsDataTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {

					if (evt.getClickCount() == 2) {

						cardGoods.show(goodsinfopanel, "delandupdgoodspanel");

						int row = goodsDataTable.getSelectedRow();
						String data;
						data = String.valueOf(goodsmodel.getValueAt(row, 0));

						for (Goods goods : goodsList) {

							if (goods.getGoodsId().equals(Long.parseLong(data))) {
								getgoodsID = goods.getGoodsId();
								System.out.println("getgoodsID:" + getgoodsID);
								break;
							} else {
								continue;
							}

						}

						goodsinfoPanel();
					}

				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 实现查询员工的功能
		 */
		// TODO 查询全部的员工 把数据在表格中显示出来
		try {
			Employee employee = new Employee();
			EmployeeServiceImpl empSimp = new EmployeeServiceImpl();

			List<Employee> empList = empSimp.findEmployee(employee);

			dataTable = new JTable();
			SetTableCenter.setTableCenter(dataTable);// 设置表格中内容居中
			model = new MyEmpTableModel(empList);
			dataTable.setModel(model);
			JScrollPane dataScrollPane = new JScrollPane(dataTable);
			// dataScrollPane.setOpaque(false);
			// dataScrollPane.getViewport().setOpaque(false);//JScrollPane 透明
			dataScrollPane.setBounds(0, 0, 845, 470);
			dataPanel.setBackground(Color.RED);
			dataPanel.add(dataScrollPane);

			// TODO JTable的鼠标监听事件
			dataTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {

					if (evt.getClickCount() == 2) {
						int row = dataTable.getSelectedRow();

						String data;
						data = String.valueOf(model.getValueAt(row, 0));

						for (Employee employee : empList) {
							// System.out.println(employee.getEmpId());
							if (employee.getEmpId().equals(Long.parseLong(data))) {
								getID = employee.getEmpId();
								new GetTableFrame();
								break;
							} else {
								continue;
							}
						}
					}
				}
			});
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BGJPanel rightJPanel = new BGJPanel();
		rightJPanel.empRigthBG();
		rightJPanel.setBounds(859, 91, 141, 470);
		empJpanel.add(rightJPanel);
		rightJPanel.setLayout(null);

		JButton addEmp = new JButton("添加员工");
		addEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEmpFrame();
			}
		});
		addEmp.setBounds(14, 72, 113, 27);
		rightJPanel.add(addEmp);

		JButton removeEmp = new JButton("删除员工");
		removeEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new DelEmpFrame();

			}
		});
		removeEmp.setBounds(14, 171, 113, 27);
		rightJPanel.add(removeEmp);

		JButton updateEmp = new JButton("更改信息");
		updateEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdEmpFrame();
			}
		});
		updateEmp.setBounds(14, 270, 113, 27);
		rightJPanel.add(updateEmp);

		// TODO 刷新等于重新查询一次全部人数
		refreshbtn = new JButton("刷   新");
		refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		refreshbtn.setBounds(14, 369, 113, 27);
		rightJPanel.add(refreshbtn);

		BGJPanel busJpanel = new BGJPanel();
		busJpanel.busJpanelBG();
		busJpanel.setOpaque(false);
		mainPanel.add(busJpanel, "bus");
		busJpanel.setLayout(null);

		BGJPanel busdatapaneltoppanel = new BGJPanel();
		busdatapaneltoppanel.setBounds(0, 0, 1000, 83);
		busdatapaneltoppanel.busOrderBG();
		busJpanel.add(busdatapaneltoppanel);
		busdatapaneltoppanel.setLayout(null);

		// TODO 订单
		JPanel orderdatapanel = new JPanel();
		orderdatapanel.setBounds(0, 84, 1000, 413);
		orderdatapanel.setOpaque(false);
		busJpanel.add(orderdatapanel);
		CardLayout cardorder = new CardLayout(0, 0);
		orderdatapanel.setLayout(cardorder);

		// TODO 订单信息管理
		JPanel allorderpanel = new JPanel();
		// allorderpanel.setBackground(Color.PINK);
		// allorderpanel.setForeground(Color.LIGHT_GRAY);
		orderdatapanel.add(allorderpanel, "allorderpanel");
		allorderpanel.setLayout(null);

		JPanel historderpanel = new JPanel();
		historderpanel.setBackground(Color.LIGHT_GRAY);
		orderdatapanel.add(historderpanel, "historderpanel");
		historderpanel.setLayout(null);

		// showallmoneylabel.setText(String.valueOf(allMoney));
		/**
		 * 
		 * TODO 将全部订单的数据显示在表格上
		 * 
		 */
		try {

			Order order = new Order();
			OrderServiceImpl orderSimpl = new OrderServiceImpl();
			List<Order> orderList = orderSimpl.findOrder(order);
			allOrderDataTable = new JTable();
			SetTableCenter.setTableCenter(allOrderDataTable);// 居中
			ordermodel = new MyOrderTableModel(orderList);
			allOrderDataTable.setModel(ordermodel);

			JScrollPane allorderdatascollPanel = new JScrollPane(allOrderDataTable);
			allorderdatascollPanel.setBounds(0, 0, 1000, 413);
			allorderdatascollPanel.setOpaque(false);
			allorderdatascollPanel.getViewport().setOpaque(false);// JScrollPane
																	// 透明
			allorderpanel.add(allorderdatascollPanel);

			// 设置点击事件
			allOrderDataTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() == 2) {

						int row = allOrderDataTable.getSelectedRow();

						String data;
						data = String.valueOf(ordermodel.getValueAt(row, 0));
						eorderDataID = Long.parseLong(data);
						new EOrderFrame();
					}
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 
		 * TODO 将历史订单显示在表格上
		 * 
		 */
		// double allMoney = 0;
		try {

			// Order order = new Order();
			OrderServiceImpl orderSimpl = new OrderServiceImpl();
			String ordStatus = "1";// 查询订单的状态是已结账
			List<String> conditions = new ArrayList<String>();
			conditions.add("order_status = '" + ordStatus + "'");
			List<Order> list = orderSimpl.findOrder(conditions);

			for (Order order : list) {
				allMoney += order.getOrdAllamtall();
			}

			histOrderDataTable = new JTable();
			SetTableCenter.setTableCenter(histOrderDataTable);// 居中
			ordermodel = new MyOrderTableModel(list);
			histOrderDataTable.setModel(ordermodel);

			JScrollPane historderdatatscollPanel = new JScrollPane(histOrderDataTable);
			historderdatatscollPanel.setBounds(0, 0, 1000, 413);
			historderpanel.add(historderdatatscollPanel);

			// 设置点击事件
			histOrderDataTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() == 2) {

						int row = histOrderDataTable.getSelectedRow();

						String data;
						data = String.valueOf(ordermodel.getValueAt(row, 0));
						eorderDataID = Long.parseLong(data);
						new EOrderFrame();
					}
				}

			});
		} catch (Exception ee) {
			ee.printStackTrace();
		}


		JButton findallorderbtn = new JButton("查看全部订单");
		findallorderbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cardorder.show(orderdatapanel, "allorderpanel");
				allOrderData();
			}
		});
		findallorderbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findallorderbtn.setBounds(137, 18, 150, 46);
		busdatapaneltoppanel.add(findallorderbtn);

		JLabel showallmoneylabel = new JLabel(String.valueOf(allMoney));
		showallmoneylabel.setForeground(Color.WHITE);
		showallmoneylabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		showallmoneylabel.setBounds(701, 515, 162, 43);
		busJpanel.add(showallmoneylabel);

		JButton findolddorderbtn = new JButton("查看历史订单");
		findolddorderbtn.addActionListener(new ActionListener() {
			// double allMoney = 0;

			public void actionPerformed(ActionEvent e) {

				cardorder.show(orderdatapanel, "historderpanel");

				histOrderData();

				showallmoneylabel.setText(String.valueOf(allMoney + AddGoodsOrCheckout.money));
			}
		});
		findolddorderbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findolddorderbtn.setBounds(424, 18, 150, 46);
		busdatapaneltoppanel.add(findolddorderbtn);

		// TODO 将数据导出
		JButton exportorderbtn = new JButton("导出订单");
		exportorderbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// File file = new File("C:\\Users\\46512\\Downloads");
				// try {
				// exportTable(histOrderDataTable, file);
				// } catch (IOException ee) {
				// // TODO Auto-generated catch block
				// ee.printStackTrace();
				// }  
				JFrame frame = new JFrame();
				FileDialog fd = new FileDialog(frame, "保存记录", FileDialog.SAVE);
				fd.setLocation(400, 250);
				fd.setVisible(true);
				String stringfile = fd.getDirectory() + fd.getFile() + ".xls";
				
				new JTableToExcel().export(new File(stringfile), "海文KTV营业清单", "", histOrderDataTable);
			}
		});
		exportorderbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		exportorderbtn.setBounds(711, 18, 150, 46);
		busdatapaneltoppanel.add(exportorderbtn);

		JLabel busallmonelabel = new JLabel("营业总额：");
		busallmonelabel.setForeground(Color.WHITE);
		busallmonelabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		busallmonelabel.setBounds(596, 515, 100, 43);
		busJpanel.add(busallmonelabel);

		JLabel yuanlabel = new JLabel("元");
		yuanlabel.setForeground(Color.WHITE);
		yuanlabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		yuanlabel.setBounds(872, 515, 28, 43);
		busJpanel.add(yuanlabel);

		JLabel label = new JLabel("请输入要查询的月份：");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(52, 510, 200, 43);
		busJpanel.add(label);

		checkmonthtxt = new JTextField();
		checkmonthtxt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		checkmonthtxt.setBounds(249, 510, 154, 48);
		busJpanel.add(checkmonthtxt);
		checkmonthtxt.setColumns(10);

		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cardorder.show(orderdatapanel, "historderpanel");

				String time = checkmonthtxt.getText();
				if (time.length() < 6) {
					// -----------------------------------------------------------------------查询
					JOptionPane.showMessageDialog(contentPane, "请输入正确的格式！如查询2017年9月份的营业额：201709", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					double allMoney = 0;
					String yrea = time.substring(0, 4);
					String month = time.substring(4, 6);

					time = yrea + "-" + month;

					OrderServiceImpl orderSimpl = new OrderServiceImpl();

					List<String> conditions = new ArrayList<String>();
					conditions.add("order_enddate like '%" + time + "%'");
					List<Order> list = orderSimpl.findOrder(conditions);

					for (Order order : list) {
						allMoney += order.getOrdAllamtall();
					}

					showallmoneylabel.setText(String.valueOf(allMoney));

					if (list.size() == 0) {
						JOptionPane.showMessageDialog(contentPane, "该月无营业记录！", "提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contentPane, "该月有" + list.size() + "条营业记录！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
					}

					// ordermodel.updateList(list);
					// histOrderDataTable.updateUI();

					// -------------------------------------------------------------------------------------------历史订单

					JTable histOrderDataTable = new JTable();
					SetTableCenter.setTableCenter(histOrderDataTable);// 居中
					MyOrderTableModel ordermodel = new MyOrderTableModel(list);
					histOrderDataTable.setModel(ordermodel);

					JScrollPane historderdatatscollPanel = new JScrollPane(histOrderDataTable);
					historderdatatscollPanel.setBounds(0, 0, 1000, 413);
					historderpanel.add(historderdatatscollPanel);

					// 设置点击事件
					histOrderDataTable.addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent evt) {
							if (evt.getClickCount() == 2) {

								int row = histOrderDataTable.getSelectedRow();

								String data;
								data = String.valueOf(ordermodel.getValueAt(row, 0));
								eorderDataID = Long.parseLong(data);
								new EOrderFrame();
							}
						}

					});

				}

			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnNewButton.setBounds(412, 510, 72, 48);
		busJpanel.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("*格式：201709");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(52, 545, 118, 18);
		busJpanel.add(lblNewLabel_1);

		JButton fristbtn = new JButton("首页");
		BtnEvent.btnFrist(fristbtn);
		fristbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardmain.show(mainPanel, "frist");
			}
		});
		fristbtn.setBounds(14, 108, 260, 52);
		BorderHide.setBtnBorderHide(fristbtn);
		menuPanel.add(fristbtn);

		JButton roomMage = new JButton("包房管理");
		BtnEvent.btnRoom(roomMage);
		roomMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cardmain.show(mainPanel, "room");

				// 在房间管理的面板显示商品清单 但不可以点击
				Goods goods = new Goods();
				GoodsServiceImpl goodsSimp = new GoodsServiceImpl();
				try {

					List<Goods> goodsList = goodsSimp.findGoods(goods);

					goodsRoomDataTable = new JTable();
					SetTableCenter.setTableCenter(goodsRoomDataTable);// 设置表格中内容居中
					goodsrmodel = new MyGoodsOnRoomTableModel(goodsList);
					goodsRoomDataTable.setModel(goodsrmodel);

					JScrollPane scrollPane = new JScrollPane(goodsRoomDataTable);
					scrollPane.setBounds(0, 0, 371, 460);
					roomgoodsdatapanel.add(scrollPane);

				} catch (Exception ee) {
					ee.printStackTrace();
				}

			}
		});
		roomMage.setBounds(14, 268, 260, 52);
		BorderHide.setBtnBorderHide(roomMage);
		menuPanel.add(roomMage);

		JButton memMage = new JButton("会员管理");
		BtnEvent.btnMem(memMage);
		memMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardmain.show(mainPanel, "mem");
				cardmeminfo.show(infopanel, "infoshow");
			}
		});
		memMage.setBounds(14, 428, 260, 52);
		BorderHide.setBtnBorderHide(memMage);
		menuPanel.add(memMage);

		JLabel txtTime = new JLabel("北京时间：");
		txtTime.setForeground(Color.WHITE);
		txtTime.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		txtTime.setBounds(473, 151, 90, 38);
		contentPane.add(txtTime);

		getTime = new JLabel("");
		getTime.setForeground(Color.WHITE);
		getTime.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getTime.setBounds(565, 151, 289, 38);
		contentPane.add(getTime);

		JLabel nowUser = new JLabel("当前用户：");
		nowUser.setForeground(Color.WHITE);
		nowUser.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		nowUser.setBounds(48, 151, 102, 38);
		contentPane.add(nowUser);

		JLabel userName = new JLabel("");
		userName.setForeground(Color.WHITE);
		userName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		userName.setBounds(152, 151, 102, 38);
		userName.setText(EmployeeServiceImpl.getName());
		contentPane.add(userName);

		JLabel post = new JLabel("职位：");
		post.setForeground(Color.WHITE);
		post.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		post.setBounds(282, 151, 54, 38);
		contentPane.add(post);

		JLabel postlvl = new JLabel("");
		postlvl.setForeground(Color.WHITE);
		postlvl.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		postlvl.setBounds(340, 151, 54, 38);
		postlvl.setText(EmployeeServiceImpl.getPost());
		contentPane.add(postlvl);

	}

	// 刷新历史订单
	public void histOrderData() {
		OrderServiceImpl orderSimpl = new OrderServiceImpl();
		String ordStatus = "1";// 查询订单的状态是已结账
		List<String> conditions = new ArrayList<String>();
		conditions.add("order_status = '" + ordStatus + "'");
		List<Order> list = orderSimpl.findOrder(conditions);
		ordermodel.updateList(list);
		histOrderDataTable.updateUI();

	}

	// 刷新全部订单
	static public void allOrderData() {
		Order order = new Order();
		OrderServiceImpl orderSimpl = new OrderServiceImpl();
		try {
			List<Order> orderList = orderSimpl.findOrder(order);
			ordermodel.updateList(orderList);
			allOrderDataTable.updateUI();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * TODO 将商品信息显示在面板上
	 * 
	 */
	static public void goodsinfoPanel() {
		/**
		 * 
		 * TODO 获取商品表格的ID，然后查询将数据放在面板上 getgoodsID
		 */
		try {
			// 将数据显示在面板上
			GoodsServiceImpl goodsSimp = new GoodsServiceImpl();
			Goods goods = new Goods();
			goods = goodsSimp.findGoods(getgoodsID);

			goodsidlabel.setText(Long.toString(goods.getGoodsId()));
			goodsnametxt.setText(goods.getGoodsName());
			goodspricetxt.setText(Double.toString(goods.getGoodsPrice()));
			goodscounttxt.setText(Integer.toString(goods.getGoodsCount()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * TODO 将会员信息显示在面板上
	 * 
	 * 
	 */
	static public void meminfoPanel() {
		/**
		 * 
		 * TODO 获取会员表格的ID，然后查询将数据放在面板上 getmemID
		 */
		try {
			// 将数据显示在面板上
			MemberServiceImpl memSimp = new MemberServiceImpl();
			Member member = new Member();
			member = memSimp.findMem(getmemID);
			if (member.getMemGender().equals("男")) {
				radmemman.setSelected(true);
			} else {
				radmemwom.setSelected(true);
			}
			memIDlabel.setText(Long.toString(member.getMemId()));
			memnametxt.setText(member.getMemName());
			memagetxt.setText(Integer.toString(member.getMemAge()));
			memphonetxt.setText(member.getMemPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * TODO 刷新在房间里的商品信息
	 *
	 */
	static public void roomGoodsFresh() {
		Goods goods = new Goods();
		GoodsServiceImpl goodsSimp = new GoodsServiceImpl();
		try {

			List<Goods> goodsList = goodsSimp.findGoods(goods);

			goodsrmodel.updateList(goodsList);
			goodsRoomDataTable.updateUI();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * TODO 刷新商品信息
	 * 
	 */
	static public void goodsRefresh() {

		findgoodstxt.setText("");

		Goods goods = new Goods();
		GoodsServiceImpl goodsSipm = new GoodsServiceImpl();
		try {
			List<Goods> goodsList = goodsSipm.findGoods(goods);
			goodsmodel.updateList(goodsList);
			goodsDataTable.updateUI();

			goodsDataTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {

					if (evt.getClickCount() == 2) {

						int row = goodsDataTable.getSelectedRow();
						String data;
						data = String.valueOf(goodsmodel.getValueAt(row, 0));

						for (Goods goods : goodsList) {

							if (goods.getGoodsId().equals(Long.parseLong(data))) {
								getgoodsID = goods.getGoodsId();
								// System.out.println("getgoodsID:" +
								// getgoodsID);
								break;
							} else {
								continue;
							}

						}
					}

				}

			});

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * TODO 刷新会员信息
	 * 
	 */
	static public void memRefresh() {

		findmemnametxt.setText("");
		findnotmem.setVisible(false);// 设置不可见
		Member member = new Member();
		MemberServiceImpl memSipm = new MemberServiceImpl();
		try {
			List<Member> memList = memSipm.findMem(member);
			memmodel.updateList(memList);
			memDataTable.updateUI();

			memDataTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {

					if (evt.getClickCount() == 2) {

						int row = memDataTable.getSelectedRow();
						String data;
						data = String.valueOf(memmodel.getValueAt(row, 0));

						for (Member member : memList) {

							if (member.getMemId().equals(Long.parseLong(data))) {
								getmemID = member.getMemId();
								// System.out.println("getmemID:" + getmemID);
								break;
							} else {
								continue;
							}

						}
					}

				}

			});

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * TODO 刷新数据
	 * 
	 */
	static public void refresh() {
		try {
			querytxtName.setText("");
			querytxtNum.setText("");
			dataTable.setVisible(true);
			Employee employee = new Employee();
			EmployeeServiceImpl empSimp = new EmployeeServiceImpl();
			List<Employee> empList = empSimp.findEmployee(employee);
			model.updateList(empList);
			dataTable.updateUI();
			dataPanel.setBackground(Color.RED);

			// TODO JTable的鼠标监听事件
			dataTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {

					if (evt.getClickCount() == 2) {
						int row = dataTable.getSelectedRow();

						String data;
						data = String.valueOf(model.getValueAt(row, 0));

						for (Employee employee : empList) {
							System.out.println(employee.getEmpId());
							if (employee.getEmpId().equals(Long.parseLong(data))) {
								getID = employee.getEmpId();
								new GetTableFrame();
								break;
							} else {
								continue;
							}
						}
					}
				}
			});

		} catch (ServiceException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO 用线程获取当前的时间
		while (true) {
			Date date = new Date();
			DateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
			getTime.setText(dateformat.format(date));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// TODO 刷新房间管理标签
	static public void refRoomLabel(Long id, JLabel jLabel) {
		RoomServiceImpl rsi = new RoomServiceImpl();
		Room room = new Room();
		try {
			room = rsi.findRoom(id);

			jLabel.setText(room.getRoomStatus());
			if (jLabel.getText().equals("使用中")) {
				jLabel.setForeground(Color.RED);
			}
			if (jLabel.getText().equals("已预定")) {
				jLabel.setForeground(Color.BLUE);
			}
			if (jLabel.getText().equals("未使用")) {
				jLabel.setForeground(Color.BLACK);
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void exportTable(JTable table, File file) throws IOException {
		try {
			OutputStream out = new FileOutputStream(file);
			TableModel model = table.getModel();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			// 创建字表，并写入数据
			WritableSheet ws = wwb.createSheet("中文", 0);
			// 添加标题
			for (int i = 0; i < model.getColumnCount(); i++) {
				jxl.write.Label labelN = new jxl.write.Label(i, 0, model.getColumnName(i));
				try {
					ws.addCell(labelN);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 添加列
			for (int i = 0; i < model.getColumnCount(); i++) {
				for (int j = 1; j <= model.getRowCount(); j++) {
					jxl.write.Label labelN = new jxl.write.Label(i, j, model.getValueAt(j - 1, i).toString());
					try {
						ws.addCell(labelN);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
				}
			}
			wwb.write();
			try {
				wwb.close();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "导入数据前请关闭工作表");
		}
	}
}
