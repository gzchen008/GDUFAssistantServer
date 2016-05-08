package com.vanroid.gduf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gd_lfcomment")
public class LFCommnet {
	@Id
	@GeneratedValue
	private int lfcId;
	private String content;
	private Date lfcDate;
	@ManyToOne(targetEntity = Message.class)
	@JoinColumn(name = "mid")
	private Message message;

	public int getLfcId() {
		return lfcId;
	}

	public void setLfcId(int lfcId) {
		this.lfcId = lfcId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLfcDate() {
		return lfcDate;
	}

	public void setLfcDate(Date lfcDate) {
		this.lfcDate = lfcDate;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "LFCommnet [lfcId=" + lfcId + ", content=" + content + ", lfcDate=" + lfcDate + ", message=" + message
				+ "]";
	}

}
