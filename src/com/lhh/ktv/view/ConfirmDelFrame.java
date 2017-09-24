package com.lhh.ktv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.BtnEvent;
import com.lhh.ktv.util.DelInformation;
import com.lhh.ktv.util.WindowMove;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmDelFrame {

	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// ConfirmDelFrame window = new ConfirmDelFrame();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public ConfirmDelFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 283, 221);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		BorderHide.setJFrameHide(frame);// 设置边框隐藏
		new WindowMove().install(frame);// 边框隐藏之后可以移动
		frame.setVisible(true);
		frame.setResizable(false);

		JLabel infoLabel = new JLabel("确认删除？");
		infoLabel.setForeground(Color.RED);
		infoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		infoLabel.setBounds(71, 13, 139, 66);
		frame.getContentPane().add(infoLabel);

		JButton OKbtn = new JButton("是");
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 判断ID的路径
				Long delID = null;
				if (DelEmpFrame.getGetDelID() != null) {
					delID = DelEmpFrame.getGetDelID();
					// GetTableFrame.closeGetFrame();
				} else {
					delID = MainFrame.getGetID();
					// DelEmpFrame.closeDelEmp();
				}
				DelInformation.delEmpInfo(delID);
				JOptionPane.showMessageDialog(contentPane, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				MainFrame.refresh();

				if (DelEmpFrame.getGetDelID() != null) {
					GetTableFrame.closeGetFrame();
				} else {
					DelEmpFrame.closeDelEmp();
				}

				// -----这里是加入是从表格中获取的ID进行的删除的

				frame.setVisible(false);
				frame.dispose();
			}
		});
		OKbtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		OKbtn.setBounds(57, 92, 56, 43);
		frame.getContentPane().add(OKbtn);

		JButton NObtn = new JButton("否");
		NObtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		NObtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		NObtn.setBounds(139, 92, 56, 43);
		frame.getContentPane().add(NObtn);

		JButton closebtn = new JButton("");
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		closebtn.setBounds(253, 0, 30, 30);
		frame.getContentPane().add(closebtn);
		BtnEvent.btnExit(closebtn);
		BorderHide.setBtnBorderHide(closebtn);
	}

}
