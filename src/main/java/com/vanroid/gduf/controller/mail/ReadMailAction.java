package com.vanroid.gduf.controller.mail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.entity.MailInfo;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.mail.MailService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 读邮件控制器
 * 
 * @author CGZ
 *
 */
@Controller("ReadMailAction")
@Scope("prototype")
public class ReadMailAction extends ActionSupport {
	/**
	 * 邮件地址
	 */
	private String id;
	/**
	 * 邮件服务
	 */
	@Resource
	private MailService mailService;
	/**
	 * 邮件，返回值
	 */
	private MailInfo mail;

	/**
	 * HttpClient
	 */
	private HttpClient httpClient;

	public ReadMailAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		httpClient = HttpClientUtils.getHttpClient(request);
	}

	/**
	 * 读邮件方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String readMail() throws Exception {
		mail = mailService.getMail(httpClient, id);
		return Action.SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MailInfo getMail() {
		return mail;
	}

	public void setMail(MailInfo mail) {
		this.mail = mail;
	}

}
