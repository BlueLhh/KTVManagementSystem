package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdEmpFrame {

	static private JFrame frame;
	private JTextField InputIDtxt;
	private JTextField getName;
	private JTextField getPhone;
	private JTextField getAge;
	private JTextField getUser;
	private JTextField getPass;

	static private String getUpdName;
	static private String getUpdGender;
	static private byte getUpdAge;
	static private String getUpdPhone;
	static private String getUpdPost;
	static private String getUpdUsername;
	static private String getUpdPassword;
	static private Long getUpdId;

	public static String getGetUpdName() {
		return getUpdName;
	}

	public static void setGetUpdName(String getUpdName) {
		UpdEmpFrame.getUpdName = getUpdName;
	}

	public static String getGetUpdGender() {
		return getUpdGender;
	}

	public static void setGetUpdGender(String getUpdGender) {
		UpdEmpFrame.getUpdGender = getUpdGender;
	}

	public static byte getGetUpdAge() {
		return getUpdAge;
	}

	public static void setGetUpdAge(byte getUpdAge) {
		UpdEmpFrame.getUpdAge = getUpdAge;
	}

	public static String getGetUpdPhone() {
		return getUpdPhone;
	}

	public static void setGetUpdPhone(String getUpdPhone) {
		UpdEmpFrame.getUpdPhone = getUpdPhone;
	}

	public static String getGetUpdPost() {
		return getUpdPost;
	}

	public static void setGetUpdPost(String getUpdPost) {
		UpdEmpFrame.getUpdPost = getUpdPost;
	}

	public static String getGetUpdUsername() {
		return getUpdUsername;
	}

	public static void setGetUpdUsername(String getUpdUsername) {
		UpdEmpFrame.getUpdUsername = getUpdUsername;
	}

	public static String getGetUpdPassword() {
		return getUpdPassword;
	}

	public static void setGetUpdPassword(String getUpdPassword) {
		UpdEmpFrame.getUpdPassword = getUpdPassword;
	}

	public static Long getGetUpdId() {
		return getUpdId;
	}

	public static void setGetUpdId(Long getUpdId) {
		UpdEmpFrame.getUpdId = getUpdId;
	}

	/**
	 * Create the application.
	 */
	public UpdEmpFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 762, 691);
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
		closebtn.setBounds(732, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);

		JLabel updTitelLabel = new JLabel("更改信息");
		updTitelLabel.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		updTitelLabel.setBounds(290, 13, 118, 64);
		frame.getContentPane().add(updTitelLabel);

		JLabel InputIDLabel = new JLabel("请输入要更新的ID：");
		InputIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		InputIDLabel.setBounds(97, 114, 168, 39);
		frame.getContentPane().add(InputIDLabel);

		InputIDtxt = new JTextField();
		InputIDtxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		InputIDtxt.setBounds(263, 114, 86, 39);
		frame.getContentPane().add(InputIDtxt);
		InputIDtxt.setColumns(10);

		JPanel updPanel = new JPanel();
		updPanel.setBounds(14, 189, 716, 442);
		frame.getContentPane().add(updPanel);
		updPanel.setVisible(false);// 初始化不可见
		updPanel.setLayout(null);

		JLabel promptLabel = new JLabel("*没有这个人！");
		promptLabel.setForeground(Color.RED);
		promptLabel.setBounds(97, 157, 98, 18);
		promptLabel.setVisible(false);// 初始不可见
		frame.getContentPane().add(promptLabel);

		JLabel label = new JLabel("要更新信息的员工信息如下：");
		label.setForeground(Color.BLACK);
		label.setBounds(14, 13, 200, 18);
		updPanel.add(label);

		JLabel IDLabel = new JLabel("员工编号：");
		IDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		IDLabel.setBounds(66, 63, 96, 49);
		updPanel.add(IDLabel);

		JLabel genderLabel = new JLabel("员工性别：");
		genderLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		genderLabel.setBounds(66, 138, 96, 49);
		updPanel.add(genderLabel);

		JLabel phoneLabel = new JLabel("联系方式：");
		phoneLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		phoneLabel.setBounds(66, 211, 96, 49);
		updPanel.add(phoneLabel);

		JLabel usreLabel = new JLabel("员工账号：");
		usreLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		usreLabel.setBounds(66, 284, 96, 49);
		updPanel.add(usreLabel);

		JLabel nameLabel = new JLabel("员工姓名：");
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		nameLabel.setBounds(340, 63, 96, 49);
		updPanel.add(nameLabel);

		JLabel ageLabel = new JLabel("员工年龄：");
		ageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		ageLabel.setBounds(340, 138, 96, 49);
		updPanel.add(ageLabel);

		JLabel postLabel = new JLabel("员工职位：");
		postLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		postLabel.setBounds(340, 211, 96, 49);
		updPanel.add(postLabel);

		JLabel passLabel = new JLabel("账号密码：");
		passLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		passLabel.setBounds(340, 284, 96, 49);
		updPanel.add(passLabel);

		JLabel getID = new JLabel("");
		getID.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getID.setBounds(158, 63, 96, 49);
		updPanel.add(getID);

		JButton NOUpdbtn = new JButton("放弃修改");
		NOUpdbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		NOUpdbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		NOUpdbtn.setBounds(402, 363, 113, 42);
		updPanel.add(NOUpdbtn);

		getName = new JTextField();
		getName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getName.setBounds(432, 63, 190, 49);
		updPanel.add(getName);
		getName.setColumns(10);

		getPhone = new JTextField();
		getPhone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getPhone.setColumns(10);
		getPhone.setBounds(158, 211, 168, 49);
		updPanel.add(getPhone);

		getAge = new JTextField();
		getAge.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getAge.setColumns(10);
		getAge.setBounds(432, 138, 190, 49);
		updPanel.add(getAge);

		getUser = new JTextField();
		getUser.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getUser.setColumns(10);
		getUser.setBounds(158, 284, 168, 49);
		updPanel.add(getUser);

		getPass = new JTextField();
		getPass.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getPass.setColumns(10);
		getPass.setBounds(432, 284, 190, 49);
		updPanel.add(getPass);

		JRadioButton radMage = new JRadioButton("经理");
		radMage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radMage.setBounds(503, 217, 65, 40);
		updPanel.add(radMage);

		JRadioButton radEmp = new JRadioButton("前台");
		radEmp.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radEmp.setBounds(432, 217, 65, 40);
		radEmp.setSelected(true);// 设置默认
		updPanel.add(radEmp);

		ButtonGroup gropEmp = new ButtonGroup();
		gropEmp.add(radMage);
		gropEmp.add(radEmp);

		JRadioButton radMan = new JRadioButton("男");
		radMan.setSelected(true);
		radMan.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radMan.setBounds(158, 144, 53, 40);
		updPanel.add(radMan);

		JRadioButton radWom = new JRadioButton("女");
		radWom.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radWom.setBounds(209, 144, 53, 40);
		updPanel.add(radWom);

		ButtonGroup gropGender = new ButtonGroup();
		gropGender.add(radMan);
		gropGender.add(radWom);

		JButton OKUpdbtn = new JButton("确认修改");
		OKUpdbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO 获取文本框的内容
				// 获取信息
				getUpdId = Long.parseLong(getID.getText());
				getUpdName = getName.getText();
				if (radMan.isSelected()) {
					getUpdGender = "男";
				} else {
					getUpdGender = "女";
				}
				getUpdAge = (byte) Integer.parseInt(getAge.getText());
				getUpdPhone = getPhone.getText();
				if (radMage.isSelected()) {
					getUpdPost = "0";
				} else {
					getUpdPost = "1";
				}
				getUpdUsername = getUser.getText();
				getUpdPassword = getPass.getText();

				new ConfirmUpdFrame();
			}
		});
		OKUpdbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		OKUpdbtn.setBounds(213, 363, 113, 42);
		updPanel.add(OKUpdbtn);

		/**
		 * 
		 * TODO 点击确定按钮，先根据ID查询这个人的信息。 先查询，后修改！
		 * 
		 */
		JButton OKbtn = new JButton("确定");
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promptLabel.setVisible(false);// 初始不可见
				updPanel.setVisible(true);// 初始化不可见
				Long id = null;

				id = Long.parseLong(InputIDtxt.getText());

				EmployeeServiceImpl emp = new EmployeeServiceImpl();
				Employee employee = new Employee();

				try {
					employee = emp.findEmployee(id);

					if (employee.getEmpGender().equals("男")) {
						radMan.setSelected(true);
					} else {
						radWom.setSelected(true);
					}

					if (employee.getEmpPost().equals("1")) {
						radEmp.setSelected(true);
					} else {
						radMage.setSelected(true);
					}

					getID.setText(Long.toString(employee.getEmpId()));
					getName.setText(employee.getEmpName());
					getAge.setText(Integer.toString(employee.getEmpAge()));
					getPhone.setText(employee.getEmpPhone());
					getUser.setText(employee.getUsername());
					getPass.setText(employee.getPassword());

					System.out.println(employee);

				} catch (Exception E) {
					// TODO Auto-generated catch block

					promptLabel.setVisible(true);

					E.printStackTrace();
				}

			}
		});
		OKbtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		OKbtn.setBounds(392, 114, 98, 39);
		frame.getContentPane().add(OKbtn);
	}

	static public void closeUpdEmp() {
		frame.setVisible(false);
		frame.dispose();
	}

}
