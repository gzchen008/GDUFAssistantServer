package com.vanroid.gduf.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vanroid.gduf.dto.JwcInfo;
import com.vanroid.gduf.entity.LibraryUserInfo;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.impl.jwc.JWCHandler;
import com.vanroid.gduf.service.impl.jwc.JwcLoginService;
import com.vanroid.gduf.service.library.MyLibraryService;
import com.vanroid.gduf.service.mail.MailService;
import com.vanroid.gduf.service.user.UserService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 
 * @ClassName BindUserAction.java Create on 2015-8-29
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 绑定学校帐户
 * 
 * @version 1.0
 */
@Controller("BindUserAction")
@Scope("prototype")
public class BindUserAction extends ActionSupport implements ServletRequestAware, ModelDriven<User> {
	@Resource
	private MyLibraryService myLibraryService;
	@Resource(name = "loginService")
	private JwcLoginService jwcLoginService;
	@Resource
	private MailService mailService;
	@Resource
	private UserService userService;
	/**
	 * 用户HttpClient
	 */
	private HttpClient httpClient;
	private Map<String, Object> resultMap;
	/**
	 * 接收绑定表单
	 */
	private User userForm;

	public String bindUser() throws Exception {
		// 用户登录信息
		User qtUser = (User) ActionContext.getContext().getSession().get("qtUser");
		if (qtUser == null) {
			return "noLogin";
		}

		resultMap = new HashMap<String, Object>();

		if (userForm.getStuId() == null || userForm.getJwcPass() == null || userForm.getLibaryPass() == null
				|| userForm.getXnMailPass() == null) {
			resultMap.put("resultCode", -1);
			resultMap.put("msg", "学号和学校各密码不能为空！");
			return SUCCESS;
		}

		String msg = "";
		int resultCode = 1;
		// 验证图书馆帐户
		LibraryUserInfo libUser = myLibraryService.login(httpClient, userForm.getStuId(), userForm.getLibaryPass());
		// 验证教务系统
		JwcInfo jwcInfo = new JwcInfo();
		jwcInfo.setXh(userForm.getStuId());
		jwcInfo.setPassword(userForm.getJwcPass());
		String jwcRes = jwcLoginService.login(httpClient, jwcInfo);
		// 验证校内邮箱
		boolean mailRes = mailService.login(httpClient, userForm.getStuId(), userForm.getXnMailPass());
		if (libUser == null) {
			msg += "图书馆密码错误！";
			resultCode = -2;
		}
		if (jwcRes == null || jwcRes.equals("")) {
			msg += "教务系统密码错误！";
			resultCode = -3;
		}
		if (!mailRes) {
			msg += "校内邮箱密码错误！";
			resultCode = -4;
		}
		if (resultCode == 1) {
			msg = "验证成功";
			// 获取用户信息
			User userInfo = new JWCHandler(httpClient).getUserInfo(jwcInfo.getXh(), jwcRes);
			qtUser.setClassId(userInfo.getClassId());
			qtUser.setSex(userInfo.getSex());
			qtUser.setDepart(userInfo.getDepart());
			qtUser.setMarjor(userInfo.getMarjor());
			qtUser.setStuId(userForm.getStuId());
			qtUser.setJwcPass(userForm.getJwcPass());
			qtUser.setXnMailPass(userForm.getXnMailPass());
			qtUser.setLibaryPass(userForm.getLibaryPass());
			qtUser.setStatus(1); // 已绑定

			userService.update(qtUser);
		}

		resultMap.put("resultCode", resultCode);
		resultMap.put("msg", msg);

		return "success";
	}

	@Override
	public User getModel() {
		if (userForm == null) {
			userForm = new User();
		}
		return userForm;
	}

	public User getUserForm() {
		return userForm;
	}

	public void setUserForm(User userForm) {
		this.userForm = userForm;
	}

	public MyLibraryService getMyLibraryService() {
		return myLibraryService;
	}

	public void setMyLibraryService(MyLibraryService myLibraryService) {
		this.myLibraryService = myLibraryService;
	}

	public JwcLoginService getJwcLoginService() {
		return jwcLoginService;
	}

	public void setJwcLoginService(JwcLoginService jwcLoginService) {
		this.jwcLoginService = jwcLoginService;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		httpClient = HttpClientUtils.getHttpClient(session, null);
	}

}
