package com.vanroid.gduf.dao.libary.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.vanroid.gduf.dao.libary.BookDao;
import com.vanroid.gduf.entity.Book;
/**
 * 
* @ClassName BookDaoImpl.java Create on 2015-8-28     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author CGZ vsjosonadmin@163.com  
*  
* @Description: BookDao 实现类
*
* @version 1.0
 */
@Repository(value="bookDao")
public class BookDaoImpl implements BookDao{
	@Resource
	private HibernateTemplate hibernateTemplate;

	@Override
	public void add(Book book) {
		hibernateTemplate.save(book);
	}

}
