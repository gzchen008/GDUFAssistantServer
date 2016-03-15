package com.vanroid.gduf.service.library.html;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vanroid.gduf.entity.BookBorrowedInfo;

public class BookBorrowedHistoryHTML {

	/**
	 * 当前借阅情况HTML解析
	 * 
	 * @param code
	 * @return
	 */
	public List<BookBorrowedInfo> analysis(String code) {
		Document doc = Jsoup.parse(code);
		Elements tbody = doc.select("tbody");
		Elements trs = tbody.select("tr");
		BookBorrowedInfo bookBorrowedInfo;
		List<BookBorrowedInfo> list = new ArrayList<BookBorrowedInfo>();
		for (Element tr : trs) {
			
			bookBorrowedInfo = new BookBorrowedInfo();
			//遍历每列
			Elements tds = tr.select("td");
			if(tds.size()!=5)		//5列才是需要的结果
				break;
			bookBorrowedInfo.setBookId(tds.get(4).text());
			bookBorrowedInfo.setDeathDate(tds.get(1).text());
			bookBorrowedInfo.setTitle(tds.get(2).text());
			bookBorrowedInfo.setBookType(tds.get(3).text());
			bookBorrowedInfo.setBorrowDate(tds.get(0).text());
			list.add(bookBorrowedInfo);
		}
	
		return list;

	}

	
}
