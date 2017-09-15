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
	private String roomStatus;// 包房状态，初始0为未开箱，1为已经开箱

	private Reserve reserve = new Reserve();

	private Order order = new Order();

	public Room() {

	}

	public Room(Long roomId, String roomType, double roomPrice, Reserve reserve, Order order, String roomStatus) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.reserve = reserve;
		this.order = order;
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

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	@Override
	public String toString() {
		return "包房信息 [包房编号=" + roomId + ", 包房类型=" + roomType + ", 包房费用=" + roomPrice + "包房状态" + roomStatus + "]";
	}

}
