package com.lhh.ktv.model.entity;

/**
 * 包房实体类
 * 
 * @author 46512
 *
 */
public class Room {
	private Long roomId;// 包房编号
	private String roomType;// 包房种类
	private double roomPrice;// 包房费用
	private String roomStatus;// 包房状态，未使用，已预订，使用中

	//private Reserve reserve = new Reserve();

	//private Order order = new Order();

	public Room() {

	}

	public Room(Long roomId, String roomType, double roomPrice, String roomStatus) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.roomStatus = roomStatus;
	}
	
	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}


	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	@Override
	public String toString() {
		return "包房信息 [包房编号=" + roomId + ", 包房类型=" + roomType + ", 包房费用=" + roomPrice + "包房状态=" + roomStatus + "]";
	}

}
