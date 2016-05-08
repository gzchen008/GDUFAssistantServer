package com.vanroid.gduf.dao.impl.circle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.dao.circle.CircleDao;
import com.vanroid.gduf.entity.Circle;
import com.vanroid.gduf.entity.CircleMes;
import com.vanroid.gduf.entity.User;

/**
 * 
 * @ClassName CircleDaoImpl.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 朋友圈dao实现类
 * 
 * @version 1.0
 */
@Component("circleDao")
public class CircleDaoImpl implements CircleDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactoy;

	@Override
	/**
	 * 发布朋友圈
	 */
	@Transactional
	public void addCircle(Circle circle) {
		// TODO 自动生成的方法存根
		hibernateTemplate.save(circle);
	}

	/**
	 * 删除朋友圈
	 */

	@Override
	@Transactional
	public Circle deleteCircle(int tid) {
		// TODO 自动生成的方法存根
		// /Circle circle=queryById(tid);
		Circle circle = new Circle();
		circle.setTid(tid);
		hibernateTemplate.delete(circle);
		return circle;
	}

	@Override
	/**
	 * 根据坐标返回响应的朋友圈列表
	 */
	@Transactional
	public List<Circle> queryCircles(int from, int size) {
		// TODO 自动生成的方法存根
		Session s = sessionFactoy.getCurrentSession();
		String hql = "From Circle order by createTime desc";
		Query query = s.createQuery(hql).setFirstResult(from)
				.setMaxResults(size);
		List<Circle> list = query.list();
		return list;
	}

	@Override
	/**
	 * 根据id返回实体
	 */
	@Transactional
	public Circle queryById(int id) {
		Circle circle = hibernateTemplate.get(Circle.class, id);
		// TODO 自动生成的方法存根
		return circle;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public SessionFactory getSessionFactoy() {
		return sessionFactoy;
	}

	@Resource
	public void setSessionFactoy(SessionFactory sessionFactoy) {
		this.sessionFactoy = sessionFactoy;
	}

	@Override
	/**
	 * 消息通知
	 */
	public void addNotifaction(CircleMes cmes) {
		// TODO 自动生成的方法存根
		this.hibernateTemplate.save(cmes);
	}

	@Override
	@Transactional
	public List<CircleMes> findMyNotifaction(int myId) {
		// TODO 自动生成的方法存根
		List<CircleMes> list = new ArrayList<CircleMes>();
		Session session = this.sessionFactoy.getCurrentSession();
		String hql = "from CircleMes where receiver=? order by mid desc";
		User u = new User();
		u.setId(myId);
		Query query = session.createQuery(hql).setEntity(0, u);
		list = query.list();
		return list;

	}

}
