package com.vanroid.gduf.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vanroid.gduf.constant.URLConstant;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.service.club.AdminService;
import com.vanroid.gduf.util.SendMailUtil;

@SuppressWarnings("serial")
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

	private Admin admin = new Admin();
	private AdminService adminService;
	private List<String> list = new ArrayList<String>();

	/**
	 * 跳转找回密码页
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public String findPwdPage() throws Exception {
		return "pwdPage";
	}

	/**
	 * 找回密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findPwd() throws Exception {
		// 取得表单中输入的用户注册邮箱
		String admin_name = admin.getAdmin_name();

		System.out.println("-----------------------");
		System.out.println("adminName:" + admin_name);

		// 根据管理员帐号名称查询检测是否存在
		Admin existAdmin = adminService.findAdminByName(admin_name);
		if (existAdmin != null) {
			/**
			 * 创建一份验证找回用户密码的邮件.
			 */
			StringBuffer content = new StringBuffer();
			// 产生随机验证码
			String velidfCode = UUID.randomUUID().toString();
			final String URL = URLConstant.BASE_PATH
					+ "admin_dealPwdPage.action?velidfCode=" + velidfCode
					+ "&email=" + admin_name;
			System.out.println("请求URL:" + URL);
			content.append("<h1>找回管理员密码</h1>");
			content.append("<p>请点击以下连接或者复制链接到浏览器进行找回管理员密码</p>");
			content.append("<a href='" + URL + "'>" + URL + "</a>");
			SendMailUtil.send(admin_name, content.toString());
			existAdmin.setAdmin_validate(velidfCode);
			// 将随机生成的验证码保存到管理员记录中
			adminService.updateAdmin(existAdmin);
			addActionMessage("请前往注册邮箱进行找回密码操作");
			return "show";
		}

		System.out.println("-----------------------");

		addActionMessage("未知错误");
		return ERROR;
	}

	/**
	 * 处理重置密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dealPwdPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String velidfCode = request.getParameter("velidfCode");
		String admin_name = request.getParameter("email");
		Admin existAdmin = adminService.findAdminByName(admin_name);
		if (existAdmin != null) {
			if (velidfCode.equals(existAdmin.getAdmin_validate())) {
				/* adminService.updateAdmin(existAdmin); */
				admin.setAdmin_name(admin_name);
				addActionMessage("重置密码页面");
				return "reset";
			}
		}
		addActionError("dealPwdPage未知错误");
		return ERROR;
	}

	/**
	 * 重置密码后处理
	 * 
	 * @return
	 */
	public String afterReset() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String repassword = request.getParameter("repassword");
		String admin_password = admin.getAdmin_password();
		Admin existAdmin = adminService.findAdminByName(admin.getAdmin_name());
		if (repassword.equals(admin_password)) {
			existAdmin.setAdmin_password(admin_password);
			existAdmin.setAdmin_validate("");
			adminService.updateAdmin(existAdmin);
			addActionMessage("重置密码成功,请到登录页进行登录");
			return "exit";
		}
		addActionError("发生错误,输入密码不一致");
		return ERROR;
	}

	/**
	 * 登录成功
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loginIn() throws Exception {
		Admin existAdmin = adminService.getAdmin(admin);
		if (existAdmin != null) {
			ServletActionContext.getRequest().getSession()
					.setAttribute("admin", existAdmin);
			this.addActionMessage("登录成功");
			return "loginIn";
		} else {
			this.addActionError("登录失败，请检查原因");
			return "loginFail";
		}

	}

	/**
	 * 检查用户是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkAdmin() throws Exception {
		String adminName = ServletActionContext.getRequest().getParameter(
				"admin_name");
		Admin existAdmin = adminService.findAdminByName(adminName);
		if (existAdmin != null) {
			list.add(existAdmin.getAdmin_name());
		}
		return SUCCESS;
	}

	/**
	 * 返回首页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String backToIndex() throws Exception {
		return "loginIn";
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exit() throws Exception {

		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("admin");
		session.invalidate();
		return "exit";
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}

}
