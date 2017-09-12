package com.lhh.ktv.view;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.service.IEmployeeService;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.util.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * 设置边框隐藏
 * 
 * @author 46512
 *
 */
public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5576377560207024153L;
	private JPanel contentPane;
	private JTextField txtloginID;
	private JPasswordField txtloginPW;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					BorderHide.setJFrameHide(frame);//设置边框隐藏
					new WindowMove().install(frame);//边框隐藏之后可以移动
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		
		
		
	}
	
	/**
	 * 登录界面
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblloginID = new JLabel("账号：");
		lblloginID.setBounds(157, 139, 45, 18);
		contentPane.add(lblloginID);
		
		JLabel lblloginPW = new JLabel("密码：");
		lblloginPW.setBounds(157, 184, 45, 18);
		contentPane.add(lblloginPW);
		
		txtloginID = new JTextField();
		txtloginID.setBounds(216, 136, 196, 24);
		contentPane.add(txtloginID);
		txtloginID.setColumns(10);
		
		txtloginPW = new JPasswordField();
		txtloginPW.setBounds(216, 181, 196, 24);
		contentPane.add(txtloginPW);
		
		JButton btnlogin = new JButton("登录");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String loginID;//登录账号
				String loginPW;//登录密码
				
				loginID = txtloginID.getText();
				loginPW = String.valueOf(txtloginPW.getPassword()) ;//getPassword获取的字符类型是char。需要强转成Stringleixing
				
				IEmployeeService employeeService = new EmployeeServiceImpl();
				
				try {
					if(employeeService.login(loginID, loginPW)){
						//TODO 登录成功，在此处弹出一个提示框！
						System.out.println("登录成功！");
					}else{
						System.out.println(loginID+"---"+loginPW);
						System.out.println("登录失败！");
					}
				} catch (ServiceException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
			}
		});
		btnlogin.setBounds(227, 249, 77, 27);
		BorderHide.setBtnBorderHide(btnlogin);//设置按钮边框隐藏
		contentPane.add(btnlogin);
		
		JButton btnexit = new JButton("退出");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnexit.setBounds(324, 249, 77, 27);
		BorderHide.setBtnBorderHide(btnexit);//设置按钮边框隐藏
		contentPane.add(btnexit);
	}
}
