package com.lhh.test.room;

import java.util.Scanner;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Room;
import com.lhh.ktv.model.service.IRoomService;
import com.lhh.ktv.model.service.impl.RoomServiceImpl;

public class AddRoom {
	public static void main(String[] args) {
		
		String roomType;//包房种类
		double roomPrice;//包房费用
		String roomStatus;//包房状态
		
		IRoomService irs = new RoomServiceImpl();
		Room room = new Room();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("请输入包房种类：");
		roomType = sc.next();
		System.out.println("请输入包房费用：");
		roomPrice = sc.nextDouble();
		System.out.println("请输入包房状态：");
		roomStatus = sc.next();
		
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		room.setRoomStatus(roomStatus);
		
		try {
			irs.addRoom(room);
			System.out.println("添加包房成功！");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
