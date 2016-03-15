package com.vanroid.gduf.dao.impl.jwc;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.dao.jwc.CourseDao;
import com.vanroid.gduf.dao.jwc.GradeDao;
import com.vanroid.gduf.entity.Course;
import com.vanroid.gduf.entity.Grade;

/**
 * 
 * @ClassName CourseDaoImpl.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO(GradeDao接口的实现类)
 * 
 * @version 1.0
 */
@Repository("gradeDao")
public class GradeDaoImpl implements GradeDao {
	private HibernateTemplate hibernateTemplate;

	@Override
	public void add(Grade c) {
		// TODO 自动生成的方法存根
		hibernateTemplate.save(c);
	}

	@Override
	public void update(Grade c) {
		// TODO 自动生成的方法存根

	}

	@Override
	public Grade query() {
		// TODO 自动生成的方法存根
		
		return null;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	@Transactional
	public Grade queryExistInDb(Grade c) {
		// TODO 自动生成的方法存根
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("from Grade where stuId=? and year=? and xq=?").setString(0, c.getStuId()).setString(1, c.getYear()).setInteger(2, c.getXq());
		Grade grade = (Grade) query.uniqueResult();
		if(grade!=null&&grade.getGid()>=0)
			return grade;
		else
			return null;
	}

}
