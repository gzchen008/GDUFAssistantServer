package com.vanroid.gduf.controller.library;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.entity.LibrarySearchHistory;
import com.vanroid.gduf.service.library.LibrarySearchService;
import com.vanroid.gduf.util.HttpClientUtils;
/**
 * 
* @ClassName LibrarySearchAction.java Create on 2015-9-3     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author CGZ vsjosonadmin@163.com  
*  
* @Description: 
*
* @version 1.0
 */
@Controller("LibrarySearchAction")
@Scope("prototype")
public class LibrarySearchAction extends ActionSupport {
	/**
	 * 搜索关键字
	 */
	private String keywords;
	/**
	 * 页码
	 */
	private int page;
	/**
	 * 返回的书列表
	 */
	private LibrarySearchHistory librarySearchHistory;
	/**
	 * 图书馆服务对象
	 */
	@Resource
	private LibrarySearchService librarySearchService ;
	/**
	 * session
	 */
	private HttpSession session;
	/**
	 * HttpClient
	 */
	private HttpClient httpClient;

	public LibrarySearchAction() {
		session = ServletActionContext.getRequest().getSession();
		httpClient = HttpClientUtils.getHttpClient(session, null);
	}
	/**
	 * 搜索方法 参数为页码、关键字
	 */
	public String search() {
		if (page == 0) {//没有赋值
			page = 1;
		}
		librarySearchHistory = librarySearchService.search(httpClient,keywords, page);
		return Action.SUCCESS;
	}


	public LibrarySearchHistory getLibrarySearchHistory() {
		return librarySearchHistory;
	}

	public void setLibrarySearchHistory(LibrarySearchHistory librarySearchHistory) {
		this.librarySearchHistory = librarySearchHistory;
	}

	public LibrarySearchService getLibrarySearchService() {
		return librarySearchService;
	}

	public void setLibrarySearchService(
			LibrarySearchService librarySearchService) {
		this.librarySearchService = librarySearchService;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
