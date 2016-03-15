package com.vanroid.gduf.service.club;

import com.vanroid.gduf.entity.Admin;

public interface AdminService {
	/**
	 * 校验管理员账号是否存在
	 * 
	 * @param admin
	 * @return Admin
	 */
	public Admin getAdmin(Admin admin);

	/**
	 * 根据管理员帐号名查找管理员是否存在
	 * 
	 * @param adminName
	 * @return
	 */
	public Admin findAdminByName(String adminName);

	/**
	 * 更新用户
	 * 
	 * @param admin
	 */
	public void updateAdmin(Admin existAdmin);
}
