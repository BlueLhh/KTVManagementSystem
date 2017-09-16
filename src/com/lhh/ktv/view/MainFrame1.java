package com.lhh.ktv.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.util.BGJPanel;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.MyEmpTableModel;
import com.lhh.ktv.util.SetTableCenter;
import com.lhh.ktv.util.WindowMove;

public class MainFrame extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 43473320193261407L;
	private JPanel contentPane;
	private CardLayout card;
	private JLabel getTime;

	private static JButton refreshbtn;

	private static JTextField querytxtName;
	private static JTextField querytxtNum;
	private static JTable dataTable;
	private static MyEmpTableModel model;

	static JPanel dataPanel = new JPanel();

	// 定义静态的ID方法
	static private Long getID;

	public static Long getGetID() {
		return getID;
	}

	public static void setGetID(Long getID) {
		MainFrame.getID = getID;
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
	public MainFrame(JFrame jFrame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(false);
					dispose();
					MainFrame frame = new MainFrame();
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

	public MainFrame() {
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
		card = new CardLayout(0, 0);
		mainPanel.setLayout(card);

		BGJPanel frist = new BGJPanel();
		frist.fristBg();
		frist.setOpaque(false);
		mainPanel.add(frist, "frist");

		JPanel roomJpanel = new JPanel();
		roomJpanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(roomJpanel, "room");
		roomJpanel.setLayout(null);

		JPanel memJpanel = new JPanel();
		memJpanel.setBackground(Color.GREEN);
		mainPanel.add(memJpanel, "mem");
		memJpanel.setLayout(null);

		JPanel goodsJpanel = new JPanel();
		goodsJpanel.setBackground(Color.RED);
		mainPanel.add(goodsJpanel, "goods");
		goodsJpanel.setLayout(null);

		JPanel empJpanel = new JPanel();
		mainPanel.add(empJpanel, "emp");
		empJpanel.setLayout(null);

		JPanel functionPanel = new JPanel();
		functionPanel.setBackground(Color.LIGHT_GRAY);
		functionPanel.setBounds(0, 0, 1000, 78);
		empJpanel.add(functionPanel);
		functionPanel.setLayout(null);

		JLabel queryName = new JLabel("姓名：");
		queryName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		queryName.setBounds(101, 13, 64, 40);
		functionPanel.add(queryName);

		querytxtName = new JTextField();
		querytxtName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		querytxtName.setBounds(165, 16, 172, 40);
		functionPanel.add(querytxtName);
		querytxtName.setColumns(10);

		JLabel queryNum = new JLabel("账号：");
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
				String name = null;
				String num = null;
				EmployeeServiceImpl empSimp = new EmployeeServiceImpl();
				List<String> conditions = new ArrayList<String>();
				conditions.add("emp_name = '" + querytxtName.getText() + "'");
				conditions.add("emp_username = '" + querytxtNum.getText() + "'");
				empList = new ArrayList<Employee>();
				empList = empSimp.findEmployee(conditions);

				for (Employee employee : empList) {
					name = employee.getEmpName();
					num = employee.getUsername();
				}

				if (!(querytxtName.getText().equals(name)) || !(querytxtNum.getText().equals(num))) {
					JOptionPane.showMessageDialog(contentPane, "没有这个人！", "提示", JOptionPane.INFORMATION_MESSAGE);
					querytxtName.setText("");
					querytxtNum.setText("");
				} else {
					// TODO 查询刷新
					JOptionPane.showMessageDialog(contentPane, "查询成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(empList);

					JTable queryTable = new JTable();
					queryTable.setVisible(true);
					SetTableCenter.setTableCenter(dataTable);// 设置表格中内容居中
					MyEmpTableModel model = new MyEmpTableModel(empList);
					queryTable.setModel(model);
					JScrollPane dataScrollPane = new JScrollPane(queryTable);
					//dataScrollPane.setOpaque(false);
					//dataScrollPane.getViewport().setOpaque(false);//JScrollPane 透明
					dataScrollPane.setBounds(0, 0, 845, 470);
					dataPanel.add(dataScrollPane);

					queryTable.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							if (evt.getClickCount() == 1) {
								int row = dataTable.getSelectedRow();
								String data;
								data = String.valueOf(model.getValueAt(row, 0));

								System.out.println("data:" + data);
								for (Employee employee : empList) {
									System.out.println(employee.getEmpId());
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
			//dataScrollPane.setOpaque(false);
			//dataScrollPane.getViewport().setOpaque(false);//JScrollPane 透明
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

						System.out.println("data:" + data);

						for (Employee employee : empList) {
							System.out.println(employee.getEmpId());
							if (employee.getEmpId().equals(Long.parseLong(data))) {
								getID = employee.getEmpId();
								System.out.println("for循环中的getID:" + getID);
								new GetTableFrame();
								break;
							} else {
								System.out.println("数据data为：" + data);
								continue;
							}
						}

						System.out.println("getID:" + getID);
					}
				}
			});
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel rightJPanel = new JPanel();
		rightJPanel.setBackground(Color.LIGHT_GRAY);
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
		refreshbtn = new JButton("刷新");
		refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		refreshbtn.setBounds(34, 369, 76, 27);
		rightJPanel.add(refreshbtn);

		JPanel busJpanel = new JPanel();
		busJpanel.setBackground(Color.YELLOW);
		mainPanel.add(busJpanel, "bus");
		busJpanel.setLayout(null);

		JButton fristbtn = new JButton("首页");
		BtnEvent.btnFrist(fristbtn);
		fristbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "frist");
			}
		});
		fristbtn.setBounds(14, 25, 260, 52);
		BorderHide.setBtnBorderHide(fristbtn);
		menuPanel.add(fristbtn);

		JButton roomMage = new JButton("包房管理");
		BtnEvent.btnRoom(roomMage);
		roomMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "room");
			}
		});
		roomMage.setBounds(14, 116, 260, 52);
		BorderHide.setBtnBorderHide(roomMage);
		menuPanel.add(roomMage);

		JButton memMage = new JButton("会员管理");
		BtnEvent.btnMem(memMage);
		memMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "mem");
			}
		});
		memMage.setBounds(14, 207, 260, 52);
		BorderHide.setBtnBorderHide(memMage);
		menuPanel.add(memMage);

		JButton goodsMage = new JButton("商品管理");
		BtnEvent.btnGoods(goodsMage);
		goodsMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "goods");
			}
		});
		goodsMage.setBounds(14, 298, 260, 52);
		BorderHide.setBtnBorderHide(goodsMage);
		menuPanel.add(goodsMage);

		JButton empMage = new JButton("员工管理");
		BtnEvent.btnEmp(empMage);
		empMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "emp");
			}
		});
		empMage.setBounds(14, 389, 260, 52);
		BorderHide.setBtnBorderHide(empMage);
		menuPanel.add(empMage);

		JButton busMage = new JButton("营业查询");
		BtnEvent.btnBus(busMage);
		busMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "bus");
			}
		});
		busMage.setBounds(14, 480, 260, 52);
		BorderHide.setBtnBorderHide(busMage);
		menuPanel.add(busMage);

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
}
