package com.vanroid.gduf.test.service.jwc;

import javax.annotation.Resource;

import org.apache.http.client.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanroid.gduf.dto.JwcInfo;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.impl.jwc.JWCHandler;
import com.vanroid.gduf.service.impl.jwc.JwcLoginService;
import com.vanroid.gduf.util.HttpClientUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class JwcServiceTest {
	@Resource(name="loginService")
	private JwcLoginService jwcLoginService;
	@Test
	public void testLogin(){
		JwcInfo jwcInfo = new JwcInfo();
		jwcInfo.setXh("131545251");
		jwcInfo.setPassword("fy18098135003@");
		HttpClient client = HttpClientUtils.createDefaultHttpClient(null);
		String jwcRes = jwcLoginService.login(client,jwcInfo);
		JWCHandler handler = new JWCHandler(client);
		User user = handler.getUserInfo(jwcInfo.getXh(), jwcRes);
		System.out.println(user);
	}
	
	
}
