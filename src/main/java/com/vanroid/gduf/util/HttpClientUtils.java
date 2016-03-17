package com.vanroid.gduf.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @ClassName HttpClientUtils.java Create on 2015-8-28
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: HTTPClient 工具类
 * 
 * @version 1.0
 */
public class HttpClientUtils {

	/**
	 * 通过用户session获取专属HTTPClient
	 * 
	 * @param session
	 * @return
	 */
	public static CloseableHttpClient getHttpClient(HttpSession session,
			BasicCookieStore cookieStore) {
		if (session == null) {
			return createDefaultHttpClient(cookieStore);
		}
		CloseableHttpClient myHttpClient = (CloseableHttpClient) session
				.getAttribute("myHttpClient");
		if (myHttpClient == null) {
			myHttpClient = createDefaultHttpClient(cookieStore);
			// 存入session中
			session.setAttribute("myHttpClient", myHttpClient);
		}
		return myHttpClient;

	}

	/**
	 * 创建一个默认的HttpCLient
	 * 
	 * @return
	 */
	public static CloseableHttpClient createDefaultHttpClient(
			BasicCookieStore cookieStore) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setDefaultSocketConfig(SocketConfig.custom().setSoKeepAlive(true)
				.setSoReuseAddress(true).setSoTimeout(3000).build());
		cm.setMaxTotal(120);
		return HttpClients.custom().setDefaultCookieStore(cookieStore)
				.setConnectionManager(cm).build();
	}

	/**
	 * 传入session的get方法
	 * 
	 * @param session
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResult get(HttpSession session, String url,
			Map<String, String> headers, Map<String, String> params)
			throws ClientProtocolException, IOException {

		BasicCookieStore cookieStore = new BasicCookieStore();
		// 获取client
		CloseableHttpClient client = getHttpClient(session, cookieStore);
		return get(client, url, headers, params);

	}

	/**
	 * 传入session的post方法
	 * 
	 * @param session
	 * @param url
	 * @param headers
	 * @param params
	 * @param encoding
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResult post(HttpSession session, String url,
			Map<String, String> headers, Map<String, String> params,
			String encoding) throws ClientProtocolException, IOException {

		BasicCookieStore cookieStore = new BasicCookieStore();

		// 获取client
		CloseableHttpClient client = getHttpClient(session, cookieStore);

		return post(client, url, headers, params, encoding);
	}

	/**
	 * //TODO 转换header
	 * 
	 * @param headers
	 * @return
	 */
	private static Header[] parseHeader(Map<String, String> headers) {
		if (null == headers || headers.isEmpty()) {
			return getDefaultHeaders();
		}
		Header[] allHeader = new BasicHeader[headers.size()];
		int i = 0;
		for (String str : headers.keySet()) {
			allHeader[i] = new BasicHeader(str, headers.get(str));
			i++;
		}
		return allHeader;
	}

	/**
	 * //TODO 默认header
	 * 
	 * @return
	 */
	private static Header[] getDefaultHeaders() {
		Header[] allHeader = new BasicHeader[4];
		allHeader[0] = new BasicHeader("Content-Type",
				"application/x-www-form-urlencoded");
		allHeader[1] = new BasicHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
		allHeader[2] = new BasicHeader("Accept","text/html, application/xhtml+xml, */*");
		allHeader[3] = new BasicHeader("Connection","Keep-Alive");
		return allHeader;
	}

	/**
	 * //TODO 转换参数列表
	 * 
	 * @param params
	 * @return
	 */
	private static String parseParam(Map<String, String> params) {
		if (null == params || params.isEmpty()) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String key : params.keySet()) {
			sb.append(key + "=" + params.get(key) + "&");
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 释放httpclient对象
	 */
	public static void closeClient(CloseableHttpClient client) {
		if (null != client) {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 传入HttpClient的Get方法
	 * 
	 * @param client
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResult get(HttpClient client, String url,
			Map<String, String> headers, Map<String, String> params)
			throws ClientProtocolException, IOException {
		BasicCookieStore cookieStore = new BasicCookieStore();
		url = (null == params ? url : url + "?" + parseParam(params));

		HttpGet get = new HttpGet(url);
		get.setHeaders(parseHeader(headers));

		
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();

		HttpResult result = new HttpResult();

		result.setHttpClient((CloseableHttpClient) client);

		result.setCookies(cookieStore.getCookies());

		result.setStatusCode(response.getStatusLine().getStatusCode());

		result.setHeaders(response.getAllHeaders());

		result.setHttpEntity(entity);

		result.setBody(EntityUtils.toString(entity));
		return result;
	}

	/**
	 * 传入Client的Post方法
	 * 
	 * @param client
	 * @param url
	 * @param headers
	 * @param params
	 * @param encoding
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResult post(HttpClient httpClient, String url,
			Map<String, String> headers, Map<String, String> params,
			String encoding) throws ClientProtocolException, IOException {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = (CloseableHttpClient) httpClient;
		HttpPost post = new HttpPost(url);
		post.setHeaders(parseHeader(headers));
		
		
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (String temp : params.keySet()) {
			list.add(new BasicNameValuePair(temp, params.get(temp)));
		}
		post.setEntity(new UrlEncodedFormEntity(list, encoding));

		

		CloseableHttpResponse response = (CloseableHttpResponse) client
				.execute(post);
		HttpEntity entity = response.getEntity();

		HttpResult result = new HttpResult();

		result.setHttpClient(client);

		result.setCookies(cookieStore.getCookies());

		result.setStatusCode(response.getStatusLine().getStatusCode());

		result.setHeaders(response.getAllHeaders());

		result.setHttpEntity(entity);

		result.setBody(EntityUtils.toString(entity));
		return result;
	}

}
