package com.vanroid.gduf.service.impl.jwc;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.vanroid.gduf.dao.jwc.CourseDao;
import com.vanroid.gduf.dao.jwc.GradeDao;
import com.vanroid.gduf.entity.Course;
import com.vanroid.gduf.entity.Grade;
import com.vanroid.gduf.service.jwc.CourseService;
import com.vanroid.gduf.service.jwc.GradeService;

/**
 * 
 * @ClassName CourseServiceImpl.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO(课程表的逻辑处理类，包括从网上获取数据和从数据库中读取和写入)
 * 
 * @version 1.0
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {
	private GradeDao gradeDao;

	/**
	 * 先查询数据库中是否含有该课程信息，如果没有再到网上获取，获取后再存入数据库 该传入参数的course必须传入年份、学期、学号！
	 */
	public Grade getGradeInfo(JWCHandler handler,Grade grade, String xm) {
		// TODO 自动生成的方法存根
		Grade backGrade=queryExistInDb(grade);
		if ( backGrade!= null) {
			System.out.println("-------从缓存中获取---------");
			return backGrade;
		}
		try {
			System.out.println("------从网上获取数据开始-------");
			// 从网络上获取数据
			final Grade grade2 = handler.getGrade(grade.getStuId(), xm,
					grade.getYear(), grade.getXq());
			// 开启一个新的线程存储到数据库
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					gradeDao.add(grade2);
					System.out.println("-------已存入数据库-------");
				}
			}).start();
			return grade2;
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;

	}

	public void add(Grade c) {
		// TODO 自动生成的方法存根
		gradeDao.add(c);
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

	public GradeDao getGradeDao() {
		return gradeDao;
	}

	@Resource
	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}

	@Override
	public Grade queryExistInDb(Grade c) {
		// TODO 自动生成的方法存根
		Grade grade = gradeDao.queryExistInDb(c);
		return grade;
	}

}
