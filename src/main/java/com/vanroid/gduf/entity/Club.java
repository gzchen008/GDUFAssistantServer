package com.vanroid.gduf.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 社团实体类
 * 
 * @author joe
 *
 */
@Entity
@Table(name = "gd_club")
public class Club {
	@Id
	@GeneratedValue
	private int cid;
	private String cName;
	private String cdescribe;
	private Date cdate;
	private String cIcon;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aid", referencedColumnName = "aid")
	private Admin admin;
	@OneToMany(mappedBy = "club")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Message> messages;
	private String clubNum;
	private int status;

	public Club() {
		super();
	}

	public Club(int cid, String cName, String cdescribe, Admin admin) {
		this.cid = cid;
		this.cName = cName;
		this.cdescribe = cdescribe;
		this.admin = admin;
	}

	public Club(int cid, String cName, String cdescribe, Date cdate, String cIcon, Admin admin, String clubNum,
			int status) {
		super();
		this.cid = cid;
		this.cName = cName;
		this.cdescribe = cdescribe;
		this.cdate = cdate;
		this.cIcon = cIcon;
		this.admin = admin;
		this.clubNum = clubNum;
		this.status = status;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getCdescribe() {
		return cdescribe;
	}

	public void setCdescribe(String cdescribe) {
		this.cdescribe = cdescribe;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getcIcon() {
		return cIcon;
	}

	public void setcIcon(String cIcon) {
		this.cIcon = cIcon;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void setClubNum(String clubNum) {
		this.clubNum = clubNum;
	}

	public String getClubNum() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		String clubNum = formater.format(getCdate()) + getCid();
		return clubNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Club [cid=" + cid + ", cName=" + cName + ", cdescribe=" + cdescribe + ", cdate=" + cdate + ", cIcon="
				+ cIcon + ", admin=" + admin + ", clubNum=" + clubNum + ", status=" + status + "]";
	}
}
