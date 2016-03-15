package com.vanroid.gduf.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailUtil {
	public static final String HOST = "smtp.qq.com";
	public static final String PROTOCOL = "smtp";
	public static final int PORT = 587;
	public static final String SENDER = "526410281@qq.com";
	public static final String SENDERPWD = "ssqzaec360";

	public static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);// 设置服务地址
		props.put("mail.store.protocol", PROTOCOL);// 设置协议
		props.put("mail.smtp.port", PORT);// 设置端口
		props.put("mail.smtp.auth", true);

		Authenticator authenticator = new Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER, SENDERPWD);
			};
		};
		Session session = Session.getDefaultInstance(props, authenticator);
		return session;
	}

	/**
	 * 发邮件
	 * 
	 * @param receiver
	 * @param content
	 */
	public static void send(String receiver, String content) {
		Session session = getSession();
		try {
			System.out.println("-------开始发送邮件--------");
			Message msg = new MimeMessage(session);
			// 设置message属性
			msg.setFrom(new InternetAddress(SENDER));
			InternetAddress[] addrs = { new InternetAddress(receiver) };
			msg.setRecipients(Message.RecipientType.TO, addrs);
			msg.setSubject("广金校园通-社团管理员帐号激活");
			msg.setSentDate(new Date());
			msg.setContent(content, "text/html;charset=UTF-8");
			// 开始发送
			Transport.send(msg);
			System.out.println("----------发送邮件成功---------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发送失败" + e.toString());
		}
	}
}
