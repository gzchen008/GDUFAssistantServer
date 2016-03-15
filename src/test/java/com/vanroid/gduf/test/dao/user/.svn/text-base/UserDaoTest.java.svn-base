package com.vanroid.gduf.test.dao.user;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanroid.gduf.dao.user.UserDao;
import com.vanroid.gduf.util.MD5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserDaoTest {
	@Resource
	private UserDao userDao;

	@Test
	public void testCountByPhone(){
		System.out.println(userDao.countByPhone("18826243872"));
	}
	@Test
	public void testFindByPro(){
		String s = userDao.findByProp(new String[]{"telphone","password"}, "18826243871",MD5Util.MD5("123")).get(0).toString();
		System.out.println(s);
	}
}
