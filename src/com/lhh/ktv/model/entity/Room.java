package com.lhh.ktv.model.entity;

/**
 * 包房实体类
 * 
 * @author 46512
 *
 */
public class Room {
	private Long roomId;// 包房编号
	private String roomKid;// 包房种类
	private double roomPrice;// 包房费用

	public Room() {

	}

	public Room(Long roomId, String roomKid, double roomPrice) {
		super();
		this.roomId = roomId;
		this.roomKid = roomKid;
		this.roomPrice = roomPrice;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomKid() {
		return roomKid;
	}

	public void setRoomKid(String roomKid) {
		this.roomKid = roomKid;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	@Override
	public String toString() {
		return "包房信息 [包房编号=" + roomId + ", 包房类型=" + roomKid + ", 包房费用=" + roomPrice + "]";
	}

}
