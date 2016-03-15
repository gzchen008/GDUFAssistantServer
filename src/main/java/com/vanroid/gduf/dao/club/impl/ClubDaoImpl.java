package com.vanroid.gduf.dao.club.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.dao.club.ClubDao;
import com.vanroid.gduf.entity.Club;

@Transactional
public class ClubDaoImpl implements ClubDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 保存社团的信息
	 */
	public void saveClub(Club club) {
		sessionFactory.getCurrentSession().save(club);
	}

	/**
	 * 给定名称查询社团
	 */
	@SuppressWarnings("unchecked")
	public List<Club> getClubByName(Club club) {
		String hql = "from Club where club_name=?";

		List<Club> list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, club.getClub_name()).list();
		if (list.size() != 0)
			return list;
		else
			return null;
	}

	/**
	 * 给定Id查询社团
	 */
	public Club getClubById(Integer clubId) {

		return (Club) sessionFactory.getCurrentSession()
				.get(Club.class, clubId);
	}

	/**
	 * 得到所有的社团
	 */
	@SuppressWarnings("unchecked")
	public List<Club> getAllCLubs() {
		String hql = "from Club ";
		List<Club> list = sessionFactory.getCurrentSession().createQuery(hql)
				.list();
		if (list.size() != 0)
			return list;
		else
			return null;
	}

	/**
	 * 根据管理员Id寻找对应的社团
	 */
	@Override
	public Club findClueByAdminId(Integer id) {
		String hql = " from Club where admin.id=?";
		@SuppressWarnings("unchecked")
		List<Club> list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).list();
		System.out.println("查询到的容量:" + list.size());
		if (list.size() != 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public void updateClub(Club existClub) {
		sessionFactory.getCurrentSession().update(existClub);
	}

}
