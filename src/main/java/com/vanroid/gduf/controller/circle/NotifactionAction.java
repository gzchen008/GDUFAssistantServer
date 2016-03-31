package com.vanroid.gduf.controller.circle;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.dao.circle.CircleDao;
import com.vanroid.gduf.entity.CircleMes;

/**
 * 
 * @ClassName NotifactionAction.java Create on 2015年10月1日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 消息通知action
 * 
 * @version 1.0
 */
@Controller("notifactionAction")
public class NotifactionAction extends ActionSupport {
	private CircleDao circleDao;
	private int myId;

	public String execute() {
		List<CircleMes> list=circleDao.findMyNotifaction(myId);
		ServletActionContext.getRequest().setAttribute("myNotifaction", list);
		return Action.SUCCESS;
	}

	public CircleDao getCircleDao() {
		return circleDao;
	}

	@Resource
	public void setCircleDao(CircleDao circleDao) {
		this.circleDao = circleDao;
	}

	public int getMyId() {
		return myId;
	}

	public void setMyId(int myId) {
		this.myId = myId;
	}
	
}
