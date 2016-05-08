package com.vanroid.gduf.service.club.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.dao.club.AdminDao;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.service.club.AdminService;
import com.vanroid.gduf.util.MD5Util;

@Service("adminService")
public class AdminServiceImp implements AdminService {

	private AdminDao adminDao;

	public AdminDao getAdminDao() {
		return adminDao;
	}

	@Resource
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Transactional
	@Override
	public boolean isLogin(String account, String apwd) {
		Admin admin = adminDao.getAdminByAccount(account);
		System.out.println("密码:" + MD5Util.MD5(admin.getaPassword()));
		if (admin != null) {
			if (admin.getaAccount().equals(account) && admin.getaPassword().equals(MD5Util.MD5(apwd)))
				return true;
		}
		return false;
	}

	@Transactional
	@Override
	public Admin getAdminByAccount(String account) {
		Admin admin = null;
		admin = adminDao.getAdminByAccount(account);
		return admin;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Admin> getAllAdmin() {
		return adminDao.getAllAdmin();
	}

	@Transactional(readOnly = true)
	@Override
	public Admin getAdminById(int id) {
		return adminDao.getAdminById(id);
	}

}
