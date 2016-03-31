package com.vanroid.gduf.service.circle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanroid.gduf.dao.circle.CommentDao;
import com.vanroid.gduf.entity.Circle;
import com.vanroid.gduf.entity.Comment;
import com.vanroid.gduf.entity.ImagePath;
import com.vanroid.gduf.entity.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestCircleService {
	Circle c;
	public TestCircleService() {
		// TODO 自动生成的构造函数存根
		c=new Circle();
		User u1=new User();u1.setId(1);
		User u2=new User();u2.setId(2);
		c.setContent("我今天捡到一百块钱");
		c.setCreateTime(new Date());
		c.setSender(u1);
		ImagePath ip1=new ImagePath(); ip1.setPath("1111");ip1.setTid(c);
		ImagePath ip2=new ImagePath();ip2.setPath("2222");ip2.setTid(c);
		List<ImagePath> images=new ArrayList<ImagePath>();
		images.add(ip1);images.add(ip2);
		c.setImages(images);
		
	}
	@Autowired
	private CircleService circleService;
	@Autowired
	private CommentDao commentDao;
	@Test
	public void testAddCircle() {
		
		circleService.addCircle(c);
	}

	@Test
	public void testDeleteCircle() {
		Circle c2=new Circle();c2.setTid(29);
		//circleService.deleteCircle(c2);
	}
	@Test
	public void testDeleteComment(){
		
		circleService.deleteComment(42);
	}
	@Test
	public void testQueryCircles() {
		List<Circle> list=circleService.queryCircles(0, 1);
		int i=0;
		for (Circle circle : list) {
			//circle.ge
			
		}
	}
	@Test
	public void testGetCid(){
		//tid=? and sender=? and text=
		Comment comment=new Comment();
		Circle circle=new Circle();circle.setTid(52);
		comment.setTid(circle);
		User u=new User();u.setId(1);
		comment.setSender(u);
		comment.setText("你吃饭没");
		System.out.println("cid:"+circleService.getCid(comment)); ;
	}
	@Test
	public void testAddComment(){
		Comment comment=new Comment();
		Circle circle=new Circle();circle.setTid(30);
		comment.setTid(circle);
		commentDao.addComment(comment);
	}

	public CircleService getCircleService() {
		return circleService;
	}

	public void setCircleService(CircleService circleService) {
		this.circleService = circleService;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

}
