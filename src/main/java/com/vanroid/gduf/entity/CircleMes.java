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
 * @ClassName CircleMes.java Create on 2015年10月1日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO 朋友圈点赞评论的通知
 * 
 * @version 1.0
 */
@Entity
@Table(name = "gd_circlemes")
public class CircleMes {
	private int mid;
	private Circle tid;
	private User sender;
	private User recevier;
	private String mes;
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tid")
	public Circle getTid() {
		return tid;
	}

	public void setTid(Circle tid) {
		this.tid = tid;
	}

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "sender")
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.DETACH)
	@JoinColumn(name = "receiver")
	public User getRecevier() {
		return recevier;
	}

	public void setRecevier(User recevier) {
		this.recevier = recevier;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Id
	@GeneratedValue
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public CircleMes(Circle tid, User sender, User recevier, String mes,
			String status) {
		super();
		this.tid = tid;
		this.sender = sender;
		this.recevier = recevier;
		this.mes = mes;
		this.status = status;
	}

	public CircleMes() {
		super();
	}

}
