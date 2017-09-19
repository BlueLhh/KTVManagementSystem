package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DelEmpFrame {

	static private JFrame frame;
	private JTextField delIDField;
	
	//获取ID
	static Long getDelID; 

	public static Long getGetDelID() {
		return getDelID;
	}

	public static void setGetDelID(Long getDelID) {
		DelEmpFrame.getDelID = getDelID;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DelEmpFrame window = new DelEmpFrame();
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
	public DelEmpFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 524);
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
		closebtn.setBounds(557, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);
		
		JLabel delTileLabel = new JLabel("删除员工");
		delTileLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		delTileLabel.setBounds(229, 27, 102, 48);
		frame.getContentPane().add(delTileLabel);
		
		JLabel delIDLabel = new JLabel("请输入ID：");
		delIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		delIDLabel.setBounds(113, 115, 102, 40);
		frame.getContentPane().add(delIDLabel);
		
		delIDField = new JTextField();
		delIDField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		delIDField.setBounds(229, 115, 86, 40);
		frame.getContentPane().add(delIDField);
		delIDField.setColumns(10);
		
		JLabel errinfoLable = new JLabel("*错误提示，没有这个人！");
		errinfoLable.setForeground(Color.RED);
		errinfoLable.setBounds(113, 158, 173, 18);
		errinfoLable.setVisible(false);
		frame.getContentPane().add(errinfoLable);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(14, 189, 541, 275);
		infoPanel.setVisible(false);
		frame.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel infoshowLabel = new JLabel("要删除的员工信息如下：");
		infoshowLabel.setBounds(45, 8, 165, 18);
		infoPanel.add(infoshowLabel);
		
		JLabel delID = new JLabel("员工编号：");
		delID.setBounds(45, 43, 90, 24);
		delID.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		infoPanel.add(delID);
		
		JLabel delGender = new JLabel("员工性别：");
		delGender.setBounds(45, 80, 90, 24);
		delGender.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		infoPanel.add(delGender);
		
		JLabel delName = new JLabel("员工姓名：");
		delName.setBounds(256, 43, 90, 24);
		delName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		infoPanel.add(delName);
		
		JLabel getID = new JLabel("");
		getID.setBounds(138, 43, 57, 27);
		infoPanel.add(getID);
		getID.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JLabel getGender = new JLabel("");
		getGender.setBounds(138, 80, 57, 27);
		infoPanel.add(getGender);
		getGender.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JLabel getName = new JLabel("");
		getName.setBounds(348, 43, 133, 27);
		infoPanel.add(getName);
		getName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JLabel delAge = new JLabel("员工年龄：");
		delAge.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delAge.setBounds(256, 80, 90, 24);
		infoPanel.add(delAge);
		
		JLabel getAge = new JLabel("");
		getAge.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getAge.setBounds(349, 80, 57, 27);
		infoPanel.add(getAge);
		
		JLabel delPost = new JLabel("员工职位：");
		delPost.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delPost.setBounds(45, 117, 90, 24);
		infoPanel.add(delPost);
		
		JLabel delUser = new JLabel("员工账号：");
		delUser.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delUser.setBounds(45, 154, 90, 24);
		infoPanel.add(delUser);
		
		JLabel getPost = new JLabel("");
		getPost.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getPost.setBounds(138, 117, 57, 27);
		infoPanel.add(getPost);
		
		JLabel getUser = new JLabel("");
		getUser.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		getUser.setBounds(138, 154, 104, 27);
		infoPanel.add(getUser);
		
		JLabel delPhone = new JLabel("联系方式：");
		delPhone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delPhone.setBounds(256, 117, 90, 24);
		infoPanel.add(delPhone);
		
		JLabel delPass = new JLabel("账号密码：");
		delPass.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delPass.setBounds(256, 154, 90, 24);
		infoPanel.add(delPass);
		
		JLabel getPhone = new JLabel("");
		getPhone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		getPhone.setBounds(348, 117, 133, 27);
		infoPanel.add(getPhone);
		
		JLabel getPass = new JLabel("");
		getPass.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		getPass.setBounds(349, 154, 132, 27);
		infoPanel.add(getPass);
		
		JButton confirmDelbtn = new JButton("确认删除");
		confirmDelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO 确认删除
				new ConfirmDelFrame();
			}
		});
		confirmDelbtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		confirmDelbtn.setBounds(82, 219, 113, 27);
		infoPanel.add(confirmDelbtn);
		
		JButton cancelDelbtn = new JButton("取消删除");
		cancelDelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		cancelDelbtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		cancelDelbtn.setBounds(293, 220, 113, 27);
		infoPanel.add(cancelDelbtn);
		
		JButton delOKbtn = new JButton("确定");
		delOKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoPanel.setVisible(true);
				errinfoLable.setVisible(false);
				Long num;
				String post;
				num = Long.parseLong(delIDField.getText());
				EmployeeServiceImpl emp = new EmployeeServiceImpl();
				Employee employee = new Employee();
				
				try {
					employee = emp.findEmployee(num);
					
					if(employee.getEmpPost().equals("0")){
						post = "经理";
					}else{
						post = "前台";
					}
					getDelID = employee.getEmpId();
					getID.setText(Long.toString(getDelID));
					getName.setText(employee.getEmpName());
					getGender.setText(employee.getEmpGender());
					getAge.setText(Integer.toString(employee.getEmpAge()));
					getPhone.setText(employee.getEmpPhone());
					getPost.setText(post);
					getUser.setText(employee.getUsername());
					getPass.setText(employee.getPassword());
					
				} catch (Exception ee) {
					// TODO Auto-generated catch block
					errinfoLable.setVisible(true);
					try {
						throw new ServiceException("查找失败！");
					} catch (ServiceException E) {
						// TODO Auto-generated catch block
						E.printStackTrace();
					}
					//ee.printStackTrace();
				}
				
			}
		});
		delOKbtn.setBounds(350, 115, 86, 40);
		frame.getContentPane().add(delOKbtn);
	}
	
	static public void closeDelEmp(){
		frame.setVisible(false);
		frame.dispose();
	}
	
}
