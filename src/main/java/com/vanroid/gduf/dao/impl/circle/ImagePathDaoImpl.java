package com.vanroid.gduf.dao.impl.circle;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.vanroid.gduf.dao.circle.ImagePathDao;
import com.vanroid.gduf.entity.ImagePath;
@Component("imagePathDao")
public class ImagePathDaoImpl implements ImagePathDao {
	private HibernateTemplate hibernateTemplate;
	@Override
	public void addImage(ImagePath ip) {
		// TODO 自动生成的方法存根
		hibernateTemplate.save(ip);
	}

	@Override
	public String queryPath(int tid) {
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

}
