package com.vanroid.gduf.dao.impl.circle;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.dao.circle.CommentDao;
import com.vanroid.gduf.entity.Circle;
import com.vanroid.gduf.entity.Comment;

/**
 * 
 * @ClassName CommentDaoImpl.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 点赞评论dao层实现类
 * 
 * @version 1.0
 */
@Component("commentDao")
public class CommentDaoImpl implements CommentDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactoy;

	@Override
	/**
	 * 朋友圈好友评论、点赞
	 */
	public void addComment(Comment comment) {
		// TODO 自动生成的方法存根
		comment.setCtime(new Date());
		hibernateTemplate.save(comment);
	}

	/**
	 * 删除单条评论
	 */
	@Override
	public void deleteComment(int cid) {
		// TODO 自动生成的方法存根
		Comment comment = queryById(cid);
		hibernateTemplate.delete(comment);
	}

	/**
	 * 根据朋友圈的Id查询所有的评论
	 */
	@Override
	@Transactional
	public List<Comment> queryAllComments(int tid) {
		// TODO 自动生成的方法存根
		Session s = sessionFactoy.getCurrentSession();
		String hql = "From Comment where tid=?";
		Query query = s.createQuery(hql).setInteger(0, tid);
		List<Comment> list = query.list();
		return list;
	}

	/**
	 * 根据评论的id返回该评论的实体
	 */
	@Override
	public Comment queryById(int cid) {
		// TODO 自动生成的方法存根
		Comment comment = hibernateTemplate.get(Comment.class, cid);
		return comment;
	}

	/**
	 * 判断是否已经点赞过了
	 */
	@Override
	@Transactional
	public boolean isSupported(Comment comment) {
		// TODO 自动生成的方法存根
		Session session = sessionFactoy.getCurrentSession();
		String hql = "select count(*) from Comment where tid=? and sender=? and text=?";
		Query query = session.createQuery(hql).setEntity(0, comment.getTid())
				.setEntity(1, comment.getSender()).setString(2, "");
		if (((Long) query.uniqueResult()) > 0)
			return true;
		return false;
	}

	// 根据内容返回评论的cid
	@Transactional
	public int getCid(Comment comment) {
		// TODO 自动生成的方法存根
		Session session = sessionFactoy.getCurrentSession();
		String hql = "select cid from Comment where tid=? and sender=? and text=?";
		Query query = session.createQuery(hql).setEntity(0, comment.getTid())
				.setEntity(1, comment.getSender())
				.setString(2, comment.getText());
		return (Integer) query.uniqueResult();
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

}
