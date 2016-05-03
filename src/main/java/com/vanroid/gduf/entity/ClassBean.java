package com.vanroid.gduf.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @ClassName Class.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO(对应数据库中的gd_class表的实体类)
 * 
 * 
 * @version 1.0
 */
@Entity
@Table(name = "gd_class")
public class ClassBean {
	private int classId;
	private String title;
	private String classroom;
	private int cwhen;
	private int howlong;
	private int whichday;
	private int whichweek;
	private Course course;

	@Id
	@GeneratedValue
	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public int getCwhen() {
		return cwhen;
	}

	public void setCwhen(int cwhen) {
		this.cwhen = cwhen;
	}

	public int getHowlong() {
		return howlong;
	}

	public void setHowlong(int howlong) {
		this.howlong = howlong;
	}

	public int getWhichday() {
		return whichday;
	}

	public void setWhichday(int whichday) {
		this.whichday = whichday;
	}

	public int getWhichweek() {
		return whichweek;
	}

	public void setWhichweek(int whichweek) {
		this.whichweek = whichweek;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cid", insertable = false, updatable = false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
