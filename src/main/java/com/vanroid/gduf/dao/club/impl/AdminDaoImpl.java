package com.vanroid.gduf.dao.club.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.vanroid.gduf.dao.club.AdminDao;
import com.vanroid.gduf.entity.Admin;

public class AdminDaoImpl implements AdminDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 校验管理员账号密码是否正确。
	 */
	@SuppressWarnings("unchecked")
	public Admin getAdmin(Admin admin) {
		String hql = "from Admin where admin_name=? and admin_password=?";
		List<Admin> list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, admin.getAdmin_name())
				.setParameter(1, admin.getAdmin_password()).list();
		if (list.size() != 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public void saveAdmin(Admin admin) {
		sessionFactory.getCurrentSession().save(admin);
		System.out.println("调用saveAdmin方法");
		System.out.println("保存成功");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Admin findAdminByName(String admin_name) {
		String hql = "from Admin where admin_name=?";
		List<Admin> list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, admin_name).list();
		return list.size() != 0 ? list.get(0) : null;
	}

	/**
	 * 更新管理员状态
	 */
	@Override
	public void updateAdmin(Admin existAdmin) {
		sessionFactory.getCurrentSession().update(existAdmin);
	}

}
