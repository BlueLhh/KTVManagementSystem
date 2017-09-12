package com.lhh.ktv.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lhh.ktv.util.BGJPanel;
import com.lhh.ktv.util.BorderHide;
import com.lhh.ktv.util.WindowMove;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 43473320193261407L;
	private JPanel contentPane;
	private CardLayout card;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					BorderHide.setJFrameHide(frame);// 设置边框隐藏
					new WindowMove().install(frame);// 边框隐藏之后可以移动
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1370, 800);
		contentPane = new BGJPanel();
		((BGJPanel) contentPane).bigMainFrame();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.setBounds(0, 0, 1370, 150);
		contentPane.add(topPanel);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.GRAY);
		menuPanel.setBounds(0, 190, 300, 590);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.PINK);
		mainPanel.setBounds(300, 190, 1000, 590);
		contentPane.add(mainPanel);
		card = new CardLayout(0, 0);
		mainPanel.setLayout(card);

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

		JButton roomMage = new JButton("包房管理");
		roomMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "room");
			}
		});
		roomMage.setBounds(62, 25, 159, 85);
		menuPanel.add(roomMage);

		JButton memMage = new JButton("会员管理");
		memMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "mem");
			}
		});
		memMage.setBounds(62, 136, 159, 85);
		menuPanel.add(memMage);

		JButton goodsMage = new JButton("商品管理");
		goodsMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "goods");
			}
		});
		goodsMage.setBounds(62, 246, 159, 85);
		menuPanel.add(goodsMage);

		JButton empMage = new JButton("员工管理");
		empMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "emp");
			}
		});
		empMage.setBounds(62, 356, 159, 85);
		menuPanel.add(empMage);

		JButton busMage = new JButton("营业查询");
		busMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(mainPanel, "bus");
			}
		});
		busMage.setBounds(62, 465, 159, 85);
		menuPanel.add(busMage);

	}

}
