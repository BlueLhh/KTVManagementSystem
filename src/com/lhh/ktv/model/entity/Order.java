package com.lhh.ktv.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 订单实体类
 * 
 * @author 46512
 *
 */
public class Order {
	private Long orderId;// 订单ID
	private Room roomId = new Room();// 包房编号
	private String orpName;// 消费者姓名
	private String ordOpentime;// 开包时间
	private String ordEndtime;// 结账时间
	private double ordrmPrice;// 包房费用
	private double ordAmtall;// 商品消费总额
	private double ordAllamtall;// 消费合计
	private String ordStatus;// 订单状态。0是初始状态，为未结账。1为已经结账
	private String operator;// 操作人员

	// 与子订单是一对多关系
	private List<EOrder> eorder = new ArrayList<EOrder>();

	public Order() {

	}

	public Order(Long orderId, String orpName, String ordOpentime, String ordEndtime, double ordrmPrice,
			double ordAmtall, double ordAllamtall, String ordStatus, String operator) {
		super();
		this.orderId = orderId;
		this.orpName = orpName;
		this.ordOpentime = ordOpentime;
		this.ordEndtime = ordEndtime;
		this.ordrmPrice = ordrmPrice;
		this.ordAmtall = ordAmtall;
		this.ordAllamtall = ordAllamtall;
		this.ordStatus = ordStatus;
		this.operator = operator;
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

	public String getOrdOpentime() {
		return ordOpentime;
	}

	public void setOrdOpentime(String ordOpentime) {
		this.ordOpentime = ordOpentime;
	}

	public String getOrdEndtime() {
		return ordEndtime;
	}

	public void setOrdEndtime(String ordEndtime) {
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

	public String getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<EOrder> getEorder() {
		return eorder;
	}

	public void setEorder(List<EOrder> eorder) {
		this.eorder = eorder;
	}

	@Override
	public String toString() {
		return "订单信息 [订单编号=" + orderId + ", 包房编号=" + roomId + ", 客户名字=" + orpName + ", 开包时间=" + ordOpentime + ", 结账时间="
				+ ordEndtime + ", 包房费用=" + ordrmPrice + ", 商品消费总额=" + ordAmtall + ", 消费合计=" + ordAllamtall + ", 订单状态="
				+ ordStatus + ", 操作人员=" + operator + ", 子订单集合=" + eorder + "]";
	}
}
