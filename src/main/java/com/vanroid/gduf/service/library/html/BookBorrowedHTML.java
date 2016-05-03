package com.vanroid.gduf.service.library.html;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vanroid.gduf.entity.BookBorrowedInfo;

public class BookBorrowedHTML {

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
			// 遍历每列
			Elements tds = tr.select("td");
			if (tds.size() != 7) // 最后一行只有两列
				break;
			if ("续满".equals(tds.get(0).text().trim())) {// 续满
				bookBorrowedInfo.setReNew("续满");
			} else { // 设置为input的name属性
				bookBorrowedInfo.setReNew(tds.get(0).select("input")
						.attr("name"));
			}

			bookBorrowedInfo.setDeathDate(tds.get(1).text());
			bookBorrowedInfo.setTitle(tds.get(2).text());
			bookBorrowedInfo.setBookType(tds.get(4).text());
			bookBorrowedInfo.setBookId(tds.get(5).text());
			bookBorrowedInfo.setBorrowDate(tds.get(6).text());
			list.add(bookBorrowedInfo);
		}
		return list;

	}

}
