package com.lhh.ktv.view;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;

import javax.swing.JRadioButton;

public class GetTableFrame {

	static private JFrame frame;
	private JTextField nametxt;
	private JTextField agetxt;
	private JTextField phonetxt;
	private JTextField usertxt;
	private JTextField passtxt;
	
	
	static private String getUpdName;
	static private String getUpdGender;
	static private byte getUpdAge;
	static private String getUpdPhone;
	static private String getUpdPost;
	static private String getUpdUsername;
	static private String getUpdPassword;
	static private Long getUpdId;
	
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GetTableFrame window = new GetTableFrame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public static String getGetUpdName() {
		return getUpdName;
	}

	public static void setGetUpdName(String getUpdName) {
		GetTableFrame.getUpdName = getUpdName;
	}

	public static String getGetUpdGender() {
		return getUpdGender;
	}

	public static void setGetUpdGender(String getUpdGender) {
		GetTableFrame.getUpdGender = getUpdGender;
	}

	public static byte getGetUpdAge() {
		return getUpdAge;
	}

	public static void setGetUpdAge(byte getUpdAge) {
		GetTableFrame.getUpdAge = getUpdAge;
	}

	public static String getGetUpdPhone() {
		return getUpdPhone;
	}

	public static void setGetUpdPhone(String getUpdPhone) {
		GetTableFrame.getUpdPhone = getUpdPhone;
	}

	public static String getGetUpdPost() {
		return getUpdPost;
	}

	public static void setGetUpdPost(String getUpdPost) {
		GetTableFrame.getUpdPost = getUpdPost;
	}

	public static String getGetUpdUsername() {
		return getUpdUsername;
	}

	public static void setGetUpdUsername(String getUpdUsername) {
		GetTableFrame.getUpdUsername = getUpdUsername;
	}

	public static String getGetUpdPassword() {
		return getUpdPassword;
	}

	public static void setGetUpdPassword(String getUpdPassword) {
		GetTableFrame.getUpdPassword = getUpdPassword;
	}

	public static Long getGetUpdId() {
		return getUpdId;
	}

	public static void setGetUpdId(Long getUpdId) {
		GetTableFrame.getUpdId = getUpdId;
	}

	/**
	 * Create the application.
	 */
	public GetTableFrame() {
		initialize();
	}
	
//	public GetTableFrame(Long id) {
//		initialize();
//	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 459, 650);
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
		closebtn.setBounds(429, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);
		
		JLabel prompt1 = new JLabel("编号为");
		prompt1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		prompt1.setBounds(66, 45, 62, 47);
		frame.getContentPane().add(prompt1);
		
		JLabel idLabel = new JLabel("");
		idLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		idLabel.setBounds(142, 45, 52, 47);
		frame.getContentPane().add(idLabel);
		
		JLabel prompt2 = new JLabel("的员工信息如下：");
		prompt2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		prompt2.setBounds(208, 45, 168, 47);
		frame.getContentPane().add(prompt2);
		
		JLabel prompt3 = new JLabel("*可以直接进行删除和修改信息");
		prompt3.setForeground(Color.RED);
		prompt3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		prompt3.setBounds(66, 93, 221, 18);
		frame.getContentPane().add(prompt3);
		
		JLabel nameLabel = new JLabel("员工姓名：");
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		nameLabel.setBounds(56, 140, 95, 40);
		frame.getContentPane().add(nameLabel);
		
		JLabel genderLabel = new JLabel("员工性别：");
		genderLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		genderLabel.setBounds(56, 190, 95, 40);
		frame.getContentPane().add(genderLabel);
		
		JLabel ageLabel = new JLabel("员工年龄：");
		ageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		ageLabel.setBounds(56, 240, 95, 40);
		frame.getContentPane().add(ageLabel);
		
		JLabel phoneLabel = new JLabel("联系方式：");
		phoneLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		phoneLabel.setBounds(56, 290, 95, 40);
		frame.getContentPane().add(phoneLabel);
		
		JLabel postLabel = new JLabel("员工职位：");
		postLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		postLabel.setBounds(56, 340, 95, 40);
		frame.getContentPane().add(postLabel);
		
