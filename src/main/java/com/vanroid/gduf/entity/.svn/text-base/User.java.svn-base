package com.vanroid.gduf.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName User.java Create on 2015-8-28
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 用户实体
 * 
 * @version 1.0
 */
@Entity
@Table(name = "gd_user")
public class User implements Serializable {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 学号
	 */
	private String stuId;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 确认密码
	 */
	private String comfirmPassword;
	/**
	 * 教务系统密码
	 */
	private String jwcPass;
	/**
	 * 校内邮箱密码
	 * 
	 */
	private String xnMailPass;
	/**
	 * 图书管密码
	 */
	private String libaryPass;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 注册时间
	 */
	private Date registDate;
	/**
	 * 登录次数
	 */
	private int accessTimes;
	/**
	 * 系别
	 */
	private String depart;
	/**
	 * 专业
	 */
	private String marjor;
	/**
	 * 电话
	 */
	private String telphone;
	/**
	 * 班级号
	 */
	private String classId;
	/**
	 * 用户状态 0表示没有认证 1表示已经认证
	 */
	@Column(length=5,columnDefinition="int(5) default 0")
	private int status;
	/**
	 * 头像地址
	 */
	private String headImg;
	/**
	 * 性别
	 * @return
	 */
	private String sex;
	
	
	

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getComfirmPassword() {
		return comfirmPassword;
	}

	public void setComfirmPassword(String comfirmPassword) {
		this.comfirmPassword = comfirmPassword;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJwcPass() {
		return jwcPass;
	}

	public void setJwcPass(String jwcPass) {
		this.jwcPass = jwcPass;
	}

	public String getXnMailPass() {
		return xnMailPass;
	}

	public void setXnMailPass(String xnMailPass) {
		this.xnMailPass = xnMailPass;
	}

	public String getLibaryPass() {
		return libaryPass;
	}

	public void setLibaryPass(String libaryPass) {
		this.libaryPass = libaryPass;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getAccessTimes() {
		return accessTimes;
	}

	public void setAccessTimes(int accessTimes) {
		this.accessTimes = accessTimes;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getMarjor() {
		return marjor;
	}

	public void setMarjor(String marjor) {
		this.marjor = marjor;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", stuId=" + stuId + ", password=" + password
				+ ", comfirmPassword=" + comfirmPassword + ", jwcPass="
				+ jwcPass + ", xnMailPass=" + xnMailPass + ", libaryPass="
				+ libaryPass + ", realName=" + realName + ", registDate="
				+ registDate + ", accessTimes=" + accessTimes + ", depart="
				+ depart + ", marjor=" + marjor + ", telphone=" + telphone
				+ ", classId=" + classId + ", status=" + status + ", headImg="
				+ headImg + ", sex=" + sex + "]";
	}

	


}
