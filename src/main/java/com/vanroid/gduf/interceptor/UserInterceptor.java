package com.vanroid.gduf.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.vanroid.gduf.entity.User;

/**
 * 
 * @ClassName UserInterceptor.java Create on 2015-8-29
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 用户拦截器
 * 
 * @version 1.0
 */
public class UserInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation inv) throws Exception {
		ServletActionContext.getRequest().getSession();
		// 拦截登录
		User qtUser = (User) inv.getInvocationContext().getSession().get("qtUser");
		if (qtUser == null) {
			return "error-noLogin";
		}
		return inv.invoke();
	}

}
