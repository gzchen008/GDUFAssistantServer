package com.vanroid.gduf.dao.club.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.vanroid.gduf.dao.club.AdminDao;
import com.vanroid.gduf.entity.Admin;

@Repository("adminDao")
public class AdminDaoImp implements AdminDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Admin getAdminByAccount(String account) {
		String hql = "from Admin a where a.aAccount =:aAccount";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Admin> list = session.createQuery(hql).setString("aAccount", account).list();
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public List<Admin> getAllAdmin() {
		String hql = "from Admin";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Admin> list = session.createQuery(hql).list();
		return list;
	}

	@Override
	public Admin getAdminById(int id) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Admin admin = (Admin) session.get(Admin.class, id);
		return admin;
	}
}
