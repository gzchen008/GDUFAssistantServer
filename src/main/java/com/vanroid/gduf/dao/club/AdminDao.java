package com.vanroid.gduf.dao.club;

import java.util.List;

import com.vanroid.gduf.entity.Admin;

public interface AdminDao {
	public Admin getAdminByAccount(String account);

	public List<Admin> getAllAdmin();

	public Admin getAdminById(int id);
}
