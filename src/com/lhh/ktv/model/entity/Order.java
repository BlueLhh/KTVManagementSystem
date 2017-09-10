package com.lhh.ktv.model.entity;

import java.sql.Date;

/**
 * 
 * 订单实体类
 * 
 * @author 46512
 *
 */
public class Order {
	private Long orderId;// 订单ID
	private Room roomId;// 包房编号
	private String orpName;// 消费者姓名
	private Date ordOpentime;// 开包时间
	private Date ordEndtime;// 结账时间
	private double ordrmPrice;// 包房费用
	private double ordAmtall;// 商品消费总额
	private double ordAllamtall;// 消费合计

	public Order() {

	}

	public Order(Long orderId, Room roomId, String orpName, Date ordOpentime, Date ordEndtime, double ordrmPrice,
			double ordAmtall, double ordAllamtall) {
		super();
		this.orderId = orderId;
		this.roomId = roomId;
		this.orpName = orpName;
		this.ordOpentime = ordOpentime;
		this.ordEndtime = ordEndtime;
		this.ordrmPrice = ordrmPrice;
		this.ordAmtall = ordAmtall;
		this.ordAllamtall = ordAllamtall;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Room getRoomId() {
		return roomId;
	}

	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}

	public String getOrpName() {
		return orpName;
	}

	public void setOrpName(String orpName) {
		this.orpName = orpName;
	}

	public Date getOrdOpentime() {
		return ordOpentime;
	}

	public void setOrdOpentime(Date ordOpentime) {
		this.ordOpentime = ordOpentime;
	}

	public Date getOrdEndtime() {
		return ordEndtime;
	}

	public void setOrdEndtime(Date ordEndtime) {
		this.ordEndtime = ordEndtime;
	}

	public double getOrdrmPrice() {
		return ordrmPrice;
	}

	public void setOrdrmPrice(double ordrmPrice) {
		this.ordrmPrice = ordrmPrice;
	}

	public double getOrdAmtall() {
		return ordAmtall;
	}

	public void setOrdAmtall(double ordAmtall) {
		this.ordAmtall = ordAmtall;
	}

	public double getOrdAllamtall() {
		return ordAllamtall;
	}

	public void setOrdAllamtall(double ordAllamtall) {
		this.ordAllamtall = ordAllamtall;
	}

	@Override
	public String toString() {
		return "订单信息 [订单编号=" + orderId + ", 包房编号=" + roomId + ", 客户名字=" + orpName + ", 开包时间="
				+ ordOpentime + ", 结账时间=" + ordEndtime + ", 包房费用=" + ordrmPrice + ", 商品消费总额=" + ordAmtall
				+ ", 消费合计=" + ordAllamtall + "]";
	}

}
