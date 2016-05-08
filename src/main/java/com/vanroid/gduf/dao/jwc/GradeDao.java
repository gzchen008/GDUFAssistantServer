package com.vanroid.gduf.dao.jwc;

import com.vanroid.gduf.entity.Grade;

/**
 * 
 * @ClassName CourseDao.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO(成绩表dao的接口)
 * 
 * @version 1.0
 */
public interface GradeDao {
	public void add(Grade g);

	public void update(Grade g);

	public Grade query();

	public Grade queryExistInDb(Grade g);
}
