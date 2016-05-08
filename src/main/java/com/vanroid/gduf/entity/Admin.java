package com.vanroid.gduf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gd_admin")
public class Admin {
	@Id
	@GeneratedValue
	private int aid;
	private String aAccount;
	private String aPassword;
	private String aName;
	private int status;
	private int aRank;
	private String aQuestion;
	private String aAnswer;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getaAccount() {
		return aAccount;
	}

	public void setaAccount(String aAccount) {
		this.aAccount = aAccount;
	}

	public String getaPassword() {
		return aPassword;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getaRank() {
		return aRank;
	}

	public void setaRank(int aRank) {
		this.aRank = aRank;
	}

	public String getaQuestion() {
		return aQuestion;
	}

	public void setaQuestion(String aQuestion) {
		this.aQuestion = aQuestion;
	}

	public String getaAnswer() {
		return aAnswer;
	}

	public void setaAnswer(String aAnswer) {
		this.aAnswer = aAnswer;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", aAccount=" + aAccount + ", aPassword=" + aPassword + ", aName=" + aName
				+ ", status=" + status + ", aRank=" + aRank + ", aQuestion=" + aQuestion + ", aAnswer=" + aAnswer + "]";
	}

}
