package com.vanroid.gduf.service.user;

import java.util.Map;

import com.vanroid.gduf.entity.User;


/**
 * 
* @ClassName UserService.java Create on 2015-8-28     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author CGZ vsjosonadmin@163.com  
*  
* @Description:  用户模块逻辑
*
* @version 1.0
 */
public interface UserService {
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user);

	/**
	 * 登录验证
	 * @param user
	 * @return
	 */
	public User loginCheck(User user);

	/**
	 * 仅使用电话号码进行注册
	 * @param user
	 * @return
	 */
	public Map<String,Object> registerByPhone(User user);

	/**
	 * 更新用户信息
	 * @param qtUser
	 */
	public boolean update(User qtUser);
	
	

}
