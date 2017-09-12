package com.lhh.ktv.model.entity;


/**
 * 
 * 商品实体类
 * 
 * @author 46512
 *
 */
public class Goods {
	private Long goodsId;//商品id
	private String goodsName;//商品名称
	private double goodsPrice;//商品价格
	private int goodsCount;//商品库存
	
	private EOrder eorder = new EOrder();
	
	public Goods() {
		
	}
	
	public Goods(Long goodsId, String goodsName, double goodsPrice, int goodsCount, EOrder eorder) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsCount = goodsCount;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	public EOrder getEorder() {
		return eorder;
	}

	public void setEorder(EOrder eorder) {
		this.eorder = eorder;
	}

	@Override
	public String toString() {
		return "商品信息 [商品编号=" + goodsId + ", 商品名称=" + goodsName + ", 商品价格=" + goodsPrice + ", 商品库存="
				+ goodsCount + "]";
	}
	
}
