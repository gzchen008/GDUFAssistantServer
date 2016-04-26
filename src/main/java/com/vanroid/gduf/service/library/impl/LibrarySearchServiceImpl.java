package com.vanroid.gduf.service.library.impl;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.common.GdufLinks;
import com.vanroid.gduf.common.page.PageBean;
import com.vanroid.gduf.dao.libary.LibrarySearchDao;
import com.vanroid.gduf.entity.BookInfo;
import com.vanroid.gduf.entity.LibrarySearchHistory;
import com.vanroid.gduf.service.library.LibrarySearchService;
import com.vanroid.gduf.service.library.html.LibraryHTML;
import com.vanroid.gduf.util.HttpUtil;

/**
 * 
 * @ClassName LibrarySearchService.java Create on 2015-9-3
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 图书搜索服务
 * 
 * @version 1.0
 */
@Service
public class LibrarySearchServiceImpl implements LibrarySearchService {
	/**
	 * 图书管数据访问层
	 */
	@Resource
	private LibrarySearchDao librarySearchDao;

	/**
	 * 通过关键字搜索图书方法
	 * 
	 * @param keywords
	 *            搜索关键字
	 * @param page
	 *            页码
	 * @return
	 */
	@Override
	public LibrarySearchHistory search(HttpClient httpClient, String keywords,
			int page) {
		LibrarySearchHistory librarySearchHistoryInCache = librarySearchDao.search(
				keywords, page);
		if (librarySearchHistoryInCache != null) { // 如果数据库中有缓存相关数据
			return librarySearchHistoryInCache;
		}

		// 书目列表
		List<BookInfo> list = null;
		final LibrarySearchHistory librarySearchHistory = new LibrarySearchHistory();
		// 设置关键字
		librarySearchHistory.setKeywords(keywords);
		// 搜索地址
		String url = GdufLinks.LIBRARY_SEARCH
				+ "?anywords="
				+ "mykeywords&dt=ALL&cl=ALL&dept=ALL&sf=M_PUB_YEAR&ob=DESC&page=mypage&dp=20&sm=table";
		try {
			keywords = URLEncoder.encode(keywords, "gbk");
			// 替换关键字和页数
			url = url.replace("mykeywords", keywords);
			url = url.replace("mypage", page + "");

			HttpGet httpGet = HttpUtil.getHttpGet(httpClient, url);
			// 返回的html字符串
			String result = null;
			HttpResponse response = httpClient.execute(httpGet);
			if (HttpUtil.isOK(response)) {
				result = HttpUtil.getResultString(response);
				LibraryHTML libraryHTML = new LibraryHTML();
				list = libraryHTML.analysis(result);
				// 设置值
				librarySearchHistory.setBooks(list);
				// 设置总记录数
				librarySearchHistory.setTotal(Integer.parseInt(libraryHTML.getBooksCount()));
				// 设置当前页
				librarySearchHistory.setPage(page);
				// 存入数据库
				librarySearchDao.save(librarySearchHistory);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return librarySearchHistory;
	}

}
