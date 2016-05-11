package com.vanroid.gduf.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 社团文章实体类
 * 
 * @author joe
 *
 */
@Entity
@Table(name = "gd_message")
public class Message {
	@Id
	@GeneratedValue
	private int mid;
	private String mIcon;
	private String mTitle;
	@Column(length = 16777216)
	private String mContent;
	private Date m_publish_date;
	private int type;
	@ManyToOne
	@JoinColumn(name = "cid")
	private Club club;

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmContent() {
		return mContent;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
	}

	public Date getM_publish_date() {
		return m_publish_date;
	}

	public void setM_publish_date(Date m_publish_date) {
		this.m_publish_date = m_publish_date;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getmIcon() {
		return mIcon;
	}

	public void setmIcon(String mIcon) {
		this.mIcon = mIcon;
	}

	@Override
	public String toString() {
		return "Message [mid=" + mid + ", mTitle=" + mTitle + ", mContent=" + mContent + ", m_publish_date="
				+ m_publish_date + ", type=" + type + ", club=" + club + "]";
	}
}
