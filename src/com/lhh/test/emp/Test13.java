package com.lhh.test.emp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * 测试JLabel显示动态时间的方法
 * 
 * @author 46512
 *
 */

public class Test13 extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7011067750344727132L;

	private JLabel label1;
	private JLabel label2;

	public Test13() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 200);
		this.setResizable(false);

		this.setTitle("测试时间显示");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}

		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}

		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		this.setLayout(null);

		label1 = new JLabel("当前时间：");
		label1.setSize(100, 30);
		label1.setLocation(30, 20);
		label1.setHorizontalAlignment(JLabel.RIGHT);
		this.getContentPane().add(label1);

		label2 = new JLabel("");
		label2.setSize(200, 30);
		label2.setLocation(140, 20);
		label2.setHorizontalAlignment(JLabel.LEFT);
		this.getContentPane().add(label2);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Date d = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			label2.setText(df.format(d));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Test13 t = new Test13();
		new Thread(t).start();
		t.setVisible(true);
	}

}
