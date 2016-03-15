package com.vanroid.gduf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 社团发布的消息实体
 * 
 * @author Joe_Huang
 * 
 */
@Entity
@Table(name = "gd_club_message")
public class ClubMessage {
	private Integer id; // 编号
	private String message_content;// 消息内容
	private Date message_publishdate;// 发表时间
	private Club club;// 属于哪一个社团
	private Integer message_state;// 审核状态
	private String message_title;// 消息标题
	private String message_photo;// 消息图片

	public ClubMessage() {
	}

	public ClubMessage(String message_content, Date message_publishdate,
			Integer message_state, String message_title, String message_photo) {
		super();
		this.message_content = message_content;
		this.message_publishdate = message_publishdate;
		this.message_state = message_state;
		this.message_title = message_title;
		this.message_photo = message_photo;
	}

	public String getMessage_photo() {
		return message_photo;
	}

	public void setMessage_photo(String message_photo) {
		this.message_photo = message_photo;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public Date getMessage_publishdate() {
		return message_publishdate;
	}

	public void setMessage_publishdate(Date message_publishdate) {
		this.message_publishdate = message_publishdate;
	}

	@ManyToOne
	@JoinColumn(name = "club_id", referencedColumnName = "id", nullable = false)
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Integer getMessage_state() {
		return message_state;
	}

	public void setMessage_state(Integer message_state) {
		this.message_state = message_state;
	}

	public String getMessage_title() {
		return message_title;
	}

	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}

}
