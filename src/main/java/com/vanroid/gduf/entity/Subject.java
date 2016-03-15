package com.vanroid.gduf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
* @ClassName Subject.java Create on 2015年8月29日     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author Jerry 983951558@qq.com  
*  
* @Description:   科目表实体类，与成绩表相关，对应数据库gd_subject表
*
* @version 1.0
 */
@Entity
@Table(name="gd_subject")
public class Subject {
	private int sid;
	private String gname;
	private double xf;
	private double jd;
	private double cj;
	private String dept;
	@Id
	@GeneratedValue
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public double getXf() {
		return xf;
	}

	public void setXf(double xf) {
		this.xf = xf;
	}

	public double getJd() {
		return jd;
	}

	public void setJd(double jd) {
		this.jd = jd;
	}

	public double getCj() {
		return cj;
	}

	public void setCj(double cj) {
		this.cj = cj;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
