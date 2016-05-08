package com.vanroid.gduf.service.jwc;

import javax.servlet.http.HttpSession;

import com.vanroid.gduf.entity.Course;

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
	 */
	public Course getCourseInfo(HttpSession session,Course course, String xm);

	public Course queryExistInDb(Course c);
}
