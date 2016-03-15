package com.vanroid.gduf.service.impl.jwc;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import com.vanroid.gduf.common.GdufLinks;
import com.vanroid.gduf.dto.JwcInfo;
import com.vanroid.gduf.entity.ClassBean;
import com.vanroid.gduf.entity.Course;
import com.vanroid.gduf.entity.Grade;
import com.vanroid.gduf.entity.Subject;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.util.HttpClientUtils;
import com.vanroid.gduf.util.HttpResult;

/**
 * 
 * @ClassName JWCHandler.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 教务系统的处理控制者，包括教务系统登陆，获取课程表和成绩的数据
 * 
 * @version 1.0
 */
public class JWCHandler {
	private HttpClient httpClient;
	private CourseHtmlHandler coursehtmlHandler = new CourseHtmlHandler(); // 处理Html代码的工具类
	private GradeHtmlHandler gradehtmlHandler = new GradeHtmlHandler();

	public JWCHandler(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * 教务系统的登陆，登陆成功即获取姓名 第一次get获取到viewstate 第二次post登陆数据 第三次get，从referer链接获取数据
	 * 
	 * @param httpClient
	 * 
	 */
	public String login(JwcInfo info) throws ClientProtocolException,
			IOException {

		// 目的是获取ViewState
		String code = HttpClientUtils.get(httpClient, GdufLinks.JWC_LOGIN,
				null, null).getBody();
		// 获取ViewState值
		String __VIEWSTATE = coursehtmlHandler.getVIEWSTATE(code);
		System.out.println("VIEWSTATE的值为：" + __VIEWSTATE);
		// 前一连接
		String referer = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/xs_main.aspx?xh="
				+ info.getXh();
		Map<String, String> params = new HashMap<String, String>();
		params.put("txtUserName", info.getXh());
		params.put("TextBox2", info.getPassword());
		params.put("__VIEWSTATE", __VIEWSTATE);
		params.put("txtSecretCode", "");
		params.put("RadioButtonList1", "学生");
		params.put("Button1", "登录");
		HttpResult re = HttpClientUtils.post(httpClient, GdufLinks.JWC_LOGIN,
				null, params, "utf-8");
		System.out.println(re.getBody());
		Map<String, String> header = new HashMap<String, String>();
		header.put("referer", referer);
		header.put("Connection", "Keep-Alive");
		header.put("Host", "jwc.gduf.edu.cn");
		header.put("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");
		String code2 = HttpClientUtils.get(httpClient, referer, header, null)
				.getBody();
		// 提取姓名
		String xm = coursehtmlHandler.getXM(code2);
		System.out.println("姓名为" + xm);
		if (code2.contains("欢迎使用正方教务管理系统！请登录") || "".equals(xm)) {
			return null;
		}
		return xm;
	}

	/**
	 * 获取课程表数据，输入参数为年份和学期
	 */
	public Course getCourseData(String xh, String xm, String year, int xq)
			throws ClientProtocolException, IOException {
		Course course = new Course(xh, year, xq);
		// 个人课表的URL
		String url = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/xskbcx.aspx?xh="
				+ xh
				+ "&xm="
				+ URLEncoder.encode(xm, "utf-8")
				+ "&gnmkdm=N121603";

		String v = getViewState(xh, url);

		String tjUrl = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/tjkbcx.aspx?xh="
				+ xh
				+ "&xm="
				+ URLEncoder.encode(xm, "utf-8")
				+ "&gnmkdm=N121601";
		// TODO 此处需要修改，如果当前学年，显示专业推荐课表，否则显示个人课表
		
		String code;
		List<ClassBean> list;
		if (year.equals("2015-2016")) {
			code = getTuiJianCourseCode(xh, tjUrl, v);
			list = coursehtmlHandler.execute(code);
		} else {
			code = getCourseCode(xh, url, v, year, xq);
			list = coursehtmlHandler.execute(code);
		}
		Set<ClassBean> set = new HashSet<ClassBean>(list);
		course.setClasses(set);
		return course;

	}

	/**
	 * 专业推荐课表
	 * */
	private String getTuiJianCourseCode(String xh, String url,
			String __VIEWSTATE) {
		String referer = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/xs_main.aspx?xh="
				+ xh;

		Map<String, String> params = new HashMap<String, String>();
		params.put("__EVENTARGUMENT", "");
		params.put("__EVENTTARGET", "xqd");
		params.put("__VIEWSTATE", __VIEWSTATE);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html, application/xhtml+xml, */*");
		headers.put("Accept-Language", "zh-CN");
		headers.put("Cache-Control", "no-cache");
		headers.put("Connection", "Keep-Alive");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Host", "jwc.gduf.edu.cn");
		headers.put("Referer", referer);

		String code = null;
		try {
			code = HttpClientUtils.get(httpClient, url, headers, null)
					.getBody();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	/**
	 * 获取成绩单
	 */

	public Grade getGrade(String xh, String xm, String year, int xq)
			throws ClientProtocolException, IOException {
		String gradeurl = null;
		Grade grade = new Grade(xh, year, xq);
		gradeurl = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/xscjcx.aspx?xh="
				+ xh
				+ "&xm="
				+ URLEncoder.encode(xm, "utf-8")
				+ "&gnmkdm=N121605";

		String v = getViewState(xh, gradeurl);
		String code = getGradeCode(gradeurl, v, xh,year, xq);
		List<Subject> list = gradehtmlHandler.execute(code);
		Set<Subject> set = new HashSet<Subject>(list);
		grade.setSubjects(set);
		return grade;
	}

	/**
	 * 获取成绩单的数据源码
	 */
	private String getGradeCode(String url, String v, String xh,String year, int xq)
			throws ClientProtocolException, IOException {
		String referer = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/xs_main.aspx?xh="
				+ xh;
		String code = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("__EVENTARGUMENT", "");
		params.put("__EVENTTARGET", "");
		params.put("__VIEWSTATE", v);
		params.put("btn_xq", "学期成绩");
		params.put("ddlXN", year);
		params.put("ddlXQ", xq + "");
		params.put("ddl_kcxz", "");
		params.put("hidLanguage", "");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html, application/xhtml+xml, */*");
		headers.put("Accept-Language", "zh-CN");
		headers.put("Cache-Control", "no-cache");
		headers.put("Connection", "Keep-Alive");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Host", "jwc.gduf.edu.cn");
		headers.put("Referer", referer);
		code = HttpClientUtils.post(httpClient, url, headers, params, "utf-8").getBody();
		return code;

	}

	// 获取课程表,成绩表需要的参数ViewState
	public String getViewState(String xh, String url)
			throws ClientProtocolException, IOException {
		String referer = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/xs_main.aspx?xh="
				+ xh;
		SuperHtmlHandler htmlhandler = new SuperHtmlHandler();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html, application/xhtml+xml, */*");
		headers.put("Accept-Language", "zh-CN");
		headers.put("Cache-Control", "no-cache");
		headers.put("Connection", "Keep-Alive");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Host", "jwc.gduf.edu.cn");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Referer", referer);
		String code = HttpClientUtils.get(httpClient, url, headers, null)
				.getBody();
		String __VIEWSTATE = htmlhandler.getVIEWSTATE(code);
		return __VIEWSTATE;
		// HttpGet httpRequest = HttpHandler.getHttpGet(url);
		// httpRequest.setHeader("referer", referer);
		// httpRequest.setHeader("Host", "jwc.gduf.edu.cn");
		// httpRequest
		// .setHeader("User-Agent",
		// "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");
		// httpRequest.setHeader("Connection", "Keep-Alive");
		// httpRequest.setHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.5");
		// httpRequest.setHeader("Accept-Encoding", "gzip, deflate");
		// httpRequest
		// .setHeader("Accept", "text/html, application/xhtml+xml, */*");
		// HttpResponse httpResponse2;
		//
		// String code = null;
		// httpResponse2 = new HttpHandler().getResponse(httpRequest);
		// code = HttpHandler.getResultString(httpResponse2);
		// String __VIEWSTATE = null;
		// __VIEWSTATE = htmlHandler.getVIEWSTATE(code);
		// // System.out.println("__VIEWSTATE2:" + __VIEWSTATE);
		// // httpRequest.releaseConnection();

	}

	//
	// // 获取不同学期的课程表
	public String getCourseCode(String xh, String url, String __VIEWSTATE,
			String year, int xq) throws ClientProtocolException, IOException {
		String code = null;
		// 当处于默认学期时间，采用get方式，切换学期使用post方式
		// if ("2015-2016".equals(year) && xq == 2) {
		// HttpGet httpRequest = HttpHandler.getHttpGet(url);
		// httpRequest.setHeader("referer", referer);
		// httpRequest.setHeader("Host", "jwc.gduf.edu.cn");
		// httpRequest
		// .setHeader("User-Agent",
		// "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");
		// httpRequest.setHeader("Connection", "Keep-Alive");
		// httpRequest
		// .setHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.5");
		// httpRequest.setHeader("Accept-Encoding", "gzip, deflate");
		// httpRequest.setHeader("Accept",
		// "text/html, application/xhtml+xml, */*");
		// HttpResponse httpResponse2;
		//
		// httpResponse2 = new HttpHandler().getResponse(httpRequest);
		// code = HttpHandler.getResultString(httpResponse2);
		// httpRequest.releaseConnection();
		// System.out.println("code的值为:" + code);
		// } else {

		String referer = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/xs_main.aspx?xh="
				+ xh;

		Map<String, String> params = new HashMap<String, String>();
		params.put("__EVENTARGUMENT", "");
		params.put("__EVENTTARGET", "xqd");
		params.put("__VIEWSTATE", __VIEWSTATE);
		params.put("xnd", year);
		params.put("xqd", xq + "");

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html, application/xhtml+xml, */*");
		headers.put("Accept-Language", "zh-CN");
		headers.put("Cache-Control", "no-cache");
		headers.put("Connection", "Keep-Alive");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Host", "jwc.gduf.edu.cn");
		headers.put("Referer", referer);

		code = HttpClientUtils.post(httpClient, url, headers, params, "utf-8")
				.getBody();
		return code;

	}

	public User getUserInfo(String xh, String xm) {
		Map<String, String> headers = new HashMap<String, String>();

		String referer = "http://jwc.gduf.edu.cn/(1kr3xaag1lzthj45feqise55)/xs_main.aspx?xh="
				+ xh;

		headers.put("Accept", "text/html, application/xhtml+xml, */*");
		headers.put("Accept-Language", "zh-CN");
		headers.put("Cache-Control", "no-cache");
		headers.put("Connection", "Keep-Alive");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Host", "jwc.gduf.edu.cn");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Referer", referer);
		Map<String, String> params = new HashMap<String, String>();
		params.put("xh", xh);
		params.put("xm", xm);
		params.put("gnmkdm", "N121501");
		String html;
		try {
			html = HttpClientUtils.get(httpClient, GdufLinks.JWC_USER_INFO,
					headers, params).getBody();
			return UserInfoHtml.getUserInfo(html);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JWCHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

}
