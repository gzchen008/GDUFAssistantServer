package com.vanroid.gduf.entity;
/**
 * 图书馆个人信息用户模型
 * @author CGZ
 *
 */
public class LibraryUserInfo {
	/**
	 * 证号
	 */
	private String stuId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 单位
	 */
	private String dept;
	/**
	 * 当前状态
	 */
	private String status;
	/**
	 * 预存金我额 
	 */
	private String balance;
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "LibraryUserInfo [stuId=" + stuId + ", name=" + name + ", dept="
				+ dept + ", status=" + status + ", balance=" + balance + "]";
	}
	
	
}
