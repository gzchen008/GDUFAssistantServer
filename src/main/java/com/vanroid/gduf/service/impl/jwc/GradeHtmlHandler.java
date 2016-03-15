package com.vanroid.gduf.service.impl.jwc;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.vanroid.gduf.entity.Subject;

/**
 * 
* @ClassName GradeHtmlHandler.java Create on 2015年8月29日     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author Jerry 983951558@qq.com  
*  
* @Description:   获取成绩表的html文件进行解析、封装，为JWCHandler类调用
*
* @version 1.0
 */
public class GradeHtmlHandler extends SuperHtmlHandler{
	private List<Subject> list;

	public List<Subject> execute(String code) {
		// TODO Auto-generated method stub
		list = new ArrayList<Subject>();

		Document doc = Jsoup.parse(code);
		// 提取表格中的行
		Elements trEle = doc.select("tr");
		for (Element e : trEle) {
			String t = e.text();
			if (t.contains("必修") || t.contains("通选")||t.contains("公共选修")) {// 筛选的表格行含有这两个字符为有效的课程成绩
				// System.out.println(t);
				Subject gd = new Subject();
				String[] message = t.split(" ");// 遇空格分割
				gd.setGname(message[3]);
				gd.setXf(Double.parseDouble(message[6]));
				try {
					gd.setJd(Double.parseDouble(message[7]));
				} catch (Exception ee) {
					gd.setJd(0); // 通选课没有几点，转换为double会报错，将几点设置为0
				}
				gd.setCj(Double.parseDouble(message[8]));
				gd.setDept(message[12]);
				list.add(gd);
			}

		}

		return list;
	}

}
