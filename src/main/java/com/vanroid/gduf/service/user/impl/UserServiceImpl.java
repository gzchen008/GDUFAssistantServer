package com.vanroid.gduf.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.dao.user.UserDao;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.user.UserService;
import com.vanroid.gduf.util.MD5Util;

/**
 * 
 * @ClassName UserServiceImpl.java Create on 2015-8-28
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 
 * 
 * @version 1.0
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Resource
	private UserDao userDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public User loginCheck(User user) {
		if(user == null){
			return null;
		}
		// 加密
		user.setPassword(MD5Util.MD5(user.getPassword()));
		// 通过手机号，
		List<User> ls = userDao.findByProp(new String[] { "telphone",
				"password" }, user.getTelphone(), user.getPassword());
		return ls.size() == 1 ? ls.get(0) : null;
	}

	
	@Override
	@Transactional
	public Map<String, Object> registerByPhone(User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (user == null || user.getPassword() == null) {
			resultMap.put("resultCode", 0);
			resultMap.put("msg", "用户名，密码不能为空");
			return resultMap;
		}
		if (!user.getPassword().equals(user.getComfirmPassword())) {
			resultMap.put("resultCode", 1);
			resultMap.put("msg", "两次密码不一致");
			return resultMap;
		}
		try {
			// 验证电话号码是否被使用
			int count = userDao.countByPhone(user.getTelphone());
			if (count != 0) {
				resultMap.put("resultCode", 2);
				resultMap.put("msg", "该手机号已被使用，请联系管理员");
			} else {
				// 加密密码
				user.setPassword(MD5Util.MD5(user.getPassword()));
				user.setRegistDate(new Date());
				// 添加用户
				Integer userId = userDao.add(user);
				if(userId == null)
					throw new Exception("注册失败");
				resultMap.put("resultCode", 3);
				resultMap.put("msg", "注册成功！");
				logger.debug("register:" + user.getTelphone());
			}
		} catch (Exception e) {
			resultMap.put("resultCode", 4);
			resultMap.put("msg", "服务器内部错误");
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}

}
