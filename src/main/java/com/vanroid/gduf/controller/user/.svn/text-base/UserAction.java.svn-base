package com.vanroid.gduf.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.user.UserService;

/**
 * 
 * @ClassName UserAction.java Create on 2015-8-29
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 用户控制器
 * 
 * @version 1.0
 */
@Controller("UserAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private static final Logger logger = LoggerFactory
			.getLogger(UserAction.class);
	/**
	 * 接收用户表单
	 */
	private User user;
	/**
	 * 用户service
	 */
	@Resource
	private UserService userService;
	/**
	 * json返回值
	 */
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	/**
	 * 登录方法
	 * 
	 * @return
	 */
	public String login() throws Exception {
		try {
			User loginedUser = userService.loginCheck(user); // 检查是否登录成功
			if (loginedUser != null) { // 登录成功
				// 把用户存在session中
				ActionContext.getContext().getSession()
						.put("qtUser", loginedUser);
				resultMap.put("resultCode", 1);
				resultMap.put("msg", "登录成功");
				logger.debug(user.getTelphone() + "已登录");
			} else {
				resultMap.put("resultCode", 0);
				resultMap.put("msg", "用户名或密码错误");

			}
		} catch (Exception e) {
			resultMap.put("resultCode", 3);
			resultMap.put("msg", "服务器内部错误");
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 注册方法
	 */
	public String register() {

		resultMap = userService.registerByPhone(user);
		// 登录，存入session
		ActionContext.getContext().getSession().put("qtUser", user);
		return "success";
	}

	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		resultMap.put("resultCode", 1);
		resultMap.put("msg", "退出成功");
		return "success";
	}
	/**
	 * 显示用户信息
	 * @return
	 */
	public String userInfo(){
		resultMap.put("resultMap", "1");
		User userInfo = (User) ActionContext.getContext().getSession().get("qtUser");
		resultMap.put("userInfo", userInfo);
		return "success";
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getModel() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

}
