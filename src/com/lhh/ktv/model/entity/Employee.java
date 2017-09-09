package com.lhh.ktv.model.entity;

/**
 * 员工实体类
 * 
 * @author 46512
 *
 */
public class Employee {
	private Long empId; //员工ID
	private String empName;//员工姓名
	private String empGender;//员工性别
	private byte empAge;//员工年龄
	private String empPhone;//员工联系方式
	private String empPost;//员工职别
	private String empCode;//账号密码
	
	public Employee() {
		
	}
	
	public Employee(Long empId, String empName, String empGender, byte empAge, String empPhone, String empPost,String empCode) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empGender = empGender;
		this.empAge = empAge;
		this.empPhone = empPhone;
		this.empPost = empPost;
		this.empCode = empCode;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public byte getEmpAge() {
		return empAge;
	}

	public void setEmpAge(byte empAge) {
		this.empAge = empAge;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpPost() {
		return empPost;
	}

	public void setEmpPost(String empPost) {
		this.empPost = empPost;
	}
	
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	@Override
	public String toString() {
		return "员工信息 [员工编号=" + empId + ", 员工姓名=" + empName + ", 员工性别=" + empGender + ", 员工年龄=" + empAge
				+ ", 联系方式=" + empPhone + ", 员工职别=" + empPost + ", 账号密码=" + empCode +"]";
	}
	
}
