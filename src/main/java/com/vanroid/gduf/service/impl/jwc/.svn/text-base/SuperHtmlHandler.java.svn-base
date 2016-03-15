package com.vanroid.gduf.service.impl.jwc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 * 
* @ClassName SuperHtmlHandler.java Create on 2015年8月29日     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author Jerry 983951558@qq.com  
*  
* @Description:   为CourseHtmlHandler和GradeHtmlHandler的父类，
* 				      提供获取viewstate值等公用功能
*
* @version 1.0
 */
public class SuperHtmlHandler {
	private String VIEWSTATE;
	private Document doc;
	public String getVIEWSTATE(String code) {
		doc = Jsoup.parse(code);
		Elements elements = doc.select("INPUT[type=hidden][name=__VIEWSTATE]");
		VIEWSTATE = elements.attr("value");
		return VIEWSTATE;
	}


	public void setVIEWSTATE(String vIEWSTATE) {
		VIEWSTATE = vIEWSTATE;
	}


	public String getEVENTARGUMEN(String code) {
		doc = Jsoup.parse(code);
		Elements elements = doc.select("INPUT[type=hidden][name=__EVENTARGUMENT]");
		return  elements.attr("value");
		
	}


	public String get__EVENTTARGET(String code) {
		doc = Jsoup.parse(code);
		Elements elements = doc.select("INPUT[type=hidden][name=__EVENTTARGET]");
		return elements.attr("value");
		
	}

	//提取姓名
	public String getXM(String code) {
		doc = Jsoup.parse(code);
		Elements elements = doc.select("SPAN[id=xhxm]");
		return elements.text().replace("同学", "");
	}

}
