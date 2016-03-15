package com.vanroid.gduf.service.impl.jwc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.vanroid.gduf.entity.User;

/**
 * 
* @ClassName UserInfoHtml.java Create on 2015-9-1     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author CGZ vsjosonadmin@163.com  
*  
* @Description:  解析HTML,获取用户信息的
*
* @version 1.0
 */
public class UserInfoHtml {
	public static User getUserInfo(String html){
		User user = new User();
		Document doc = Jsoup.parse(html);
		String marjor = doc.select("span[id=lbl_zymc]").text();
		String classId = doc.select("span[id=lbl_xzb]").text();
		String depart = doc.select("span[id=lbl_xy]").text();
		String sex = doc.select("span[id=lbl_xb]").text();
		String realName = doc.select("span[id=xm]").text();
		
		user.setMarjor(marjor);
		user.setClassId(classId);
		user.setDepart(depart);
		user.setSex(sex);
		user.setRealName(realName);
		
		return user;
	}

}
