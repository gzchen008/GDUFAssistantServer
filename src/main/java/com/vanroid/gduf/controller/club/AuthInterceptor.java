package com.vanroid.gduf.controller.club;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.vanroid.gduf.entity.Admin;

/**
 * 登录权限拦截器,校验用户是否已经登录,没有登录跳转回登录页,适用于社团动态,兼职就业,失物招领
 * 
 * @author joe
 *
 */
public class AuthInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Map<String, Object> session = ctx.getSession();
		Admin admin = (Admin) session.get("admin");
		if (admin == null) {
			session.put("tip", "你还没登录,请先登录系统");
			return Action.INPUT;
		} else {
			return invocation.invoke();
		}
	}

}
