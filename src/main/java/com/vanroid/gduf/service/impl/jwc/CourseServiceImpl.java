package com.vanroid.gduf.service.impl.jwc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;

import com.vanroid.gduf.dao.jwc.CourseDao;
import com.vanroid.gduf.entity.Course;
import com.vanroid.gduf.service.jwc.CourseService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 
 * @ClassName CourseServiceImpl.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO(课程表逻辑处理类，包括从网上获取数据和从数据库中读取和写入)
 * 
 * @version 1.0
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
	private CourseDao courseDao;

	/**
	 * 先查询数据库中是否含有该课程信息，如果没有再到网上获取，获取后再存入数据库 该传入参数的course必须传入年份、学期、学号！
	 */
	public Course getCourseInfo(HttpSession session,Course course, String xm) {
		// TODO 自动生成的方法存根
		CloseableHttpClient httpClient = HttpClientUtils.getHttpClient(session,
				new BasicCookieStore());
		JWCHandler handler=new JWCHandler(httpClient);
		Course backCourse=queryExistInDb(course);
		if ( backCourse!= null) {
			System.out.println("-------从缓存中获取---------");
			return backCourse;
		}
		try {
			System.out.println("------从网上获取数据开始-------");
			// 从网络上获取数据
			final Course couser2 = handler.getCourseData(course.getStuId(), xm,
					course.getYear(), course.getXq());
			// 开启一个新的线程存储到数据库
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					if(couser2!=null)
					courseDao.add(couser2);
					System.out.println("-------已存入数据库-------");
				}
			}).start();
			return couser2;
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void add(Course c) {
		// TODO 自动生成的方法存根
		courseDao.add(c);
	}

	@Override
	public void update(Course c) {
		// TODO 自动生成的方法存根

	}

	@Override
	public Course query() {
		// TODO 自动生成的方法存根
		return null;
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	@Resource
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Course queryExistInDb(Course c) {
		// TODO 自动生成的方法存根
		Course course = courseDao.queryExistInDb(c);
		return course;
	}


}
