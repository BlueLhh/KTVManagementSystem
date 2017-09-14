package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.IEmployeeService;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class AddEmpFrame {

	private JFrame frame;
	private JTextField addNameField;
	private JTextField addAgeField;
	private JTextField addPhoneField;
	private JTextField addUserField;
	private JTextField addPassField;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddEmpFrame window = new AddEmpFrame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public AddEmpFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 515, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);

		JLabel addEmpLabel = new JLabel("添加员工");
		addEmpLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		addEmpLabel.setBounds(192, 32, 103, 54);
		frame.getContentPane().add(addEmpLabel);

		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nameLabel.setBounds(131, 99, 62, 42);
		frame.getContentPane().add(nameLabel);

		JLabel genderLabel = new JLabel("性别：");
		genderLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		genderLabel.setBounds(131, 165, 62, 42);
		frame.getContentPane().add(genderLabel);

		JLabel ageLabel = new JLabel("年龄：");
		ageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		ageLabel.setBounds(131, 231, 62, 42);
		frame.getContentPane().add(ageLabel);

		JLabel phoneLable = new JLabel("联系方式：");
		phoneLable.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		phoneLable.setBounds(90, 297, 103, 42);
		frame.getContentPane().add(phoneLable);

		JLabel posrtLabel = new JLabel("职位：");
		posrtLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		posrtLabel.setBounds(131, 363, 62, 42);
		frame.getContentPane().add(posrtLabel);

		JLabel userLabel = new JLabel("账号：");
		userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		userLabel.setBounds(131, 429, 62, 42);
		frame.getContentPane().add(userLabel);

		JLabel passLabel = new JLabel("密码：");
		passLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		passLabel.setBounds(131, 495, 62, 42);
		frame.getContentPane().add(passLabel);

		addNameField = new JTextField();
		addNameField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		addNameField.setBounds(189, 99, 156, 42);
		frame.getContentPane().add(addNameField);
		addNameField.setColumns(10);
		
		JRadioButton radman = new JRadioButton("男");
		radman.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radman.setBounds(192, 168, 48, 40);
		radman.setSelected(true);
		frame.getContentPane().add(radman);
		
		JRadioButton radwom = new JRadioButton("女");
		radwom.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radwom.setBounds(246, 168, 48, 40);
		frame.getContentPane().add(radwom);
		
		ButtonGroup groupGender = new ButtonGroup();
		groupGender.add(radman);
		groupGender.add(radwom);
		
		JRadioButton radEmp = new JRadioButton("前台");
		radEmp.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radEmp.setBounds(192, 369, 75, 37);
		radEmp.setSelected(true);
		frame.getContentPane().add(radEmp);
		
		JRadioButton radMage = new JRadioButton("经理");
		radMage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radMage.setBounds(270, 369, 75, 37);
		frame.getContentPane().add(radMage);
		
		ButtonGroup groupRad = new ButtonGroup();
		groupRad.add(radMage);
		groupRad.add(radEmp);

		addAgeField = new JTextField();
		addAgeField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		addAgeField.setColumns(10);
		addAgeField.setBounds(189, 231, 156, 42);
		frame.getContentPane().add(addAgeField);

		addPhoneField = new JTextField();
		addPhoneField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		addPhoneField.setColumns(10);
		addPhoneField.setBounds(189, 297, 156, 42);
		frame.getContentPane().add(addPhoneField);

		addUserField = new JTextField();
		addUserField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		addUserField.setColumns(10);
		addUserField.setBounds(189, 429, 156, 42);
		frame.getContentPane().add(addUserField);

		addPassField = new JTextField();
		addPassField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		addPassField.setColumns(10);
		addPassField.setBounds(189, 495, 156, 42);
		frame.getContentPane().add(addPassField);

		/**
		 * 
		 * 点击确认添加员工
		 * 
		 */
		JButton confirmbtn = new JButton("确认");
		confirmbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		confirmbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name;
				String gender;
				byte age;
				String phone;
				String post;
				String username;
				String password;
				
				name = addNameField.getText();
				//gender = addGenderField.getText();
				
				if(radman.isSelected()){
					gender = "男";
				}else{
					gender = "女";
				}
				
				age = (byte) Integer.parseInt(addAgeField.getText());
				phone = addPhoneField.getText();
				if(radEmp.isSelected()){
					post = "1";
				}else{
					post = "0";
				}
				username = addUserField.getText();
				password = addPassField.getText();
				
				IEmployeeService employeeService = new EmployeeServiceImpl();
				Employee employee = new Employee();

				employee.setEmpName(name);
				employee.setEmpGender(gender);
				employee.setEmpAge(age);
				employee.setEmpPhone(phone);
				employee.setEmpPost(post);
				employee.setUsername(username);
				employee.setPassword(password);
				
				try {
					employeeService.addEmployee(employee);
					JOptionPane.showMessageDialog(contentPane, "添加员工成功！","提示",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("添加员工成功！");
				} catch (ServiceException ee) {
					System.out.println(employee.getEmpName());
					System.out.println(employee.getEmpGender());
					System.out.println(employee.getEmpAge());
					System.out.println(employee.getEmpPhone());
					System.out.println(employee.getEmpPost());
					System.out.println(employee.getUsername());
					System.out.println(employee.getPassword());
					
					JOptionPane.showMessageDialog(contentPane, "添加失败！","提示",JOptionPane.INFORMATION_MESSAGE);
					ee.printStackTrace();
				}

			}
		});
		confirmbtn.setBounds(39, 573, 113, 42);
		frame.getContentPane().add(confirmbtn);

		JButton resetbtn = new JButton("重置");
		resetbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNameField.setText("");
				radman.setSelected(true);
				addAgeField.setText("");
				addPhoneField.setText("");
				radEmp.setSelected(true);
				addUserField.setText("");
				addPassField.setText("");
			}
		});
		resetbtn.setBounds(191, 573, 113, 42);
		frame.getContentPane().add(resetbtn);

		JButton cancelbtn = new JButton("取消");
		cancelbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		cancelbtn.setBounds(343, 573, 113, 42);
		frame.getContentPane().add(cancelbtn);
		
		
		
	}
}
