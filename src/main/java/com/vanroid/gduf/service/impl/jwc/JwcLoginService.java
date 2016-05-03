package com.vanroid.gduf.service.impl.jwc;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;

import com.vanroid.gduf.dto.JwcInfo;
import com.vanroid.gduf.util.HttpClientUtils;

import freemarker.template.utility.StringUtil;

/**
 * 
 * @ClassName JwcLoginService.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 教务系统的登陆service类
 * 
 * @version 1.0
 */
@Service("loginService")
public class JwcLoginService {

	public String login(HttpSession session, JwcInfo info) {
		CloseableHttpClient httpClient = HttpClientUtils.getHttpClient(session,
				new BasicCookieStore());
		JWCHandler handler = new JWCHandler(httpClient);
		String xm = null;
		// TODO 自动生成的构造函数存根
		try {
			xm = handler.login(info);
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return xm;

	}

	public String login(HttpClient httpClient, JwcInfo info)
			throws ClientProtocolException, IOException {

		JWCHandler handler = new JWCHandler(httpClient);
		String xm = null;
		xm = handler.login(info);
		return xm;
	}

	public boolean login(HttpClient httpClient, String stuId, String jwcPass) {
		JWCHandler handler = new JWCHandler(httpClient);
		JwcInfo info = new JwcInfo();
		info.setXh(stuId);
		info.setPassword(jwcPass);
		String xm = null;
		// TODO 自动生成的构造函数存根
		try {
			xm = handler.login(info);
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return xm == null ? false : true;
	}
}
