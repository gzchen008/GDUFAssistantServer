package com.vanroid.gduf.dao.libary.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.vanroid.gduf.dao.libary.LibrarySearchDao;
import com.vanroid.gduf.entity.LibrarySearchHistory;

@Repository("librarySearchDao")
public class LibrarySearchDaoImpl implements LibrarySearchDao {

	private HibernateTemplate hibernateTemplate;

	/**
	 * 是否在本地数据库上存在该查询纪录
	 * 
	 * @param keywords
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@Deprecated
	public boolean isExist(String keywords, int page) throws SQLException {
		/*
		 * String sql =
		 * "select count(*) from gd_history_library_search where keywords=? and page=?"
		 * ; Number number = (Number) qr.query(sql, new ScalarHandler(),
		 * keywords, page); if (number.intValue() == 0) return false;
		 */
		return true;
	}

	/**
	 * 查询每页的数据
	 * 
	 * @param keywords
	 *            关键字
	 * @param page
	 *            页码
	 * @return
	 * @throws SQLException
	 */

	@Override
	public LibrarySearchHistory search(String keywords, int page) {
		Query query = hibernateTemplate
				.getSessionFactory()
				.openSession()
				.createQuery(
						"from LibrarySearchHistory as lsh where lsh.keywords ='"
								+ keywords + "' and lsh.page =" + page);
		LibrarySearchHistory info = (LibrarySearchHistory) query.uniqueResult();

		return info;
	}

	/**
	 * 保存pageBean方法
	 * 
	 * @param pageBean
	 * @throws SQLException
	 */
	@Override
	public void save(LibrarySearchHistory librarySearchHistory) {
		hibernateTemplate.save(librarySearchHistory);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	
}
