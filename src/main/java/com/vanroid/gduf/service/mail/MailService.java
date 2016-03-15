package com.vanroid.gduf.service.mail;

import java.util.List;

import org.apache.http.client.HttpClient;

import com.vanroid.gduf.entity.MailInfo;
/**
 * 
* @ClassName MailService.java Create on 2015-8-29     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author CGZ vsjosonadmin@163.com  
*  
* @Description:   邮件服务
*
* @version 1.0
 */
public interface MailService {
	/**
	 * 获取邮件内容
	 * @param httpClient 用户独享Client
	 * @param id 邮件Id
	 * @return 邮件数据
	 */
	public MailInfo getMail(HttpClient httpClient,String id);
	/**
	 * 获取邮件列表
	 * @param httpClient
	 * @param page 页码
	 * @return
	 */
	public List<MailInfo> getMails(HttpClient httpClient,int page);
	/**
	 * 登录方法
	 * @param httpClient
	 * @param stuId
	 * @param xnMailPass
	 * @return  true 成功，false 失败
	 */
	public boolean login(HttpClient httpClient,String stuId, String xnMailPass);
}
