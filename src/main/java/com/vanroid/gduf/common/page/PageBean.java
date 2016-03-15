package com.vanroid.gduf.common.page;

import java.util.List;

/**
 * 存放翻页数据
 * 分页Bean
 * 可用于邮件、图书等
 * @author CGZ
 *
 */
public class PageBean<T> {
	/**
	 * 当前页码
	 */
	private int pageCurrent;
	/**
	 * 总记录数
	 */
	private int total;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	/**
	 * 请求路径及参数
	 */
	private String url;
	/**
	 * 数据列表
	 */
	private List<T> beanList;
	/**
	 * 关键字
	 * 
	 */
	private String keywords;
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
