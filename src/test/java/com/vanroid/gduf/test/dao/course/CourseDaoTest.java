package com.vanroid.gduf.test.dao.course;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanroid.gduf.dao.jwc.CourseDao;
import com.vanroid.gduf.entity.ClassBean;
import com.vanroid.gduf.entity.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CourseDaoTest {
	@Resource
	private CourseDao courseDao;

	@Test
	public void testQuery() {
		Course c = new Course();
		c.setStuId("131545230");
		c.setXq(1);
		c.setYear("2015-2016");

		Set<ClassBean> classes = new HashSet<ClassBean>();
		ClassBean cb = new ClassBean();
		cb.setTitle("语文");
		ClassBean cb2 = new ClassBean();
		cb2.setTitle("数学");
		ClassBean cb3 = new ClassBean();
		cb3.setTitle("英语");
		classes.add(cb);
		classes.add(cb2);
		classes.add(cb3);
		c.setClasses(classes);
		courseDao.add(c);
	}

}
