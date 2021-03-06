/**
 * 
 */
package com.lhh.ktv.util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author 46512
 *
 */
public class BGJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5939914134096358498L;

	ImageIcon icon;
	Image img;

	public void roomsrBG() {
		icon = new ImageIcon(getClass().getResource("/images/roomsr.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void roomsBG() {
		icon = new ImageIcon(getClass().getResource("/images/rooms.png"));// 在src文件下
		img = icon.getImage();
	}

	public void memtopBG() {
		icon = new ImageIcon(getClass().getResource("/images/memtopbg.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void goodstopBG() {
		icon = new ImageIcon(getClass().getResource("/images/goodstoppanel.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void mainBG() {
		icon = new ImageIcon(getClass().getResource("/images/mainbg.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void empTopBG() {
		icon = new ImageIcon(getClass().getResource("/images/emptop.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void empRigthBG() {
		icon = new ImageIcon(getClass().getResource("/images/rightbg.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void busOrderBG() {
		icon = new ImageIcon(getClass().getResource("/images/ordertopbg.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void busJpanelBG() {
		icon = new ImageIcon(getClass().getResource("/images/busmainbg.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void goodsInfopanelBG() {
		icon = new ImageIcon(getClass().getResource("/images/infopanelbg.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void infofirstpanelBG() {
		icon = new ImageIcon(getClass().getResource("/images/infofirstpanel.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void topPanel() {
		icon = new ImageIcon(getClass().getResource("/images/topbg.jpg"));// 在src文件下
		img = icon.getImage();
	}

	public void loginPanel() {
		icon = new ImageIcon(getClass().getResource("/images/login.png"));// 在src文件下
		img = icon.getImage();
	}

	public void mainPanel() {
		icon = new ImageIcon(getClass().getResource("/images/BGmain.jpg"));
		img = icon.getImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void bigMainFrame() {
		icon = new ImageIcon(getClass().getResource("/images/mainFrame.jpg"));
		img = icon.getImage();
	}

	public void menBg() {
		icon = new ImageIcon(getClass().getResource("/images/menbg.jpg"));
		img = icon.getImage();
	}

	public void fristBg() {
		icon = new ImageIcon(getClass().getResource("/images/frist.jpg"));
		img = icon.getImage();
	}

	public void empDateBg() {
		icon = new ImageIcon(getClass().getResource("/images/dataemp.jpg"));
		img = icon.getImage();
	}
}
