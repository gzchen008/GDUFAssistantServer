package com.vanroid.gduf.controller.club;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.constant.ClubConstant;
import com.vanroid.gduf.constant.URLConstant;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.service.club.ClubService;
import com.vanroid.gduf.util.SendMailUtil;

@SuppressWarnings("serial")
public class ClubAction extends ActionSupport {
	// 社团服务接口
	private ClubService clubService;
	// 社团列表
	private List<Club> list = new ArrayList<Club>();
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	private Club club = new Club();
	private Admin admin = new Admin();

	/**
	 * 返回社团管理员注册页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String registerPage() throws Exception {
		return "registerPage";
	}

	/**
	 * 取得所有的社团
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAllClubs() throws Exception {
		list = clubService.getAllClubs();
		return Action.SUCCESS;
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws Exception
	 */

	public String regist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 取得验证码
		String validateCode = request.getParameter("validateCode");
		HttpSession session = request.getSession();
		String autoCode = (String) session.getAttribute("autoCode");
		if (validateCode.equalsIgnoreCase(autoCode)) {
			/**
			 * 将用户信息保存到数据库和发送激活邮件到用户的邮箱中,等待用户激活账户
			 */
			// 设置激活码
			String verifycode = UUID.randomUUID().toString();
			session.setAttribute("verifycode", verifycode);
			// 取得域名
			// String serverName = request.getServerName();
			// 取得端口号
			int port = request.getServerPort();
			// 访问的URL

			String url = request.getScheme() + "://" + URLConstant.HOST + ":"
					+ port + request.getContextPath()
					+ "/club_activate.action?verifycode=" + verifycode
					+ "&eamil=" + admin.getAdmin_name();
			// 实例化发送邮件工具类

			// 添加消息
			StringBuffer content = new StringBuffer();
			content.append("<h1>广金校园通管理员:</h1>");
			content.append("<p>请点击下面连接进行帐号的激活</p>");
			content.append("<a href='" + url + "'>" + url + "</a>");
			// 取得收件人邮箱
			String email = admin.getAdmin_name();
			// 发送邮件
			SendMailUtil.send(email, content.toString());
			// 将用户注册信息保存到数据库并返回管理员实例
			clubService.saveRegiestInfo(admin, club, verifycode);
			addActionMessage("注册成功，请前往邮箱中激活用户");
			return "show";
		} else {
			request.setAttribute("errror", "验证码不正确");
			return "registerPage";
		}
	}

	/**
	 * 激活用户操作
	 * 
	 * @return
	 */
	public String activate() {
		String verifycode = ServletActionContext.getRequest().getParameter(
				"verifycode");
		String club_name = ServletActionContext.getRequest().getParameter(
				"eamil");
		System.out.println("request:验证码" + verifycode);
		System.out.println("request:eamil" + club_name);
		String info = clubService.activateClub(verifycode, club_name);
		if (info.equals(ClubConstant.OPERATION_SUCCESS)) {
			return "activate";
		} else {
			return ERROR;
		}
	}

	/**
	 * getter setter方法
	 * 
	 * @return
	 */
	public List<Club> getList() {
		return list;
	}

	public void setList(List<Club> list) {
		this.list = list;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	/**
	 * 注入Service
	 * 
	 * @param clubService
	 */
	public void setClubService(ClubService clubService) {
		this.clubService = clubService;
	}
}
