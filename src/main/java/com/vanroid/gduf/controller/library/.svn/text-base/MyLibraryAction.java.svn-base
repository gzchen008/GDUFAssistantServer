package com.vanroid.gduf.controller.library;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.entity.BookBorrowedInfo;
import com.vanroid.gduf.entity.LibraryUserInfo;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.exception.ValidateErrorException;
import com.vanroid.gduf.service.library.MyLibraryService;
import com.vanroid.gduf.util.HttpClientUtils;

/**
 * 我的图书馆控制器
 * 
 * @author CGZ
 * 
 */
@Controller("MyLibraryAction")
@Scope("prototype")
public class MyLibraryAction extends ActionSupport {
	/**
	 * 我的图书馆服务
	 */
	@Resource
	private MyLibraryService myLibraryService;
	/**
	 * 返回的json
	 */
	private Map resultMap = new HashMap();
	/**
	 * 登录我的图书馆验证码
	 */
	private String veriCode;
	/**
	 * 当前借阅书目列表
	 */
	private List<BookBorrowedInfo> bookBrrowedList;
	/**
	 * 历史借阅密码
	 */
	private String page;
	/**
	 * session
	 */
	private HttpSession session;
	/**
	 * HttpClient
	 */
	private HttpClient httpClient;

	public MyLibraryAction() {
		session = ServletActionContext.getRequest().getSession();
		httpClient = HttpClientUtils.getHttpClient(session, null);
	}

	/**
	 * 登录我的图书馆 无验证码版
	 * 
	 * @return
	 */
	public String mylibraryLogin() {
		// Session
		Map session = ActionContext.getContext().getSession();
		// 获取登录用户
		User user = (User) session.get("qtUser");
		LibraryUserInfo libraryUserInfo = myLibraryService.login(httpClient,
				user.getStuId(), user.getLibaryPass());
		if (libraryUserInfo == null) {
			throw new ValidateErrorException("图书馆密码不正确！");
		}
		resultMap.put("resultCode", "1");
		resultMap.put("libraryUserInfo", libraryUserInfo);
		return Action.SUCCESS;
	}

	/**
	 * 当前借阅情况
	 */
	public String bookBorrowed() {
		// Session
		Map session = ActionContext.getContext().getSession();
		// 取出HttpClient
		// 获取登录用户
		User user = (User) session.get("qtUser");
		// 登录
		myLibraryService.login(httpClient, user.getStuId(),
				user.getLibaryPass());
		bookBrrowedList = myLibraryService.findBookBorrowed(httpClient);
		return Action.SUCCESS;
	}

	/**
	 * 历史借阅
	 */
	public String bookborrowedhistory() {
		// Session
		Map session = ActionContext.getContext().getSession();
		// 获取登录用户
		User user = (User) session.get("qtUser");
		// 登录
		myLibraryService.login(httpClient, user.getStuId(),
				user.getLibaryPass());
		if (page == null)
			page = "1";
		bookBrrowedList = myLibraryService.findBookborrowedhistory(httpClient,
				page);
		return Action.SUCCESS;
	}

	/**
	 * 登录我的图书馆 有验证码版
	 */
	@Deprecated
	public String loginWithVeriCode() {
		/*
		 * // Session Map session = ActionContext.getContext().getSession(); //
		 * 取出HttpClient HttpClient httpClient = (HttpClient)
		 * session.get("userHttpClient"); myLibraryService = new
		 * MyLibraryService(httpClient); // 获取登录用户 UserInfo user = (UserInfo)
		 * session.get("user"); if (user == null) { // 未登录
		 * resultMap.put("resultCode", "0"); resultMap.put("msg",
		 * "必须先登录，才能访问我的图书馆"); return Action.SUCCESS; } if (veriCode == null)
		 * {// 未输入验证码 // 输出错误提示 resultMap.put("resultCode", "0");
		 * resultMap.put("msg", "验证码错误，请重新提交或访问showVeriCode换一张图"); return
		 * Action.SUCCESS; // 向用户输出验证码 } // 登录 int result =
		 * myLibraryService.login(user.getStuId(), user.getLibaryPass(),
		 * veriCode);
		 */
		return Action.SUCCESS;
	}

	/**
	 * 显示验证码 无返回值，直接返回输出流
	 */
	@Deprecated
	public void showVeriCode() {
		/*
		 * // Session Map session = ActionContext.getContext().getSession(); //
		 * 请求对象 HttpServletRequest request = ServletActionContext.getRequest();
		 * // 响应对象 HttpServletResponse response =
		 * ServletActionContext.getResponse();
		 * response.setContentType("image/gif"); // 输出流 ServletOutputStream
		 * outputStream = null; try { outputStream = response.getOutputStream();
		 * } catch (IOException e) { e.printStackTrace(); } // 向用户发送验证码
		 * myLibraryService.sendVeriCode(outputStream);
		 */
	}

	public Map getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}

	public String getVeriCode() {
		return veriCode;
	}

	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

	public List<BookBorrowedInfo> getBookBrrowedList() {
		return bookBrrowedList;
	}

	public void setBookBrrowedList(List<BookBorrowedInfo> bookBrrowedList) {
		this.bookBrrowedList = bookBrrowedList;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}
