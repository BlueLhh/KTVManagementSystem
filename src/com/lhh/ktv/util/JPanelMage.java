package com.lhh.ktv.util;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * 
 * 根据按钮实现切换JPanel
 * 
 * @author 46512
 *
 */
public class JPanelMage {
	
	public void getRoomMage(JPanel jPanel){
		jPanel.setBackground(Color.BLUE);
		jPanel.setBounds(0, 0, 1000, 590);
		jPanel.setLayout(null);
	}
	public void getMemMage(JPanel jPanel){
		jPanel.setBackground(Color.GREEN);
		jPanel.setBounds(0, 0, 1000, 590);
		jPanel.setLayout(null);
	}
	public void getGoodsMage(JPanel jPanel){
		jPanel.setBackground(Color.DARK_GRAY);
		jPanel.setBounds(0, 0, 1000, 590);
		jPanel.setLayout(null);
	}
	public void getEmpMage(JPanel jPanel){
		jPanel.setBackground(Color.LIGHT_GRAY);
		jPanel.setBounds(0, 0, 1000, 590);
		jPanel.setLayout(null);
	}
	public void getBusMage(JPanel jPanel){
		jPanel.setBackground(Color.BLUE);
		jPanel.setBounds(0, 0, 1000, 590);
		jPanel.setLayout(null);
	}
	
	
}
