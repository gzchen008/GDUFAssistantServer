package com.vanroid.gduf.service.library.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.vanroid.gduf.entity.LibraryUserInfo;

/**
 * 图书馆用户信息HTML处理器
 * @author CGZ
 *
 */
public class LibraryUserInfoHTML {
	/**
	 * 用户信息模型
	 */
	private LibraryUserInfo libraryUserInfo;

	/**
	 * 
	 * @param code HTML代码
	 * @return 用户信息
	 */
	public LibraryUserInfo analysis(String code) {
		if(code == null)
			return null;
		libraryUserInfo = new LibraryUserInfo();
		Document doc = Jsoup.parse(code);
		Elements spans = doc.select("span[class=inforight");
		if(spans.size() == 0)
			return null;
		libraryUserInfo.setStuId(spans.get(0).text());
		libraryUserInfo.setName(spans.get(1).text());
		libraryUserInfo.setDept(spans.get(3).text());
		libraryUserInfo.setStatus(spans.get(4).text());
		libraryUserInfo.setBalance(spans.get(10).text());

		return libraryUserInfo;
	}

}
