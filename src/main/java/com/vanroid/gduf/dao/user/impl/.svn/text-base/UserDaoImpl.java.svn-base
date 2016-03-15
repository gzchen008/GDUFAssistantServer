package com.vanroid.gduf.dao.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.vanroid.gduf.dao.user.UserDao;
import com.vanroid.gduf.entity.User;

/**
 * 
 * @ClassName UserDaoImpl.java Create on 2015-8-29
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description:
 * 
 * @version 1.0
 */
@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

	private HibernateTemplate hiberanteTemplate;

	public int add(User user) {
		return (Integer) hiberanteTemplate.save(user);
	}

	public HibernateTemplate getHiberanteTemplate() {
		return hiberanteTemplate;
	}

	@Resource
	public void setHiberanteTemplate(HibernateTemplate hiberanteTemplate) {
		this.hiberanteTemplate = hiberanteTemplate;
	}

	@Override
	public int countByPhone(String phone) {
		String hql = "from User as user where user.telphone = " + phone;
		List list = hiberanteTemplate.find(hql);
		return list.size();
	}

	@Override
	public User loadByPhone(String phone) {
		String hql = "from User where telphone = ?";
		List<User> ls = (List<User>) hiberanteTemplate.find(hql, phone);
		return ls.size() == 0 ? null : ls.get(0);
	}

	@Override
	public List<User> findByProp(String[] params, Object... values) {
		String hql = "";
		if (params == null || params.length == 0) {
			hql = "from User";
		} else {
			hql = "from User where ";
			for (String param : params) {
				hql += param + " = ? and ";
			}
			// 去掉最后的and
			hql = hql.substring(0, hql.lastIndexOf("and"));
		}
		System.out.println(hql);
		return (List<User>) hiberanteTemplate.find(hql, values);
	}

	@Override
	public boolean update(User user) {
		try {
			hiberanteTemplate.update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
