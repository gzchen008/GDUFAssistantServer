package com.vanroid.gduf.interceptor;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.impl.jwc.JwcLoginService;
import com.vanroid.gduf.service.mail.MailService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 
 * @ClassName JwcLoginService.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 教务系统的登陆拦截器
 * 
 * @version 1.0
 */
public class JWCInterceptor extends MethodFilterInterceptor {
	@Resource
	private JwcLoginService jwcLoginService;
	private Map<String, Object> resultMap;

	@Override
	protected String doIntercept(ActionInvocation inv) throws Exception {
		// 拦截登录
		HttpSession session = ServletActionContext.getRequest().getSession();
		User qtUser = (User) session.getAttribute("qtUser");
		if (StringUtils.isEmpty(qtUser.getStuId())
				|| StringUtils.isEmpty(qtUser.getJwcPass())) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("resultCode", -1);
			resultMap.put("msg", "未绑定教务系统");
			ServletActionContext.getRequest().setAttribute("errorResultMap",
					resultMap);
			return "error-noBond";
		} else {
			CloseableHttpClient httpClient = HttpClientUtils.getHttpClient(
					session, null);
			boolean isLoginSuccess = false;
			try {
				isLoginSuccess = jwcLoginService.login(httpClient,
						qtUser.getStuId(), qtUser.getJwcPass());

			} catch (Exception e) {
				e.printStackTrace();
				resultMap = new HashMap<String, Object>();
				resultMap.put("resultCode", -99);
				resultMap.put("msg", "教务系统无法访问");
				ServletActionContext.getRequest().setAttribute(
						"errorResultMap", resultMap);
				return "error-accessError";
			}
			if (!isLoginSuccess) {
				resultMap = new HashMap<String, Object>();
				resultMap.put("resultCode", -2);
				resultMap.put("msg", "教务系统密码不正确或者连接超时");
				ServletActionContext.getRequest().setAttribute(
						"errorResultMap", resultMap);
				return "error-noBond";
			}
		}
		return inv.invoke();
	}

	public void setJwcLoginService(JwcLoginService jwcLoginService) {
		this.jwcLoginService = jwcLoginService;
	}
}
