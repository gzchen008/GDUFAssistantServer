package com.vanroid.gduf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 图书模型
 * 
 * @author CGZ
 * 
 */
@Entity
@Table(name = "gd_book")
public class Book {
	/**
	 * 主键Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private int id; // 书的序号
	private String title;
	private String author; // 作者
	private String press; // 出版社
	private String pressDate; // 出版时间
	private String number; // 索取号
	private int dpt; // 馆藏号
	private int borrowableCount; // 可借数
	private String href; // 详细链接

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPressDate() {
		return pressDate;
	}

	public void setPressDate(String pressDate) {
		this.pressDate = pressDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getDpt() {
		return dpt;
	}

	public void setDpt(int dpt) {
		this.dpt = dpt;
	}

	public int getBorrowableCount() {
		return borrowableCount;
	}

	public void setBorrowableCount(int borrowableCount) {
		this.borrowableCount = borrowableCount;
	}

}
