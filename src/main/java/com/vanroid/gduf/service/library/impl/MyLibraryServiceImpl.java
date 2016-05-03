package com.vanroid.gduf.service.library.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vanroid.gduf.common.GdufLinks;
import com.vanroid.gduf.entity.BookBorrowedInfo;
import com.vanroid.gduf.entity.LibraryUserInfo;
import com.vanroid.gduf.service.library.MyLibraryService;
import com.vanroid.gduf.service.library.html.BookBorrowedHTML;
import com.vanroid.gduf.service.library.html.BookBorrowedHistoryHTML;
import com.vanroid.gduf.service.library.html.LibraryUserInfoHTML;
import com.vanroid.gduf.util.HttpUtil;

/**
 * 
 * @ClassName MyLibraryService.java Create on 2015-8-29
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 我的图书馆服务类
 * 
 * @version 1.0
 */
@Service("myLibraryService")
public class MyLibraryServiceImpl implements MyLibraryService {

	/**
	 * 向客户端发送验证码
	 * 
	 * @param outputStream
	 */
	/*
	 * @Deprecated public void sendVeriCode(HttpClient
	 * httpClient,ServletOutputStream outputStream) { // 生成验证码地址 String url =
	 * "http://218.192.12.92/gencheckcode.aspx"; //TODO HttpGet httpGet = new
	 * HttpGet(url); HttpResponse resp = null; try { resp =
	 * httpClient.execute(httpGet); resp.getEntity().writeTo(outputStream); }
	 * catch (IOException e) { e.printStackTrace(); }
	 * 
	 * System.out.println("isStream" + resp.getEntity().isStreaming()); }
	 */

