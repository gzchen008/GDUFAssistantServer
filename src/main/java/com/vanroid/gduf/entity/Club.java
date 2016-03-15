package com.vanroid.gduf.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 社团类型实体类
 * 
 * @author Joe_Huang
 * 
 */
@Entity
@Table(name = "gd_club")
public class Club {

	private Integer id;
	private String club_name;
	private String club_summary;
	private String club_photo;
	private Integer club_state;
	private Date club_setup;
	private Admin admin;
	private String club_verification;

	public String getClub_verification() {
		return club_verification;
	}

	public void setClub_verification(String club_verification) {
		this.club_verification = club_verification;
	}

	@OneToOne(cascade = CascadeType.ALL, targetEntity = Admin.class)
	@JoinColumn(name = "admin_id", nullable = false, updatable = false)
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	private Set<ClubMessage> messageSet = new HashSet<ClubMessage>();

	public Club() {

	}

	public Club(String club_name, String club_summary, String club_photo,
			Integer club_state, Date club_setup) {
		this.club_name = club_name;
		this.club_summary = club_summary;
		this.club_photo = club_photo;
		this.club_state = club_state;
		this.club_setup = club_setup;
	}

	@OneToMany(mappedBy = "club")
	@LazyCollection(LazyCollectionOption.FALSE)
	public Set<ClubMessage> getMessageSet() {
		return messageSet;
	}

	public void setMessageSet(Set<ClubMessage> messageSet) {
		this.messageSet = messageSet;
	}

	public Date getClub_setup() {
		return club_setup;
	}

	public void setClub_setup(Date club_setup) {
		this.club_setup = club_setup;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClub_name() {
		return club_name;
	}

	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}

	public String getClub_summary() {
		return club_summary;
	}

	public void setClub_summary(String club_summary) {
		this.club_summary = club_summary;
	}

	public String getClub_photo() {
		return club_photo;
	}

	public void setClub_photo(String club_photo) {
		this.club_photo = club_photo;
	}

	public Integer getClub_state() {
		return club_state;
	}

	public void setClub_state(Integer club_state) {
		this.club_state = club_state;
	}
}
