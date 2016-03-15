package com.vanroid.gduf.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author CGZ
 *图书馆搜索历史
 */
@Entity
@Table(name="gd_library_search_history")
public class LibrarySearchHistory {
	/**
	 * 主键
	 */
	private int id;	
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * 页码
	 */
	private int page;
	/**
	 * 对应关键字总记录
	 */
	private int total;
	/**
	 * 
	 * 对应的查询到的书
	 */
	private Set<BookInfo> books;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="hid",nullable=false)
	public Set<BookInfo> getBooks() {
		return books;
	}
	public void setBooks(Set<BookInfo> books) {
		this.books = books;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "LibrarySearchHistory [id=" + id + ", keywords=" + keywords
				+ ", page=" + page + ", total=" + total + ", books=" + books
				+ "]";
	}
	
	
}
