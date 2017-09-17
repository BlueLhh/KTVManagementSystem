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
	
	public void infofirstpanelBG(){
		icon = new ImageIcon(getClass().getResource("/images/infofirstpanel.jpg"));//在src文件下
		img = icon.getImage();
	}
	
	public void topPanel(){
		icon = new ImageIcon(getClass().getResource("/images/topbg.jpg"));//在src文件下
		img = icon.getImage();
	}
	
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
		icon = new ImageIcon(getClass().getResource("/images/mainFrame.jpg"));
		img = icon.getImage();
	}
	
	public void menBg(){
		icon = new ImageIcon(getClass().getResource("/images/menbg.jpg"));
		img = icon.getImage();
	}
	
	public void fristBg(){
		icon = new ImageIcon(getClass().getResource("/images/frist.jpg"));
		img = icon.getImage();
	}
	
	public void empDateBg(){
		icon = new ImageIcon(getClass().getResource("/images/dataemp.jpg"));
		img = icon.getImage();
	}
}
