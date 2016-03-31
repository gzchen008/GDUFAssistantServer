package com.vanroid.gduf.dao.circle;

import java.util.List;

import com.vanroid.gduf.entity.Circle;
import com.vanroid.gduf.entity.CircleMes;

/**
 * 
 * @ClassName CircleDao.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 朋友圈Dao层接口
 * 
 * @version 1.0
 */
public interface CircleDao {
	public void addCircle(Circle circle);

	public Circle deleteCircle(int tid);

	public List<Circle> queryCircles(int from, int size);

	public Circle queryById(int tid);

	public void addNotifaction(CircleMes cmes);

	public List<CircleMes> findMyNotifaction(int myId);

}
