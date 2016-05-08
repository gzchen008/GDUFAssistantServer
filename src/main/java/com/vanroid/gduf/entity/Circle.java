package com.vanroid.gduf.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * @ClassName Circle.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 广金圈实体类
 * 
 * @version 1.0
 */
@Entity
@Table(name = "gd_circle")
public class Circle implements Serializable {
	/**
	 * 
	 */
	private int tid;
	private User sender;
	private Date createTime;
	private String content;
	private List<ImagePath> images = new ArrayList<ImagePath>();
	private List<Comment> comments = new ArrayList<Comment>();

	@Id
	@GeneratedValue
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	@OneToMany(mappedBy = "tid", targetEntity = ImagePath.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	public List<ImagePath> getImages() {
		return images;
	}

	@OneToMany(mappedBy = "tid", targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	public List<Comment> getComments() {
		return comments;
	}

	public void setImages(List<ImagePath> images) {
		this.images = images;
	}

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "sender")
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
