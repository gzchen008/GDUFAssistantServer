package com.vanroid.gduf.interceptor;

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
import com.vanroid.gduf.service.mail.MailService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 
 * @ClassName MailInterceptor.java Create on 2015-8-29
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 邮件拦截器
 * 
 * @version 1.0
 */
public class MailInterceptor extends MethodFilterInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private MailService mailService;
	private Map<String, Object> resultMap;

	@Override
	protected String doIntercept(ActionInvocation inv) throws Exception {
		// 拦截登录
		HttpSession session = ServletActionContext.getRequest().getSession();
		User qtUser = (User) session.getAttribute("qtUser");
		if (StringUtils.isEmpty(qtUser.getStuId()) || StringUtils.isEmpty(qtUser.getXnMailPass())) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("resultCode", -1);
			resultMap.put("msg", "未绑定校内邮箱");
			ServletActionContext.getRequest().setAttribute("errorResultMap", resultMap);
			return "error-noBond";
		} else {
			CloseableHttpClient httpClient = HttpClientUtils.getHttpClient(session, null);
			boolean mailRes = false;
			try {
				mailRes = mailService.login(httpClient, qtUser.getStuId(), qtUser.getXnMailPass());
			} catch (Exception e) {
				e.printStackTrace();
				resultMap = new HashMap<String, Object>();
				resultMap.put("resultCode", -99);
				resultMap.put("msg", "校内邮箱无法访问");
				ServletActionContext.getRequest().setAttribute("errorResultMap", resultMap);
				return "error-accessError";
			}
			if (!mailRes) {
				resultMap = new HashMap<String, Object>();
				resultMap.put("resultCode", -2);
				resultMap.put("msg", "校内邮箱密码不正确");
				ServletActionContext.getRequest().setAttribute("errorResultMap", resultMap);
				return "error-noBond";
			}
		}
		return inv.invoke();
	}

}
