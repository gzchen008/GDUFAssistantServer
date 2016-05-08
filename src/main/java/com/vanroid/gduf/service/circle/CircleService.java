package com.vanroid.gduf.service.circle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vanroid.gduf.dao.circle.CircleDao;
import com.vanroid.gduf.dao.circle.CommentDao;
import com.vanroid.gduf.entity.Circle;
import com.vanroid.gduf.entity.CircleMes;
import com.vanroid.gduf.entity.Comment;

/**
 * 
 * @ClassName CircleService.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 朋友圈逻辑处理类，service层
 * 
 * @version 1.0
 */
@Service("circleService")
public class CircleService {
	private CircleDao circleDao;
	private CommentDao commentDao;

	/**
	 * 增加朋友圈（包括图片地址）
	 * 
	 * @param circle
	 */
	public void addCircle(Circle circle) {
		circleDao.addCircle(circle);
	}

	/**
	 * 删除朋友圈，同时删除所有的点赞和评论
	 * 
	 * @param circle
	 */
	public void deleteCircle(int id) {

		Circle circle = circleDao.deleteCircle(id);
	}

	/**
	 * 根据坐标获取朋友圈列表 和所有评论和点赞
	 * 
	 * @param from
	 *            从哪个开始
	 * @param size
	 *            每次“显示更多”显示多少个
	 * @return
	 */
	public List<Circle> queryCircles(int from, int size) {
		List<Circle> list = circleDao.queryCircles(from, size);
		List<Circle> fullCircles = new ArrayList<Circle>();
		for (Circle circle : list) {
			List<Comment> comments = commentDao.queryAllComments(circle
					.getTid());
			circle.setComments(comments);

		}

		return list;
	}

	/**
	 * 点赞或者添加评论
	 * 
	 * @param tid
	 *            对哪一条朋友圈
	 * @param comment
	 *            点赞或者评论的内容，当text字段为空时为点赞
	 */
	public void addComment(Comment comment) {
		Circle circle = circleDao.queryById(comment.getTid().getTid());// 先查出来再添加评论，防止朋友圈的其他信息被清空
		comment.setTid(circle);
		commentDao.addComment(comment);
	}

	public void deleteComment(int cid) {
		commentDao.deleteComment(cid);
	}

	/**
	 * 根据id查询到该朋友圈的实体
	 * 
	 * @param id
	 * @return
	 */
	public Circle queryById(int id) {
		Circle circle = circleDao.queryById(id);
		return circle;
	}

	public CircleDao getCircleDao() {
		return circleDao;
	}

	@Resource
	public void setCircleDao(CircleDao circleDao) {
		this.circleDao = circleDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Resource
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * 是否已经点赞过了
	 * 
	 * @param comment
	 * @return
	 */
	public boolean isSupported(Comment comment) {
		// TODO 自动生成的方法存根

		return commentDao.isSupported(comment);
	}

	/**
	 * 根据已经获得的comment内容获取到其cid
	 * 
	 * @param comment
	 * @return
	 */
	public int getCid(Comment comment) {
		return commentDao.getCid(comment);
	}

	/**
	 * 评论和点赞的消息通知
	 * 
	 * @param cmes
	 */
	public void addNotifaction(CircleMes cmes) {
		// TODO 自动生成的方法存根
		circleDao.addNotifaction(cmes);
	}
}
