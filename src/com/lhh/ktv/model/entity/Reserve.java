package com.lhh.ktv.model.entity;


/**
 * 预定信息表实体类
 * 
 * @author 46512
 *
 */
public class Reserve {
	private Long resId;// 预定编号
	private Room room = new Room();// 预定包房编号（外键）
	private String resTime;// 预定时间
	private String resendTime;// 保留时间
	private String resPname;// 预定人姓名
	private Long resmemId;// 会员编号(0为非会员)
	private String resPhone;// 预定人号码

	
	
	public Reserve() {

	}

	public Reserve(Long resId, Room room, String resTime, String resendTime, String resPname, Long resmemId,
			String resPhone) {
		super();
		this.resId = resId;
		this.room = room;
		this.resTime = resTime;
		this.resendTime = resendTime;
		this.resPname = resPname;
		this.resmemId = resmemId;
		this.resPhone = resPhone;
	}
	
	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getResTime() {
		return resTime;
	}

	public void setResTime(String resTime) {
		this.resTime = resTime;
	}

	public String getResendTime() {
		return resendTime;
	}

	public void setResendTime(String resendTime) {
		this.resendTime = resendTime;
	}

	public String getResPname() {
		return resPname;
	}

	public void setResPname(String resPname) {
		this.resPname = resPname;
	}

	public Long getResmemId() {
		return resmemId;
	}

	public void setResmemId(Long resmemId) {
		this.resmemId = resmemId;
	}

	public String getResPhone() {
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	@Override
	public String toString() {
		return "预定信息 [预定编号=" + resId + ", 包房编号=" + room + ", 预定时间=" + resTime + ", 保留时间="
				+ resendTime + ", 预订人姓名=" + resPname + ", 会员编号=" + resmemId + ", 预订人号码=" + resPhone + "]";
	}

}
