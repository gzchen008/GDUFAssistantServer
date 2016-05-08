package com.vanroid.gduf.controller.club;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterception extends AbstractInterceptor {

	private static final long serialVersionUID = 620344151936475003L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext act = invocation.getInvocationContext();
		Map<String, Object> session = act.getSession();
		Object admin = session.get("admin");
		if (admin != null)
			return invocation.invoke();
		act.put("info", "你还没有登陆");
		return Action.INPUT;

	}

}
