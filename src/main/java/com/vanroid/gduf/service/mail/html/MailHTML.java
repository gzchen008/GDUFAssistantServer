package com.vanroid.gduf.service.mail.html;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vanroid.gduf.common.GdufLinks;
import com.vanroid.gduf.entity.MailInfo;

/**
 * 处理邮件的HTML
 * 
 * @author CGZ
 *
 */
public class MailHTML {

	/**
	 * 所有邮件tr 一个tr对应网页的一个行
	 */
	private Elements mailTrs;
	/**
	 * 一行，即一条邮件记录对应的10个td
	 */
	private Elements mailTds;

	/**
	 * 解析html
	 * 
	 * @param resultStr
	 * @return 邮件列表
	 */
	public List<MailInfo> getMailList(String htmlStr) {
		List<MailInfo> list = new ArrayList<MailInfo>();

		// 转换HTMLy为Document对象
		Document doc = Jsoup.parse(htmlStr);
		mailTrs = doc.select("tr[class=toptr3]");
		for (Element mailTr : mailTrs) { // 遍历每行

			mailTds = mailTr.select("td");
			int i = 0;
			String sender = null;// 发件人
			int readFlag = 1; // 是否已读，1表示已读,0表示未读
			String title = null; // 主题
			String date = null; // 发件日期
			String size = null; // 大小
			boolean isAttach = false; // 是否有附件
			String attach = null; // 附件地址
			//String link = "http://www.gduf.edu.cn/mail/mail_read.jsp?"; // 主内容地址
			String link = null;
			String id = null; // 邮件ID
			String senderId = null; // 发件人ID
			for (Element mailTd : mailTds) { // 遍历每个单元格
				i++; // 单元格向下移
				if (i == 5) { // 发件人
					sender = mailTd.text();// 发件人名字
					// 根据是否加粗属性判断是否已读
					if (mailTd.select("strong").size() == 0) {// 未读
						readFlag = 0;
					} else {
						readFlag = 1;
					}
				} else if (i == 7) {
					title = mailTd.text();
				} else if (i == 8) {
					date = mailTd.text();
				} else if (i == 9) {
					size = mailTd.text();
				} else if (i == 10) { // 附件
					Elements attachEl = mailTd.select("a");
					/*if (attachEl.size() != 0
							&& attachEl.attr("href").length() > 4) { // 有附件
						isAttach = true; // 表示有邮件
						attach = attachEl.attr("href"); // 附件下载地址
						// ../downloadFile.jsp?link=mail/annex/2015\04\20/tzsjy2015042008572615&fileName=广东金融学院2014-2015学年第二学期第七周校园招聘安排表
						// .docx
						// 去除前面两个点，加上www.gduf.edu.cn
						attach = GdufLinks.GDUF_EDU_CN + attach.substring(3);
						attach = attach.replaceAll("\\\\", "/");
					}*/
					if(attachEl.size()!=0){	//有附件，可能为单个附件地址，也可能为多个附件，表示为#
						isAttach = true;
					}
				}

				// 获取内容地址
				// 其内容放在了onclick的属性里
				String strOnclick = mailTr.select("A[href=#]").attr("onclick");
				// 读邮件链接
				String href = "http://www.gduf.edu.cn/mail/mail_read.jsp?";
				// 以单引号为分解标志分解字符串
				String[] elements = strOnclick.split("'");
				// 获取邮件id
				id = elements[3];
				// 发件人id
				senderId = elements[7];
				// 内容链接
				/*link += "foldertype=" + elements[1] + "&id=" + elements[3]
						+ "&page=" + elements[5] + "&personId=" + elements[7]
						+ "&reply=" + elements[9] + "&transmit=" + elements[11]
						+ "&readFlag=" + elements[15];*/

			}
			// 加入模型
			//这里并没有放入附件地址
			MailInfo mailInfo = new MailInfo(id, link, sender, title, date,
					size, isAttach, null, senderId, readFlag);
			list.add(mailInfo);
		

		}

		return list;

	}

	/**
	 * 获取邮件
	 * @param code HTML代码
	 */
	public MailInfo getMail(String code) {
		
		return null;
		
	}

	/**
	 * 获取附件地址
	 * @param resultStr
	 * @return
	 */
	public List<String> getAttaches(String resultStr) {
		List<String> attaches; 
		Document doc = Jsoup.parse(resultStr);
		Elements elTdas = doc.select("td").select("a[target=_blank]");
		int size = elTdas.size();
		System.out.println("size:"+size);
		if(size == 0)
			return null;
		else{
			attaches = new ArrayList<String>();
			//附件地址
			String attach;
			for(Element tda : elTdas){
				//href属性
				attach = tda.attr("href");
				attach = GdufLinks.GDUF_EDU_CN + attach.substring(3);
				attach = attach.replaceAll("\\\\", "/");
				attaches.add(attach);
			}
		}
		return attaches;
	}

}
