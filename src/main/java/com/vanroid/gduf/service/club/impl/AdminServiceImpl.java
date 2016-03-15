package com.vanroid.gduf.service.club.impl;

import com.vanroid.gduf.dao.club.AdminDao;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.service.club.AdminService;

public class AdminServiceImpl implements AdminService {

	public AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/**
	 * 根据帐号密码查找用户
	 */
	@Override
	public Admin getAdmin(Admin admin) {
		return adminDao.getAdmin(admin);
	}

	/**
	 * 根据名称查找用户是否存在
	 */
	@Override
	public Admin findAdminByName(String adminName) {
		// TODO Auto-generated method stub
		return adminDao.findAdminByName(adminName);
	}

	@Override
	public void updateAdmin(Admin existAdmin) {
		adminDao.updateAdmin(existAdmin);

	}

}
