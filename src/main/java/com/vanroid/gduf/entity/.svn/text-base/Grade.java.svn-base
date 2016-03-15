package com.vanroid.gduf.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * 
* @ClassName Grade.java Create on 2015年8月29日     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author Jerry 983951558@qq.com  
*  
* @Description:   成绩表对应的实体类
*
* @version 1.0
 */
@Entity
@Table(name="gd_grade")
public class Grade {
	private int gid;
	private String stuId;
	private String year;
	private int xq;
	Set<Subject> subjects=new HashSet<Subject>();
	
	@ManyToMany(cascade = CascadeType.ALL ,fetch=FetchType.EAGER)
	@JoinTable(name = "gd_grade_middle", joinColumns = @JoinColumn(name = "gid"), inverseJoinColumns = @JoinColumn(name = "sid"))
	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public Grade(String stuId, String year, int xq) {
		super();
		this.stuId = stuId;
		this.year = year;
		this.xq = xq;
	}

	@Id
	@GeneratedValue
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
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
	public Grade() {
		// TODO 自动生成的构造函数存根
	}
}
