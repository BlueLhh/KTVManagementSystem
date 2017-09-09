package com.lhh.ktv.util;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 
 * 设置窗口随着鼠标的拖动而移动
 * 
 * @author 46512
 *
 */

public class WindowMove implements MouseListener, MouseMotionListener {

	private Point offset;
	private Component host;

	public void install(Component component) {
		uninstall();
		host = component;
		host.addMouseListener(this);
		host.addMouseMotionListener(this);
	}

	public synchronized void uninstall() {
		if (host != null) {
			host.removeMouseListener(this);
			host.removeMouseMotionListener(this);
			host = null;
		}
	}

	public void mousePressed(MouseEvent e) {
		if (e.getSource() == host)
			offset = e.getPoint();
	}

	public void mouseDragged(MouseEvent e) {
		if (e.getSource() != host)
			return;
		final int x = host.getX();
		final int y = host.getY();
		final Point lastAt = e.getPoint();
		host.setLocation(x + lastAt.x - offset.x, y + lastAt.y - offset.y);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}
}
