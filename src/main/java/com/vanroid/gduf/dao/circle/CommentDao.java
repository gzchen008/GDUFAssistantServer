package com.vanroid.gduf.dao.circle;

import java.util.List;

import com.vanroid.gduf.entity.Comment;
/**
 * 
* @ClassName CommentDao.java Create on 2015年8月29日     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author Jerry 983951558@qq.com  
*  
* @Description:   评论点赞Dao接口
*
* @version 1.0
 */
public interface CommentDao {
	public void addComment(Comment comment);

	public void deleteComment(int cid);

	public List<Comment> queryAllComments(int tid);
	public Comment queryById(int cid);
	public boolean isSupported(Comment comment);
	public int getCid(Comment comment);
}
