package com.vanroid.gduf.controller.mail;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.entity.MailInfo;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.exception.ValidateErrorException;
import com.vanroid.gduf.service.mail.MailService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 邮件控制器
 * 
 * @author CGZ
 * 
 */
@Controller("MailAction")
@Scope("prototype")
public class MailAction extends ActionSupport {
	/**
	 * 邮件服务对象
	 * 
	 */
	@Resource
	private MailService mailService;
	/**
	 * 返回值列表
	 */
	private List<MailInfo> mailList;

	/**
	 * 查找某页的邮件
	 */
	public String getPageMails() {
		// 获取页码
		HttpServletRequest request = ServletActionContext.getRequest();
		String page = request.getParameter("page");
		if (page == null || page.equals("")) {
			page = "1";
		}
		// 获取session
		Map session = ActionContext.getContext().getSession();
		// 从session中取出user
		User user = (User) session.get("qtUser");

		CloseableHttpClient httpClient = HttpClientUtils.getHttpClient(
				request.getSession(), new BasicCookieStore());
		// 使用user中的用户名密码登录
		boolean isLogin = mailService.login(httpClient, user.getStuId(),
				user.getXnMailPass());
		if(!isLogin){
			throw new ValidateErrorException("校内邮箱密码错误！");
		}
		if (isLogin) {
			mailList = mailService.getMails(httpClient, Integer.parseInt(page));
		}
		return Action.SUCCESS;

	}

	public List<MailInfo> getMailList() {
		return mailList;
	}
	
	public void setMailList(List<MailInfo> mailList) {
		this.mailList = mailList;
	}

}
