package com.vanroid.gduf.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @ClassName Course.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO(对应数据库的course表的实体类)
 * 
 * @version 1.0
 */
@Entity
@Table(name = "gd_course")
public class Course {
	private int cid;
	private String stuId;
	private String year;
	private int xq;
	private Set<ClassBean> classes = new HashSet<ClassBean>();

	@Id
	@GeneratedValue
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cid")
	public Set<ClassBean> getClasses() {
		return classes;
	}

	public void setClasses(Set<ClassBean> classes) {
		this.classes = classes;
	}

	public Course() {
		// TODO 自动生成的构造函数存根
	}

	public Course(String stuId, String year, int xq) {
		super();
		this.stuId = stuId;
		this.year = year;
		this.xq = xq;
	}

	@Override
	public String toString() {
		return "Course [cid=" + cid + ", stuId=" + stuId + ", year=" + year
				+ ", xq=" + xq + ", classes=" + classes + "]";
	}

}
