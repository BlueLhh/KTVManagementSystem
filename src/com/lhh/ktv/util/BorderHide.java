package com.lhh.ktv.util;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * 边框不可见
 * 按钮边框和JFrame边框
 * 
 * @author 46512
 *
 */
public class BorderHide {
	
	/**
	 * 
	 * 设置JFrame边框不可见
	 * 
	 */
	public static void setJFrameHide(JFrame jFrame){
		jFrame.setUndecorated(true);
	}
	
	/**
	 * 设置按钮边框不可见
	 * 
	 */
	public static void setBtnBorderHide(JButton jButton){
		jButton.setFocusPainted(false);//设置按钮内小框框不可见
		jButton.setBorderPainted(false);//设置按钮的外小框框不可见
	}
	
}
