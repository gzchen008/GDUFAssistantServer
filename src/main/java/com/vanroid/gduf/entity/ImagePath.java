package com.vanroid.gduf.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gd_circleimg")
public class ImagePath implements Serializable{
	private int pid;
	private Circle tid;// 对应哪条朋友圈
	private String path;// 该条朋友圈下的某张图片路径

	@Id
	@GeneratedValue
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	@Column(name="ImagePath")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "tid")
	public Circle getTid() {
		return tid;
	}

	public void setTid(Circle tid) {
		this.tid = tid;
	}

}
