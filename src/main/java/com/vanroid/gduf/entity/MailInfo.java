package com.vanroid.gduf.entity;

import java.util.List;

/**
 * 邮件类
 * 
 * @author CGZ
 * 
 */

public class MailInfo {
	/**
	 * 邮件id
	 */
	private String id;
	/**
	 * 邮件链接
	 */
	private String link;
	/**
	 * 发件人
	 */
	private String sender;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发件日期
	 */
	private String date;
	/**
	 * 邮件大小
	 */
	private String size;
	/**
	 * 是否有附件
	 */
	private boolean isAttach;
	/**
	 * 附件地址 当数组大于1时，表示有多个附件
	 */
	private List attaches;
	/**
	 * 发件人id
	 */
	private String senderId;
	/**
	 * 是否阅读
	 */
	private int readFlag;
	/**
	 * 内容
	 * 
	 */
	private String content;
	/**
	 * 外部链接 null表示没有
	 */
	private String extraLinks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List getAttaches() {
		return attaches;
	}

	public void setAttaches(List attaches) {
		this.attaches = attaches;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public int getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(int readFlag) {
		this.readFlag = readFlag;
	}

	public boolean getIsAttach() {
		return isAttach;
	}

	public void setIsAttach(boolean isAttach) {
		this.isAttach = isAttach;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExtraLinks() {
		return extraLinks;
	}

	public void setExtraLinks(String extraLinks) {
		this.extraLinks = extraLinks;
	}

	public MailInfo(String id, String link, String sender, String title,
			String date, String size, boolean isAttach, List attaches,
			String senderId, int readFlag) {
		super();
		this.id = id;
		this.link = link;
		this.sender = sender;
		this.title = title;
		this.date = date;
		this.size = size;
		this.isAttach = isAttach;
		this.attaches = attaches;
		this.senderId = senderId;
		this.readFlag = readFlag;
	}

	public MailInfo() {
		// TODO Auto-generated constructor stub
	}

}
