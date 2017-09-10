package com.lhh.ktv.model.entity;

/**
 * 会员实体类
 * 
 * @author 46512
 *
 */
public class Member {
	private Long memId;// 会员编号
	private String memName;// 会员姓名
	private String memGender;// 会员性别
	private byte memAge;// 会员年龄
	private String memPhone;// 联系方式

	public Member() {

	}

	public Member(Long memId, String memName, String memGender, byte memAge, String memPhone) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memGender = memGender;
		this.memAge = memAge;
		this.memPhone = memPhone;
	}

	public Long getMemId() {
		return memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemGender() {
		return memGender;
	}

	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}

	public byte getMemAge() {
		return memAge;
	}

	public void setMemAge(byte memAge) {
		this.memAge = memAge;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	@Override
	public String toString() {
		return "会员信息 [会员编号=" + memId + ", 会员姓名=" + memName + ", 会员性别=" + memGender + ", 会员年龄=" + memAge
				+ ", 联系方式=" + memPhone + "]";
	}

}
