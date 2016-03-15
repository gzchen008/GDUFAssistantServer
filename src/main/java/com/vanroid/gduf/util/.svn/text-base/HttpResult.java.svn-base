package com.vanroid.gduf.util;

import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 
 * @ClassName HttpResult.java Create on 2015-8-28
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: HTTP请求返回的内容
 * 
 * @version 1.0
 */
public class HttpResult {
	private CloseableHttpClient httpClient;

	private List<Cookie> cookies;

	private HttpEntity httpEntity;

	private HashMap<String, Header> headerAll;

	/**
	 * 状态值
	 */
	private int statusCode;

	/**
	 * HTML内容
	 */
	private String body;

	public List<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}

	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setHeaders(Header[] headers) {
		headerAll = new HashMap<String, Header>();
		for (Header header : headers) {
			headerAll.put(header.getName(), header);
		}
	}

	public HashMap<String, Header> getHeaderAll() {
		return headerAll;
	}

	public void setHttpEntity(HttpEntity entity) {
		this.httpEntity = entity;
	}

	public HttpEntity getHttpEntity() {
		return httpEntity;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
