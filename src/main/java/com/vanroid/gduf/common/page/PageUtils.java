package com.vanroid.gduf.common.page;

public class PageUtils {

	public static Page createPage(int totalRecord, int pageSize, int curPage) {
		int count = getTotaRecord(totalRecord);
		int size = getPageSize(pageSize);
		int cur = getcurPage(curPage);
		int totalPage = getTotalPage(count, size);
		int begin = getBegin(curPage, size);
		boolean next = hasNext(cur, totalPage);
		boolean pre = hasPre(cur);
		return new Page(count, cur, size, totalPage, begin, next, pre);
	}

	public static Page createPage(Page page, int totalRecord) {
		int count = getTotaRecord(totalRecord);
		int size = getPageSize(page.getPageSize());
		int cur = getcurPage(page.getCurPage());
		int totalPage = getTotalPage(totalRecord, size);
		int begin = getBegin(cur, size);
		boolean next = hasNext(cur, totalPage);
		boolean pre = hasPre(cur);
		return new Page(count, cur, size, totalPage, begin, next, pre);
	}

	private static int getTotaRecord(int totalRecord) {
		return totalRecord > 0 ? totalRecord : 0;
	}

	private static int getPageSize(int pageSize) {
		return pageSize > 0 ? pageSize : 10;
	}

	private static int getcurPage(int curPage) {
		return curPage > 0 ? curPage : 1;
	}

	private static int getTotalPage(int totalRecord, int pageSize) {
		if (totalRecord <= 0 || pageSize <= 0) {
			return 1;
		} else {
			int totalPage = totalRecord / pageSize;
			totalPage = (totalRecord % pageSize == 0 ? totalPage : totalPage + 1);
			return totalPage;
		}

	}

	private static int getBegin(int curPage, int pageSize) {
		return (curPage - 1) * pageSize;
	}

	private static boolean hasNext(int curPage, int totalPage) {
		return curPage >= totalPage ? false : true;
	}

	private static boolean hasPre(int curPage) {
		return curPage <= 1 ? false : true;
	}
}
