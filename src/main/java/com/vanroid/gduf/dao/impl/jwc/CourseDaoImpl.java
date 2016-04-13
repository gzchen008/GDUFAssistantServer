package com.vanroid.gduf.dao.impl.jwc;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.dao.jwc.CourseDao;
import com.vanroid.gduf.entity.Course;

/**
 * 
 * @ClassName CourseDaoImpl.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO(courseDao接口的实现类)
 * 
 * @version 1.0
 */
@Repository("courseDao")
public class CourseDaoImpl implements CourseDao {
	private HibernateTemplate hibernateTemplate;

	@Override
	public void add(Course c) {
		// TODO 自动生成的方法存根
		try {
			hibernateTemplate.save(c);
		} catch (java.lang.IllegalArgumentException e) {
		}
	}

	@Override
	public void update(Course c) {
		// TODO 自动生成的方法存根

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
	public Course queryExistInDb(Course c) {

		// TODO 自动生成的方法存根
		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session
				.createQuery("from Course where stuId=? and year=? and xq=?")
				.setString(0, c.getStuId()).setString(1, c.getYear())
				.setInteger(2, c.getXq());
		Course course = (Course) query.uniqueResult();
		if (course != null && course.getCid() >= 0)
			return course;
		else
			return null;
	}

}
