package com.vanroid.gduf.service.library;

import java.util.List;

import org.apache.http.client.HttpClient;

import com.vanroid.gduf.entity.BookBorrowedInfo;
import com.vanroid.gduf.entity.LibraryUserInfo;

public interface MyLibraryService {
	/**
	 * 登录我的图书馆方法
	 * @param stuId
	 * @param password
	 * @return
	 */
	public LibraryUserInfo login(HttpClient httpClient,String stuId, String password);

	/**
	 * 当前借阅
	 * @param httpClient
	 * @return
	 */
	public List<BookBorrowedInfo> findBookBorrowed(HttpClient httpClient);

	/**
	 * 列出借阅历史
	 * @param httpClient
	 * @param page
	 * @return
	 */
	public List<BookBorrowedInfo> findBookborrowedhistory(HttpClient httpClient,String page);
}
