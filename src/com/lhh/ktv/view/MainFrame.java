package com.lhh.ktv.view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.util.BGJPanel;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

public class MainFrame extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 43473320193261407L;
	private JPanel contentPane;
	private CardLayout card;
	private JLabel getTime;
	
	//private EmployeeServiceImpl employee = new EmployeeServiceImpl();
	
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// MainFrame frame = new MainFrame();
	// BorderHide.setJFrameHide(frame);
	// new WindowMove().install(frame);// 边框隐藏之后可以移动
	// new Thread(frame).start();// 获取系统时间
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }


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

	@Override
	public void run() {
		// TODO Auto-generated method stub
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
