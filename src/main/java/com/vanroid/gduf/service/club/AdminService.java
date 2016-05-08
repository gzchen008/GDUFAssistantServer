package com.vanroid.gduf.service.club;

import java.util.List;

import com.vanroid.gduf.entity.Admin;

public interface AdminService {
	/**
	 * 判断账号是否已经登陆
	 * 
	 * @param acount
	 * @param apwd
	 * @return
	 */
	public boolean isLogin(String acount, String apwd);

	/**
	 * 根据账号获取admin信息
	 * 
	 * @param account
	 * @return
	 */
	public Admin getAdminByAccount(String account);

	public List<Admin> getAllAdmin();

	public Admin getAdminById(int id);
}
