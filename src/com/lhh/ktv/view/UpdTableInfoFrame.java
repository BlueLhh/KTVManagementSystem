package com.lhh.ktv.view;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lhh.ktv.model.entity.Employee;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.WindowMove;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdTableInfoFrame {

	private JFrame frame;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ConfirmUpdFrame window = new ConfirmUpdFrame();
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
	public UpdTableInfoFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 327, 237);
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
		closebtn.setBounds(297, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);
		
		JLabel promptLabel = new JLabel("确认修改？");
		promptLabel.setForeground(Color.RED);
		promptLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		promptLabel.setBounds(94, 32, 139, 67);
		frame.getContentPane().add(promptLabel);
		
		JButton OKbtn = new JButton("是");
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EmployeeServiceImpl emp = new EmployeeServiceImpl();
				Employee employee = new Employee();
				
				employee.setEmpName(GetTableFrame.getGetUpdName());
				employee.setEmpGender(GetTableFrame.getGetUpdGender());
				employee.setEmpAge(GetTableFrame.getGetUpdAge());
				employee.setEmpPhone(GetTableFrame.getGetUpdPhone());
				employee.setEmpPost(GetTableFrame.getGetUpdPost());
				employee.setUsername(GetTableFrame.getGetUpdUsername());
				employee.setPassword(GetTableFrame.getGetUpdPassword());
				employee.setEmpId(GetTableFrame.getGetUpdId());
							
				try {
					emp.updateEmployee(employee);
					System.out.println("更改成功！");
					JOptionPane.showMessageDialog(contentPane, "修改信息成功！","提示",JOptionPane.INFORMATION_MESSAGE);
					MainFrame.refresh();
					GetTableFrame.closeGetFrame();
					frame.setVisible(false);
					frame.dispose();
				} catch (Exception ee) {
					// TODO Auto-generated catch block
					System.out.println("修改失败！");
					JOptionPane.showMessageDialog(contentPane, "修改信息失败！","提示",JOptionPane.INFORMATION_MESSAGE);
					ee.printStackTrace();
				}
				
			}
		});
		OKbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		OKbtn.setBounds(72, 112, 64, 44);
		frame.getContentPane().add(OKbtn);
		
		JButton NObtn = new JButton("否");
		NObtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		NObtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		NObtn.setBounds(169, 112, 64, 44);
		frame.getContentPane().add(NObtn);
	}

}