	/***
	 * 登录图书馆方法
	 * 
	 * @param stuId
	 * @param libaryPass
	 * @param veriCode
	 * @return 登录结果 0表示验证码错误 1表示用户名或密码错误 2表示登录成功
	 */
	@Deprecated
	public int login(HttpClient httpClient, String stuId, String libaryPass,
			String veriCode) {
		String url = "http://218.192.12.92/login.aspx?ReturnUrl=%2fuser%2fuserinfo.aspx&__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUKMTI3MjA1NDY3Mg9kFgJmD2QWDGYPZBYCAgEPFgIeBGhyZWYFDWNzcy9zdHlsZS5jc3NkAgEPDxYCHghJbWFnZVVybAUbflxpbWFnZXNcaGVhZGVyb3BhYzRnaWYuZ2lmZGQCAg8PFgIeBFRleHQFLeW5v%2BS4nOmHkeiejeWtpumZouWbvuS5pummhuS5puebruajgOe0ouezu%2Be7n2RkAgMPDxYCHwIFGzIwMTXlubQwNOaciDI15pelIOaYn%2Bacn%2BWFrWRkAgQPZBYEZg9kFgQCAQ8WAh4LXyFJdGVtQ291bnQCBxYQAgEPZBYCZg8VAwtzZWFyY2guYXNweAAM55uu5b2V5qOA57SiZAICD2QWAmYPFQMOY2xzYnJvd3NlLmFzcHgADOWIhuexu%2BWvvOiIqmQCAw9kFgJmDxUDDmJvb2tfcmFuay5hc3B4AAzor7vkuabmjIflvJVkAgQPZBYCZg8VAwl4c3RiLmFzcHgADOaWsOS5pumAmuaKpWQCBQ9kFgJmDxUDFHJlYWRlcnJlY29tbWVuZC5hc3B4AAzor7vogIXojZDotK1kAgYPZBYCZg8VAxNvdmVyZHVlYm9va3NfZi5hc3B4AAzmj5DphpLmnI3liqFkAgcPZBYCZg8VAxJ1c2VyL3VzZXJpbmZvLmFzcHgAD%2BaIkeeahOWbvuS5pummhmQCCA9kFgICAQ8WAh4HVmlzaWJsZWhkAgMPFgIfA2ZkAgEPZBYEAgEPZBYIZg8PZBYCHgxhdXRvY29tcGxldGUFA29mZmQCAQ8PFgIeDEVycm9yTWVzc2FnZQUP6K%2B36L6T5YWl6K%2BB5Y%2B3ZGQCAw8PFgIfBgUP6K%2B36L6T5YWl5a%2BG56CBZGQCBQ8PFgIfAmVkZAICD2QWCmYPEGRkFgFmZAIBDxBkZBYBZmQCAg8PZBYCHwUFA29mZmQCAw8PFgIfBgUP6K%2B36L6T5YWl6K%2BB5Y%2B3ZGQCBQ9kFgICAg8PFgIfBgUP6K%2B36L6T5YWl5a%2BG56CBZGQCBQ8PFgIfAgWlAUNvcHlyaWdodCAmY29weTsyMDA4LTIwMDkuIFNVTENNSVMgT1BBQyA0LjAxIG9mIFNoZW56aGVuIFVuaXZlcnNpdHkgTGlicmFyeS4gIEFsbCByaWdodHMgcmVzZXJ2ZWQuPGJyIC8%2B54mI5p2D5omA5pyJ77ya5rex5Zyz5aSn5a2m5Zu%2B5Lmm6aaGIEUtbWFpbDpzenVsaWJAc3p1LmVkdS5jbmRkZCv%2FIu32JruOW%2F61jbigFrEltuAr&__VIEWSTATEGENERATOR=C2EE9ABB&__EVENTVALIDATION=%2FwEWBgLepLH%2BCAKOmK5RApX9wcYGAsP9wL8JApLM7boDAqW86pcIXfF63wNd8bZkVlLsz4CKLVIoVEg%3D&ctl00%24ContentPlaceHolder1%24txtlogintype=0&ctl00%24ContentPlaceHolder1%24txtUsername_Lib=131545230&ctl00%24ContentPlaceHolder1%24txtPas_Lib=123456&ctl00%24ContentPlaceHolder1%24txtCode=aaaa&ctl00%24ContentPlaceHolder1%24btnLogin_Lib=%E7%99%BB%E5%BD%95";
		// 替换字符串
		url = url.replace("131545230", stuId).replace("123456", libaryPass)
				.replace("aaaa", veriCode);
		// TODO
		HttpGet httpGet = new HttpGet(url);
		// 服务器响应
		HttpResponse resp = null;
		// 提交数据
		try {
			resp = httpClient.execute(httpGet);
			// TODO
			String result = "";
			if (result.contains(stuId)) {

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 无验证码登录方法
	 * 
	 * @param stuId
	 * @param password
	 * @return null 用户名或密码错误 LibraryUserInfo 登录成功
	 */
	@Override
	public LibraryUserInfo login(HttpClient httpClient, String stuId,
			String password) {
		// 个人信息
		LibraryUserInfo libraryUserInfo = new LibraryUserInfo();
		// 替换掉学号和密码
		String url = GdufLinks.MyLIBRARY_WITHOUT_VEICODE.replace("131545230",
				stuId).replace("123456", password);
		HttpGet httpGet = HttpUtil.getHttpGet(httpClient, url);
		// 服务器响应
		HttpResponse resp = null;
		// 提交数据
		try {
			resp = httpClient.execute(httpGet);
			String result = HttpUtil.getResultString(resp);
			if (result.contains("口令错误") || result.contains("没有您的读者帐户")) {
				return null;
			} else {
				// 获取个人信息
				httpGet = HttpUtil.getHttpGet(httpClient,
						"http://218.192.12.92/user/userinfo.aspx");
				resp = httpClient.execute(httpGet);
				result = HttpUtil.getResultString(resp);
				LibraryUserInfoHTML libraryUserInfoHTML = new LibraryUserInfoHTML();
				libraryUserInfo = libraryUserInfoHTML.analysis(result);
				return libraryUserInfo;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 列出所有已借书情况
	 */
	@Override
	public List<BookBorrowedInfo> findBookBorrowed(HttpClient httpClient) {
		String url = "http://218.192.12.92/user/bookborrowed.aspx";
		HttpGet httpGet = HttpUtil.getHttpGet(httpClient, url);
		HttpResponse resp;
		List<BookBorrowedInfo> beanList = null;
		try {
			resp = httpClient.execute(httpGet);
			String result = HttpUtil.getResultString(resp);
			BookBorrowedHTML bookBorrowedHTML = new BookBorrowedHTML();
			beanList = bookBorrowedHTML.analysis(result);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return beanList;
	}

	/**
	 * 通过页码获取当页历史已借书籍
	 * 
	 * @param page
	 * @return
	 */
	@Override
	public List<BookBorrowedInfo> findBookborrowedhistory(
			HttpClient httpClient, String page) {
		String url = "http://218.192.12.92/user/bookborrowedhistory.aspx?page="
				+ page;
		HttpGet httpGet = HttpUtil.getHttpGet(httpClient, url);
		HttpResponse resp;
		List<BookBorrowedInfo> beanList = null;
		try {
			resp = httpClient.execute(httpGet);
			String result = HttpUtil.getResultString(resp);
			BookBorrowedHistoryHTML bookBorrowedHTML = new BookBorrowedHistoryHTML();
			beanList = bookBorrowedHTML.analysis(result);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return beanList;
	}

}
