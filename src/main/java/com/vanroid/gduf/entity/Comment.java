package com.vanroid.gduf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @ClassName Comment.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 广金圈评论的实体类
 * 
 * @version 1.0
 */
@Entity
@Table(name = "gd_comment")
public class Comment {
	private int cid;
	private Circle tid;
	private User sender;
	private User receiver;
	private String text;
	private Date ctime;

	@Id
	@GeneratedValue
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@ManyToOne(targetEntity = Circle.class)
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

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "receiver")
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
