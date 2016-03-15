package com.vanroid.gduf.util;

import java.util.List;

public class PageBean {
	private List<?> list; // 返回某一页记录列表
	private int allRow; // 总记录数
	private int totalPage; // 总页数
	private int currentPage; // 当前页
	private int pageSize; // 每页记录数
	private boolean isFristPage;// 是否第一页
	private boolean isLastPage;// 是否最后一页
	private boolean hasPreviousPage;// 是否有前一页
	private boolean hasNextPage;// 是否有后一页

	/**
	 * 初始化分页信息
	 * 
	 * @param isLastPage
	 * @param hasNextPage
	 * @param isFristPage
	 * @param hasPreviousPage
	 */
	public void init() {
		this.isFristPage = isFristPage;
		this.isLastPage = isLastPage;
		this.hasNextPage = hasNextPage;
		this.hasPreviousPage = hasPreviousPage;
	}

	/**
	 * 记录总页数
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param allRow
	 *            总记录数
	 * @return 总页数
	 */

	public static int countTotalPage(final int pageSize, final int allRow) {
		return allRow % pageSize == 0 ? (allRow / pageSize) : (allRow
				/ pageSize + 1);

	}

	/**
	 * 计算当前页开始的记录
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param currentPage
	 *            当前第几页
	 * @return 当前页开始的记录号
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		return (pageSize * (currentPage - 1));
	}

	/**
	 * 计算当前页,若为0或者请求的URL中没有“？page=”则用 1 代替
	 * 
	 * @param page
	 *            传入的参数(可能为空,即0,返回1)
	 * @return 当前页
	 */
	public static int countCurrentPage(int page) {
		return page == 0 ? 1 : page;
	}

	// setter,getter方法
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isFristPage() {
		return isFristPage;
	}

	public void setFristPage(boolean isFristPage) {
		this.isFristPage = isFristPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public PageBean() {

	}

	public PageBean(List<?> list, int allRow, int totalPage, int currentPage,
			int pageSize) {
		super();
		this.list = list;
		this.allRow = allRow;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

}
