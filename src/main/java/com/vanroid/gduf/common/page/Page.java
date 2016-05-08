package com.vanroid.gduf.common.page;

import java.util.List;

/**
 * 分页Bean 适用于社团管理,兼职就业,
 * 
 * @author joe
 *
 */
public class Page {
	private int totalRecord;
	private int curPage;
	private int pageSize;
	private int totalPage;
	private int begin;
	private boolean hasNext;
	private boolean hasPre;

	public Page() {
		super();
	}

	public Page(int totalRecord, int curPage, int pageSize, int totalPage, int begin, boolean hasNext, boolean hasPre) {
		super();
		this.totalRecord = totalRecord;
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.begin = begin;
		this.hasNext = hasNext;
		this.hasPre = hasPre;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPre() {
		return hasPre;
	}

	public void setHasPre(boolean hasPre) {
	}

	@Override
	public String toString() {
		return "Page [totalRecord=" + totalRecord + ", curPage=" + curPage + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + ", begin=" + begin + ", hasNext=" + hasNext + ", hasPre=" + hasPre + "]";
	}

}
