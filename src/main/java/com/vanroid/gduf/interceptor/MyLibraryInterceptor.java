package com.vanroid.gduf.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.vanroid.gduf.entity.LibraryUserInfo;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.exception.ValidateErrorException;
import com.vanroid.gduf.service.library.MyLibraryService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 
 * @ClassName MailInterceptor.java Create on 2015-8-29
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 我的图书馆拦截器
 * 
 * @version 1.0
 */
public class MyLibraryInterceptor extends MethodFilterInterceptor {
	@Resource
	private MyLibraryService myLibraryService;
	private Map<String, Object> resultMap;

	@Override
	protected String doIntercept(ActionInvocation inv) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 拦截登录
		HttpSession session = request.getSession();
		User qtUser = (User) session.getAttribute("qtUser");
		LibraryUserInfo libraryUserInfo = null;
		if (StringUtils.isEmpty(qtUser.getStuId()) || StringUtils.isEmpty(qtUser.getLibaryPass())) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("resultCode", -1);
			resultMap.put("msg", "未绑定图书馆系统");
			return "error-noBond";
		} else {
			HttpClient httpClient = HttpClientUtils.getHttpClient(request);
			try {
				libraryUserInfo = myLibraryService.login(httpClient, qtUser.getStuId(), qtUser.getLibaryPass());
				if (libraryUserInfo == null) {
					resultMap.put("resultCode", -2);
					resultMap.put("msg", "图书馆系统密码错误");
					return "error-noBond";
				} else {
					// 登录成功
					session.setAttribute("libraryUserInfo", libraryUserInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultMap = new HashMap<String, Object>();
				resultMap.put("resultCode", -99);
				resultMap.put("msg", "校内邮箱无法访问");
				return "error-accessError";
			}
		}

		ServletActionContext.getRequest().setAttribute("errorResultMap", resultMap);
		return inv.invoke();
	}

}
