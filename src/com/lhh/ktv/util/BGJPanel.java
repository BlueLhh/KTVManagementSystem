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
public class BGJPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5939914134096358498L;
	
	ImageIcon icon;
	Image img;
	
	public void loginPanel(){
		icon = new ImageIcon(getClass().getResource("/images/login.png"));//在src文件下
		img = icon.getImage();
	}

	public void mainPanel(){
		icon = new ImageIcon(getClass().getResource("/images/BGmain.jpg"));
		img = icon.getImage();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
	}
	
	public void bigMainFrame(){
		icon = new ImageIcon(getClass().getResource("/images/mainFrame.png"));
		img = icon.getImage();
	}
	
}
