package com.vanroid.gduf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gd_admin")
public class Admin {

	private Integer id;
	private String admin_name;
	private String admin_password;
	private int admin_rank;

	private String admin_validate;

	public Admin() {
	}

	public Admin(String admin_name, String admin_password, int admin_rank) {
		this.admin_name = admin_name;
		this.admin_password = admin_password;
		this.admin_rank = admin_rank;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public int getAdmin_rank() {
		return admin_rank;
	}

	public void setAdmin_rank(int admin_rank) {
		this.admin_rank = admin_rank;
	}

	public String getAdmin_validate() {
		return admin_validate;
	}

	public void setAdmin_validate(String admin_validate) {
		this.admin_validate = admin_validate;
	}

}
