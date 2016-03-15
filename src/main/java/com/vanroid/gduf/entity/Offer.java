package com.vanroid.gduf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 兼职就业实体类
 * 
 * @author Joe_Huang
 * 
 */
@Entity
@Table(name = "gd_offer")
public class Offer {
	// 编号
	private Integer id;
	// 发表时间
	private Date offer_publishTime;
	// 标题
	private String offer_title;
	// 内容
	private String offer_content;
	// 多张图片，最多三张
	private String offer_photo1;
	private String offer_photo2;
	private String offer_photo3;

	public Offer() {

	}

	public Offer(Date offer_publishTime, String offer_title,
			String offer_content, String offer_photo1, String offer_photo2,
			String offer_photo3) {

		this.offer_publishTime = offer_publishTime;
		this.offer_title = offer_title;
		this.offer_content = offer_content;
		this.offer_photo1 = offer_photo1;
		this.offer_photo2 = offer_photo2;
		this.offer_photo3 = offer_photo3;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getOffer_publishTime() {
		return offer_publishTime;
	}

	public void setOffer_publishTime(Date offer_publishTime) {
		this.offer_publishTime = offer_publishTime;
	}

	public String getOffer_title() {
		return offer_title;
	}

	public void setOffer_title(String offer_title) {
		this.offer_title = offer_title;
	}

	public String getOffer_content() {
		return offer_content;
	}

	public void setOffer_content(String offer_content) {
		this.offer_content = offer_content;
	}

	public String getOffer_photo1() {
		return offer_photo1;
	}

	public void setOffer_photo1(String offer_photo1) {
		this.offer_photo1 = offer_photo1;
	}

	public String getOffer_photo2() {
		return offer_photo2;
	}

	public void setOffer_photo2(String offer_photo2) {
		this.offer_photo2 = offer_photo2;
	}

	public String getOffer_photo3() {
		return offer_photo3;
	}

	public void setOffer_photo3(String offer_photo3) {
		this.offer_photo3 = offer_photo3;
	}

}
