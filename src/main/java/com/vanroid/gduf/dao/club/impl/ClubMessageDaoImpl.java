package com.vanroid.gduf.dao.club.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.vanroid.gduf.dao.club.ClubMessageDao;
import com.vanroid.gduf.entity.ClubMessage;

public class ClubMessageDaoImpl implements ClubMessageDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveClubMessage(ClubMessage clubMessage) {
		sessionFactory.getCurrentSession().save(clubMessage);
	}

	public ClubMessage getClubMessageById(Integer id) {

		return (ClubMessage) sessionFactory.getCurrentSession().get(
				ClubMessage.class, id);
	}

	/**
	 * 分页查询记录
	 */
	@SuppressWarnings("unchecked")
	public List<ClubMessage> queryByPage(String hql, int offset, int length) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql)
				.setFirstResult(offset).setMaxResults(length);
		List<ClubMessage> list = query.list();
		System.out.println("分页结果:" + list.size());
		return list;
	}

	public int getAllRowCount(String hql) {

		return sessionFactory.getCurrentSession().createQuery(hql).list()
				.size();
	}

	/**
	 * 根据给定社团消息类Id删除信息
	 */
	public void deleteItemById(String id) {
		ClubMessage clubMessage = getClubMessageById(Integer.parseInt(id));
		sessionFactory.getCurrentSession().delete(clubMessage);
	}

	/**
	 * 更新消息
	 */
	public void updateMessage(ClubMessage clubMessage) {

		sessionFactory.getCurrentSession().update(clubMessage);
	}

}
