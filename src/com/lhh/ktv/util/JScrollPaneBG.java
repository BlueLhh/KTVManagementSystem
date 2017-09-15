package com.lhh.ktv.util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class JScrollPaneBG extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8808882547894660842L;
	
	ImageIcon icon;
	Image img;

	public void empDateBg(){
		icon = new ImageIcon(getClass().getResource("/images/dataemp.jpg"));
		img = icon.getImage();
	}
	
}
