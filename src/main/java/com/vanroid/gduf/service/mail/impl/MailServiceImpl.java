package com.vanroid.gduf.service.mail.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vanroid.gduf.common.GdufLinks;
import com.vanroid.gduf.entity.MailInfo;
import com.vanroid.gduf.service.mail.MailService;
import com.vanroid.gduf.service.mail.html.MailHTML;
import com.vanroid.gduf.util.HttpUtil;

/**
 * 
 * @ClassName MailServiceImpl.java Create on 2015-8-29
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 邮件服务实现类，非单例
 * 
 * @version 1.0
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

	/**
	 * 登录校内邮箱方法
	 * 
	 * @param stuId
	 * @param xnMailPass
	 * @return
	 */
	@Override
	public boolean login(HttpClient httpClient,String stuId, String xnMailPass) {
		// 登录校内邮箱
		// 登录链接
		String target = GdufLinks.CHECK_USER;
		HttpPost httpRequest = new HttpPost(target);
		// 将要传递的值放入List集合中
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", stuId));
		params.add(new BasicNameValuePair("password", xnMailPass));
		// 服务器返回信息
		String result = null;
		try {
			// 设置编码方式
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 有HTTP响应
				// 读取返回的数据
				result = EntityUtils.toString(httpResponse.getEntity());
			}else {
				throw new Exception("学校系统连接错误");
			}
			if (result.contains("密码不正确") || result.contains("用户名不正确")) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	/**
	 * 通过页码获取邮件列表
	 * 
	 * @param page
	 * @return
	 */
	@Override
	public List<MailInfo> getMails(HttpClient httpClient,int page) {
		// 邮件列表
		List<MailInfo> mailList = null;
		String url = "" + GdufLinks.GET_PAGE_BY_INDEX + page;
		// 获得GET请求
		HttpGet httpRequest = HttpUtil.getHttpGet(httpClient, url);
		HttpResponse httpResponse;
		try {
			httpResponse = HttpUtil.getResponse(httpClient, httpRequest);

			if (HttpUtil.isOK(httpResponse)) {
				String resultStr = HttpUtil.getResultString(httpResponse);
				// 新建解析HTML
				MailHTML mailHTML = new MailHTML();
				mailList = mailHTML.getMailList(resultStr);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mailList;
	}

	/**
	 * 获取邮件方法
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public MailInfo getMail(HttpClient httpClient,String id) {
		// 邮件
		MailInfo mail = new MailInfo();
		mail.setId(id);
		String url = GdufLinks.READ_HTML_MAILBYID + id;
		// 获得GET请求
		HttpGet httpRequest = HttpUtil.getHttpGet(httpClient, url);
		HttpResponse httpResponse;
		try {
			httpResponse = HttpUtil.getResponse(httpClient, httpRequest);

			if (HttpUtil.isOK(httpResponse)) {
				String content = HttpUtil.getResultString(httpResponse);
				content = new String(content.getBytes("gb2312"));
				// 获取邮件内容
				mail.setContent(content);
				// 获取邮件附件地址
				url = GdufLinks.READ_MAILBYID + id;
				String resultStr = HttpUtil.getResultString(HttpUtil
						.getResponse(httpClient,
								HttpUtil.getHttpGet(httpClient, url)));
				// 新建解析HTML
				MailHTML mailHTML = new MailHTML();
				mail.setAttaches(mailHTML.getAttaches(resultStr));

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mail;

	}

	
}
