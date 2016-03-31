package com.vanroid.gduf.dao.impl.circle;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanroid.gduf.dao.circle.CircleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestCircleDao {
	@Autowired
	private CircleDao circleDao;

	@Test
	public void testAddCircle() {
		fail("尚未实现");
	}

	@Test
	public void testDeleteCircle() {
		circleDao.deleteCircle(42);
	}

	@Test
	public void testQueryCircles() {
		fail("尚未实现");
	}

	@Test
	public void testQueryById() {
		fail("尚未实现");
	}

	public CircleDao getCircleDao() {
		return circleDao;
	}

	public void setCircleDao(CircleDao circleDao) {
		this.circleDao = circleDao;
	}
}
