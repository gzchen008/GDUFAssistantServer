package com.vanroid.gduf.controller.club;

import java.util.Map;

import javax.annotation.Resource;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.service.club.AdminService;

@SuppressWarnings("serial")
@Controller("AdminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements RequestAware, SessionAware {
	private Map<String, Object> request;
	private Map<String, Object> session;
	@Resource
	private AdminService adminService;

	private String account;
	private String apwd;

	/**
	 * 跳转登陆页
	 * 
	 * @return
	 */
	public String loginPage() {
		return INPUT;
	}

	/**
	 * 登陆操作
	 * 
	 * @return
	 */
	public String login() {
		System.out.println("账号:" + getAccount());
		System.out.println("密码：" + getApwd());
		if (getAccount() == null || getApwd() == null || "".equals(getAccount()) || "".equals(getApwd())) {
			request.put("info", "账号或密码不能为空");
			return INPUT;
		} else {
			boolean isLogin = adminService.isLogin(getAccount(), getApwd());
			if (isLogin) {
				Admin admin = adminService.getAdminByAccount(getAccount());
				session.put("admin", admin);
				return SUCCESS;
			} else {
				request.put("info", "账号或密码不正确");
				return INPUT;
			}
		}
	}

	/**
	 * 用户注销操作
	 * 
	 * @return
	 */
	public String logout() {
		session.clear();
		Admin admin = (Admin) session.get("admin");
		System.out.println("admin是否已经注销:" + admin == null ? true : false);
		return INPUT;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getApwd() {
		return apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

}
