package com.vanroid.gduf.service.jwc;

import com.vanroid.gduf.entity.Course;
import com.vanroid.gduf.service.impl.jwc.JWCHandler;

/**
 * 
 * @ClassName GradeService.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 课程表service层接口
 * 
 * @version 1.0
 */

public interface CourseService {
	public void add(Course c);

	public void update(Course c);

	public Course query();
	/**
	 * 获取课程信息
	 * @param handler
	 * @param course
	 * @param xm
	 * @return
	 */

	public Course getCourseInfo(JWCHandler handler, Course course, String xm);

	public Course queryExistInDb(Course c);
}
