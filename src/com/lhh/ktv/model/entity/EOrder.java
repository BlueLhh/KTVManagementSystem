package com.lhh.ktv.model.entity;


/**
 * 
 * 子订单实体类
 * 
 * @author 46512
 *
 */
public class EOrder {
	private Long eId;//子订单编号
	private Order order = new Order();//订单编号（外键）
	private String egName;//商品名称
	private int eCount;//数量
	private double egPrice;//商品单价
	private double eMoney;//金额
	
	public EOrder() {
		
	}
	
	public EOrder(Long eId, String egName, int eCount, double egPrice, double eMoney) {
		super();
		this.eId = eId;
		this.egName = egName;
		this.eCount = eCount;
		this.egPrice = egPrice;
		this.eMoney = eMoney;
	}

	public Long geteId() {
		return eId;
	}

	public void seteId(Long eId) {
		this.eId = eId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getEgName() {
		return egName;
	}

	public void setEgName(String egName) {
		this.egName = egName;
	}

	public int geteCount() {
		return eCount;
	}

	public void seteCount(int eCount) {
		this.eCount = eCount;
	}

	public double getEgPrice() {
		return egPrice;
	}

	public void setEgPrice(double egPrice) {
		this.egPrice = egPrice;
	}

	public double geteMoney() {
		return eMoney;
	}

	public void seteMoney(double eMoney) {
		this.eMoney = eMoney;
	}

	@Override
	public String toString() {
		return "子订单表信息 [子订单=" + eId + ", 订单编号=" + order + ", 商品名称=" + egName + ", 数量=" + eCount
				+ ", 单价=" + egPrice + ", 金额=" + eMoney + "]";
	}
	
}
