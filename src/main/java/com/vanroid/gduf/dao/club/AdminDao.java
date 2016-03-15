package com.vanroid.gduf.dao.club;

import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.entity.Admin;

@Transactional
public interface AdminDao {
	/**
	 * 查询数据库是否存在这个管理员的信息
	 * 
	 * @param admin
	 * @return admin
	 */
	public Admin getAdmin(Admin admin);

	/**
	 * 保存社团管理员信息到数据库
	 * 
	 * @param admin
	 */
	public void saveAdmin(Admin admin);

	/**
	 * 根据管理员名称返回管理员实例
	 * 
	 * @param admin_name
	 * @return
	 */
	public Admin findAdminByName(String admin_name);

	/**
	 * 更新管理员
	 * 
	 * @param existAdmin
	 */
	public void updateAdmin(Admin existAdmin);
}
