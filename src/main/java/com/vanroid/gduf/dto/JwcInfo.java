package com.vanroid.gduf.dto;

/**
 * 
 * @ClassName JwcLoginInfo.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description:教务系统登陆信息dto
 * 
 * @version 1.0
 */
public class JwcInfo {
	private String xh;// 学号
	private String password;// 教务系统密码
	private String xm;// 姓名
	private String year;// 查询的年份
	private int xq;// 查询的学期

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getXq() {
		return xq;
	}

	public void setXq(int xq) {
		this.xq = xq;
	}

}
