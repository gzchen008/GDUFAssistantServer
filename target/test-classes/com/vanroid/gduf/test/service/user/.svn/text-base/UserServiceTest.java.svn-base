package com.vanroid.gduf.test.service.user;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserServiceTest {
	@Resource
	private UserService userService;
	@Test
	public void testAdd(){
		User user = new User();
		user.setRealName("陈广镇");
		userService.add(user);
	}
	@Test
	public void testRegisterByPhone(){
		User user = new User();
		user.setTelphone("18826243871");
		user.setPassword("123");
		user.setComfirmPassword("123");
		Map<String, Object> result = userService.registerByPhone(user);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("msg"));
		System.out.println(user);
	}
	
}
