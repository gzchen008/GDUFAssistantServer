package com.vanroid.gduf.dao.club.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.common.page.Page;
import com.vanroid.gduf.dao.club.ClubDao;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.entity.Message;

@Repository("clubDao")
public class ClubDaoImp implements ClubDao {
	@Resource
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Club getClub(int cid) {

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Club club = (Club) session.get(Club.class, cid);
		return club;
	}

	@Override
	public int updateClub(Club club) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.update(club);
		return 0;
	}

	@Override
	public int deleteClub(int cid) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Club club = null;
		club = (Club) session.get(Club.class, cid);
		if (club == null) {
			return 0;
		} else {
			session.delete(club);
			return 1;
		}
	}

	@Override
	public int addClub(Club club) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.save(club);
		return 0;
	}

	@Override
	public List<Club> getList(Page page) {
		System.out.println("page");
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Club> clubs = session.createQuery("from Club").setFirstResult(page.getBegin())
				.setMaxResults(page.getPageSize()).list();
		return clubs;
	}

	@Override
	@Transactional
	public Admin getAdminForClub(int cid) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Admin admin = null;
		Club club = null;
		club = (Club) session.load(Club.class, cid);
		System.out.println(club);
		if (club != null) {
			admin = club.getAdmin();
		}
		return admin;
	}

	@Override
	public int countClub() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		int count = Integer.parseInt(String.valueOf(session.createQuery("select count(*) from Club").uniqueResult()));
		return count;
	}

	@Override
	public void save(Message message) {
		hibernateTemplate.getSessionFactory().getCurrentSession().save(message);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Club> getAllClub() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		return session.createQuery("from Club club").list();
	}

	@Override
	public void saveClub(Club club) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.save(club);
	}

	@Override
	public int countMsg(String flag) {
		StringBuilder hql = new StringBuilder("select count(*) from Message m where 1=1");
		if ("club".equals(flag))
			hql.append(" and m.type = 1");
		else if ("offer".equals(flag))
			hql.append(" and m.type = 2 ");
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		int count = Integer.parseInt(String.valueOf(session.createQuery(hql.toString()).uniqueResult()));
		return count;
	}

	@Override
	public List<Message> getMsgList(Page page, String flag) {
		StringBuilder hql = new StringBuilder("from Message m where 1 = 1");
		if ("club".equals(flag))
			hql.append(" and m.type = 1");
		else if ("offer".equals(flag))
			hql.append(" and m.type = 2");
		else if ("lf".equals(flag))
			hql.append("and m.type =3");
		System.out.println("page");
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Message> messages = session.createQuery(hql.toString()).setFirstResult(page.getBegin())
				.setMaxResults(page.getPageSize()).list();
		return messages;
	}

	@Override
	public Message getMessageById(int mid) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		return (Message) session.get(Message.class, mid);
	}

	@Override
	public void deleteMsg(int cid) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Message msg = (Message) session.get(Message.class, cid);
		session.delete(msg);
	}

	@Override
	public List<Message> getMsgByCId(int parseInt) {
		String hql = "from Message m where m.club.cid=:cid";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setParameter("cid", parseInt).list();
	}

}
