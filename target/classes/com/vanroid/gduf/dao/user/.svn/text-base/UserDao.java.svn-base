package com.vanroid.gduf.dao.user;

import java.util.List;

import com.vanroid.gduf.entity.User;

public interface UserDao {
	/**
	 * 返回自增主键
	 * 
	 * @param user
	 * @return
	 */
	public int add(User user);

	/**
	 * 查看同一电话号码的数量
	 * 
	 * @return
	 */
	public int countByPhone(String phone);

	/**
	 * 通过电话号码加载用户信息
	 * 
	 * @param phone
	 * @return
	 */
	public User loadByPhone(String phone);

	/**
	 * 通过指定属性查找用户
	 * 不带分页
	 * @param user
	 * @return
	 */
	public List<User> findByProp(String[] params, Object... values);

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean update(User user);
}
