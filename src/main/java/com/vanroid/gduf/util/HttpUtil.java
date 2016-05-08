package com.vanroid.gduf.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * httpClient工具类
 * 
 */
public class HttpUtil {
	public static HttpPost getHttpPost(HttpClient httpClient, String url) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Accept", "text/html, application/xhtml+xml, */*");
		httpPost.addHeader("Accept-Language", "zh-CN,en-US;q=0.5");
		httpPost.addHeader("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		httpPost.addHeader("Accept-Encoding", "gzip, deflate");
		httpPost.addHeader("Connection", "Keep-Alive");

		return httpPost;

	}

	public static HttpResponse getResponse(HttpClient httpClient,
			HttpGet httpRequest) throws ClientProtocolException, IOException {
		HttpResponse response = httpClient.execute(httpRequest);
		return response;
	}

	// 服务器是否有正常回应
	public static boolean isOK(HttpResponse httpResponse) {
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			return true;
		return false;
	}

	public static String getResultString(HttpResponse httpResponse)
			throws ParseException, IOException {
		return EntityUtils.toString(httpResponse.getEntity());

	}

	public static HttpGet getHttpGet(HttpClient httpClient, String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept", "text/html, application/xhtml+xml, */*");
		httpGet.addHeader("Accept-Language", "zh-CN,en-US;q=0.5");
		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		httpGet.addHeader("Accept-Encoding", "gzip, deflate");
		httpGet.addHeader("Connection", "Keep-Alive");

		return httpGet;

	}

}
