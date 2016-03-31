package com.vanroid.gduf.controller.jwc;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vanroid.gduf.dto.JwcInfo;
import com.vanroid.gduf.entity.Course;
import com.vanroid.gduf.entity.Grade;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.impl.jwc.JWCHandler;
import com.vanroid.gduf.service.impl.jwc.JwcLoginService;
import com.vanroid.gduf.service.jwc.CourseService;
import com.vanroid.gduf.service.jwc.GradeService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 
 * @ClassName CourseAction.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 教务系统的动作类，包括教务系统登陆、课程表，成绩表的获取
 * 
 * @version 1.0
 */
@Controller("jwcAction")
@Scope("prototype")
public class JwcAction extends ActionSupport implements ModelDriven<JwcInfo> {
	private JwcInfo info = new JwcInfo();
	private JwcLoginService loginService;
	private CourseService courseService;
	private GradeService gradeService;
	private HttpSession session = ServletActionContext.getRequest()
			.getSession();
	// map包含resultCode和msg,resultCode的0表示失败，1表示成功
	Map<String, Object> resultMap = new HashMap<String, Object>();

	/**
	 * 获取课程表和成绩单操作前都要进行教务系统的登陆
	 * 
	 * @param JwcLoginInfo类中的stuid和Jwcpassword
	 */
	public String login() {
		String xm = loginService.login(session, info);
		if (!(xm == null || "".equals(xm))) {
			session.setAttribute("userName", xm);
			return Action.SUCCESS;
		} else
			return Action.ERROR;
	}

	/**
	 * 课程表的获取 先登陆
	 * 
	 * @param JwcInfo类中的xh和password
	 *            再获取
	 * @param JwcInfo类中的xh
	 *            ,year,xq和登陆成功后返回的xm
	 */
	public String getCourse() {
		/*CloseableHttpClient httpClient = HttpClientUtils.getHttpClient(session,
				new BasicCookieStore());
		// 先登陆获取姓名
		String xm = loginService.login(httpClient, info);
		if (!(xm == null || "".equals(xm)))
			System.out.println("--------登陆jwc成功-------");*/
		/*if(loginService.login(session,info)=="Action.ERROR")
			return Action.ERROR;*/
		Course course = new Course(info.getXh(), info.getYear(), info.getXq());
		Course validCourse = courseService.getCourseInfo(session, course, info.getXm());
		if (validCourse != null && validCourse.getClasses().size() > 0) {
			resultMap.put("resultCode", 1);
			resultMap.put("msg", "课程表获取成功");
			resultMap.put("datas", validCourse);
			return Action.SUCCESS;
		} else {
			resultMap.put("resultCode", 0);
			resultMap.put("msg", "课程表获取失败！！！");
			return Action.ERROR;
		}
	}

	/**
	 * 成绩表的获取 先登陆
	 * 
	 * @param JwcInfo类中的xh和password
	 *            再获取
	 * @param JwcInfo类中的xh
	 *            ,year,xq和登陆成功后返回的xm
	 */
	public String getGrade() {
		/*CloseableHttpClient httpClient = HttpClientUtils.getHttpClient(session,
				new BasicCookieStore());
		// 先登陆获取姓名
		String xm = loginService.login(httpClient, info);
		if (!(xm == null || "".equals(xm)))
			System.out.println("--------登陆jwc成功-------");*/
		/*if(loginService.login(session,info)=="Action.ERROR")
			return Action.ERROR;*/
		Grade grade = new Grade(info.getXh(), info.getYear(), info.getXq());
		Grade validGrade = gradeService.getGradeInfo(session,grade, info.getXm());
		if (validGrade != null && validGrade.getSubjects().size() > 0) {
			resultMap.put("resultCode", 1);
			resultMap.put("msg", "成绩表获取成功");
			resultMap.put("datas", validGrade);
			return Action.SUCCESS;
		} else {
			resultMap.put("resultCode", 0);
			resultMap.put("msg", "成绩表获取失败！！！");
			return Action.ERROR;
		}
	}

	@Override
	public JwcInfo getModel() {
		User user = (User)session.getAttribute("qtUser");
		info.setXh(user.getStuId());
		info.setPassword(user.getJwcPass());
		info.setXm(user.getRealName());
		return info;
	}

	public JwcInfo getInfo() {
		return info;
	}

	public void setInfo(JwcInfo info) {
		this.info = info;
	}

	public JwcLoginService getLoginService() {
		return loginService;
	}

	@Resource
	public void setLoginService(JwcLoginService loginService) {
		this.loginService = loginService;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	@Resource
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	@Resource
	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
