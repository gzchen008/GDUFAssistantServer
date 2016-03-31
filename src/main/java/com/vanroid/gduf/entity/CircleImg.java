package com.vanroid.gduf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @ClassName CircleImg.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 朋友圈上传相片的实体类
 * 
 * @version 1.0
 */
@Entity
@Table(name = "gd_circleImg")
public class CircleImg {
	private int pid; // 图片编号
	private Circle tid;// 对用的朋友圈id
	private String imagePath;

	@Id
	@GeneratedValue
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@ManyToOne(targetEntity = Circle.class)
	@JoinColumn(name = "tid")
	public Circle getTid() {
		return tid;
	}

	public void setTid(Circle tid) {
		this.tid = tid;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
