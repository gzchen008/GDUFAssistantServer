package com.vanroid.gduf.service.impl.jwc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vanroid.gduf.entity.ClassBean;

/**
 * 
 * @ClassName CourseHtmlHandler.java Create on 2015年8月28日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: TODO(课程表-网页上的数据解析处理者，为JWCHandler类调用)
 * 
 * @version 1.0
 */

public class CourseHtmlHandler extends SuperHtmlHandler {

	private int howLong;
	private String[] data; // 分割后的数据
	private ArrayList<ClassBean> list;

	public List<ClassBean> execute(String code) {
		list = new ArrayList<ClassBean>();
		Document doc = Jsoup.parse(code);
		// 提取表格中的行
		Elements trEle = doc.select("tr");
		// 行中的列
		Elements tdEles;
		// 从第三行开始为课程内容

		/**
		 * 思路，只把课提取出来，每个课模型中有星期几，第几节开始，持续几节
		 */
		for (int i = 2; i < trEle.size(); i++) { // 遍历行
			tdEles = trEle.get(i).select("td[align=Center]"); // 提取当前行的列
			for (Element tdUnit : tdEles)
				// 提取每一个单元格
				if (!tdUnit.text().contains("调"))//删除被调的课程，即无效的课程
					getCoureseModel(tdUnit); // 处理每一个单元格

		}
		if (list.size() == 0)
			return null;
		return list;
	}

	private void getCoureseModel(Element tdEle) {
		// 提取Html中有关课程的信息
		String courseText = tdEle.text();
		if (courseText.length() < 5) // 排除星期一等无用td
			return;
		String html = tdEle.html();
		// 用<br/>分割字符串，分离课目，教室等
		data = html.split("<br>");

		boolean isSingleWeek = courseText.contains("单周");
		boolean isDoubleWeek = courseText.contains("双周");
		if (isSingleWeek && isDoubleWeek) { // 单双周均有课且不同种类
			// 处理两个周方法
			handlerDoubleWeek();
			return;
		}

		if (isSingleWeek) { // 仅单周
			// 参数表示单周
			handlerSingleWeek(1, data);
			return;
		}
		if (isDoubleWeek) { // 仅双周
			// 参数表示双周
			handlerSingleWeek(2, data);
			return;

		}

		handlerSingleWeek(0, data); // 每周都有

	}

	// 处理两个周且内容不同，生成两个Model
	private void handlerDoubleWeek() {
		// Log.i("data.lenght", data.length+"");
		// 复制数据的前4个数据
		String[] data1 = Arrays.copyOf(data, 4);
		// 作为单周处理
		handlerSingleWeek(1, data1);
		// 复制数级后4个数据 ,有一项空白项，所以从5开始
		String[] data2 = Arrays.copyOfRange(data, 5, 9);
		// 作为双周处理
		handlerSingleWeek(2, data2);
	}

	// 单周，双周，每周的处理方法
	private void handlerSingleWeek(int whichWeek, String[] data) {
		ClassBean c = new ClassBean();
		c.setWhichweek(whichWeek); // 设置周标志
		c.setTitle(data[0]);
		c.setWhichday(getWhichDay(data[1]));
		c.setCwhen(getWhen_howLong(data[1]));
		c.setHowlong(howLong);
		if (data.length > 3)
			c.setClassroom(data[3]);
		// 将对象加入列表
		list.add(c);
	}

	private int getWhen_howLong(String data) {
		if (data.length() == 0)
			return 0;
		if (!(data.charAt(0) + "").equals("周"))
			return 0;
		// 第1,2节
		int pos1 = data.indexOf("第");
		int pos2 = data.indexOf("节");
		String datas = data.substring(pos1 + 1, pos2);
		String[] nums = datas.split(",");
		howLong = nums.length; // 持续节数
		return Integer.parseInt(nums[0]);
	}

	private int getWhichDay(String data) {
		// 提取周几
		if (data.length() == 0)
			return 0;
		String whichDay = data.substring(0, 2);
		if ("周日".equals(whichDay))
			return 0;
		if ("周一".equals(whichDay))
			return 1;
		if ("周二".equals(whichDay))
			return 2;
		if ("周三".equals(whichDay))
			return 3;
		if ("周四".equals(whichDay))
			return 4;
		if ("周五".equals(whichDay))
			return 5;
		if ("周六".equals(whichDay))
			return 6;
		return -1;
	}

}