		JLabel userLabel = new JLabel("员工账号：");
		userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		userLabel.setBounds(56, 390, 95, 40);
		frame.getContentPane().add(userLabel);
		
		JLabel passLabel = new JLabel("账号密码：");
		passLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		passLabel.setBounds(56, 440, 95, 40);
		frame.getContentPane().add(passLabel);
		
		JButton delbtn = new JButton("删除");
		delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO 删除
				new ConfirmDelFrame();
				
			}
		});
		delbtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		delbtn.setBounds(66, 509, 83, 47);
		frame.getContentPane().add(delbtn);
		
		JButton cancelbtn = new JButton("取消");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		cancelbtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		cancelbtn.setBounds(303, 509, 83, 47);
		frame.getContentPane().add(cancelbtn);
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nametxt.setBounds(142, 140, 204, 40);
		frame.getContentPane().add(nametxt);
		nametxt.setColumns(10);
		
		agetxt = new JTextField();
		agetxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		agetxt.setColumns(10);
		agetxt.setBounds(142, 240, 204, 40);
		frame.getContentPane().add(agetxt);
		
		phonetxt = new JTextField();
		phonetxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		phonetxt.setColumns(10);
		phonetxt.setBounds(142, 290, 204, 40);
		frame.getContentPane().add(phonetxt);
		
		usertxt = new JTextField();
		usertxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		usertxt.setColumns(10);
		usertxt.setBounds(142, 390, 204, 40);
		frame.getContentPane().add(usertxt);
		
		passtxt = new JTextField();
		passtxt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		passtxt.setColumns(10);
		passtxt.setBounds(142, 440, 204, 40);
		frame.getContentPane().add(passtxt);
		
		JRadioButton radMan = new JRadioButton("男");
		radMan.setSelected(true);
		radMan.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radMan.setBounds(142, 196, 52, 33);
		frame.getContentPane().add(radMan);
		
		JRadioButton radWom = new JRadioButton("女");
		radWom.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radWom.setBounds(193, 196, 52, 33);
		frame.getContentPane().add(radWom);
		
		ButtonGroup groupGender = new ButtonGroup();
		groupGender.add(radMan);
		groupGender.add(radWom);
		
		JRadioButton radEmp = new JRadioButton("前台");
		radEmp.setSelected(true);
		radEmp.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radEmp.setBounds(142, 344, 71, 33);
		frame.getContentPane().add(radEmp);
		
		JRadioButton radMage = new JRadioButton("经理");
		radMage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radMage.setBounds(216, 344, 71, 33);
		frame.getContentPane().add(radMage);
		
		ButtonGroup groupEmp = new ButtonGroup();
		groupEmp.add(radEmp);
		groupEmp.add(radMage);
		
		
		// TODO 更新数据
		JButton updbtn = new JButton("更改");
		updbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取信息
				getUpdId = Long.parseLong(idLabel.getText());
				getUpdName = nametxt.getText();
				if(radMan.isSelected()){
					getUpdGender = "男";
				}else{
					getUpdGender = "女";
				}
				
				getUpdAge = (byte)Integer.parseInt(agetxt.getText());
				getUpdPhone = phonetxt.getText();
				if(radMage.isSelected()){
					getUpdPost = "0";
				}else{
					getUpdPost = "1";
				}
				getUpdUsername = usertxt.getText();
				getUpdPassword = passtxt.getText();
				
				new UpdTableInfoFrame();
				
			}
		});
		updbtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		updbtn.setBounds(186, 509, 83, 47);
		frame.getContentPane().add(updbtn);
		
		
		// TODO 把数据显示在面板上
		EmployeeServiceImpl emp = new EmployeeServiceImpl();
		Employee employee = new Employee();
		
		try {
			employee = emp.findEmployee(MainFrame.getGetID());
			
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
			
			idLabel.setText(Long.toString(employee.getEmpId()));
			nametxt.setText(employee.getEmpName());
			agetxt.setText(Integer.toString(employee.getEmpAge()));
			phonetxt.setText(employee.getEmpPhone());
			usertxt.setText(employee.getUsername());
			passtxt.setText(employee.getPassword());
			
			System.out.println(employee);
			
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	//关闭这个界面
	static public void closeGetFrame(){
		frame.setVisible(false);
		frame.dispose();
	}
}
