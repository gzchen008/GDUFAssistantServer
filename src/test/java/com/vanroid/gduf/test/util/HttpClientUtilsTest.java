package com.vanroid.gduf.test.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.vanroid.gduf.util.HttpClientUtils;

public class HttpClientUtilsTest {

	@Test
	public void testGet(){
		String url = null;
		try {
			url = "http://www.gduf.edu.cn/mailPersonAjaxSelect.jsp?flag=@#"+URLEncoder.encode("陈广镇","gbk");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String body = null;
		try {
			Map<String, String> params = new HashMap<String,String>();
			params.put("", "");
			body = HttpClientUtils.get(HttpClientUtils.createDefaultHttpClient(null), url, null, null ).getBody();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(body);
	}
	@Test
	public void testPost(){
		String url ="http://www.gduf.edu.cn/mailPersonAjaxSelect.jsp";
		String body = null;
		try {
			Map<String, String> params = new HashMap<String,String>();
			params.put("flag", "@#黄晓强");
			body = HttpClientUtils.post(HttpClientUtils.createDefaultHttpClient(null), url, null, params , "gbk").getBody();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(body);
	}
	@Test
	public void testSendMail(){
		Map<String, String> params = new HashMap<String,String>();
		params.put("title", "我能发邮件了");
		params.put("addr", "57202");
		params.put("addr2", "");
		params.put("count", "0");
		params.put("replyName", "陈广镇");
		params.put("content", "hello");
		
		

		
	}
}
