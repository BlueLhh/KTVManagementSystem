package com.lhh.ktv.view;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.service.IEmployeeService;
import com.lhh.ktv.model.service.impl.EmployeeServiceImpl;
import com.lhh.ktv.util.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

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
					BorderHide.setJFrameHide(frame);// 设置边框隐藏
					new WindowMove().install(frame);// 边框隐藏之后可以移动
					frame.setVisible(true);
					//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	/**
	 * 登录界面 Create the frame.
	 */
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 613);
		contentPane = new BGJPanel();
		((BGJPanel) contentPane).mainPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BGJPanel loginPanel = new BGJPanel();
		loginPanel.loginPanel();
		loginPanel.setBounds(375, 180, 420, 380);
		loginPanel.setOpaque(false);// 设置JPanel为透明
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);

		txtloginID = new JTextField();
		txtloginID.setForeground(SystemColor.activeCaptionBorder);
		txtloginID.setSelectionColor(Color.GRAY);// 设置输入的内容的颜色
		txtloginID.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		txtloginID.setOpaque(false);// 设置文本框透明
		txtloginID.setBorder(new EmptyBorder(0, 0, 0, 0));// 去除边框
		txtloginID.setBounds(35, 114, 354, 48);
		loginPanel.add(txtloginID);
		txtloginID.setColumns(10);

		txtloginPW = new JPasswordField();
		txtloginPW.setForeground(SystemColor.activeCaptionBorder);
		txtloginPW.setSelectionColor(Color.GRAY);
		txtloginPW.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		txtloginPW.setOpaque(false);
		txtloginPW.setBorder(new EmptyBorder(0, 0, 0, 0));// 去除边框
		txtloginPW.setBounds(35, 212, 354, 48);
		loginPanel.add(txtloginPW);

		// 登录按钮
		JButton btnLogin = new JButton();
		BtnEvent.btnLogin(btnLogin);

		btnLogin.setBounds(80, 290, 110, 45);
		loginPanel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String loginID;// 登录账号
				String loginPW;// 登录密码

				loginID = txtloginID.getText();
				loginPW = String.valueOf(txtloginPW.getPassword());// getPassword获取的字符类型是char。需要强转成Stringleixing

				IEmployeeService employeeService = new EmployeeServiceImpl();

				try {
					if (employeeService.login(loginID, loginPW)) {
						// TODO 登录成功，在此处弹出一个提示框！
						System.out.println("登录成功！");
					} else {
						System.out.println(loginID + "---" + loginPW);
						System.out.println("登录失败！");
					}
				} catch (ServiceException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
			}
		});
		BorderHide.setBtnBorderHide(btnLogin);

		// 取消
		JButton btnClose = new JButton();
		BtnEvent.btnClose(btnClose);
		btnClose.setBounds(231, 290, 110, 45);
		loginPanel.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		BorderHide.setBtnBorderHide(btnClose);

		JButton btnExit = new JButton();
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		BtnEvent.btnExit(btnExit);
		btnExit.setBounds(1140, 0, 30, 30);
		contentPane.add(btnExit);
		BorderHide.setBtnBorderHide(btnExit);
	}
}
