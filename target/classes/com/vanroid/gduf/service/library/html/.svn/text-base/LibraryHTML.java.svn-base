package com.vanroid.gduf.service.library.html;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vanroid.gduf.entity.BookInfo;

/**
 * 图书馆HTML处理器
 * 
 * @author CGZ
 *
 */

public class LibraryHTML {

	/**
	 * 书目列表
	 */
	private ArrayList<BookInfo> list;
	/**
	 * 书的序号
	 */
	private int sortId;
	/**
	 * 书名
	 */
	private String title;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 出版社
	 */
	private String press;
	/**
	 * 出版日期
	 */
	private String pressDate;
	/**
	 * 详细链接
	 */
	private String href;
	/**
	 * 书总数
	 */
	private static String booksCount;

	public List<BookInfo> analysis(String code) {
		list = new ArrayList<BookInfo>();
		Document doc = Jsoup.parse(code);
		Elements tdLinks = doc.select("td");

		Element element = null;
		for (int i = 9; i < tdLinks.size(); i++) {
			switch (i % 9) {
			case 0:
				element = tdLinks.get(i);
				sortId = Integer.parseInt(element.text());
				break;
			case 1:
				element = tdLinks.get(i);
				title = element.text();
				href = "http://218.192.12.92/"
						+ element.select("a").attr("href");
				break;
			case 2:
				element = tdLinks.get(i);
				author = element.text();
				break;
			case 3:
				element = tdLinks.get(i);
				press = element.text();
				break;
			case 4:
				element = tdLinks.get(i);
				pressDate = element.text();

				// 建立实例
				list.add(new BookInfo(sortId, title, author, press, pressDate, href));
				break;
			}

		}
		// 书总数
		Elements spanLinks = doc
				.select("span[id=ctl00_ContentPlaceHolder1_countlbl]");
		booksCount = spanLinks.text();

		return list;
	}

	public static String getBooksCount() {
		return booksCount;
	}

	public static void setBooksCount(String booksCount) {
		LibraryHTML.booksCount = booksCount;
	}

}
