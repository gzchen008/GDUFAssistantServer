/*package com.vanroid.gduf.test.service.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.CharSetUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanroid.gduf.entity.MailInfo;
import com.vanroid.gduf.service.mail.MailService;
import com.vanroid.gduf.util.HttpClientUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MailServiceTest {
	@Resource
	private MailService mailService;

	@Test
	public void testLogin() throws Exception {
		boolean re = mailService.login(
				HttpClientUtils.createDefaultHttpClient(null), "131545230",
				"5201314cy");
		System.out.println(re);
	}

	@Test
	public void testGetMail() {
		CloseableHttpClient client = HttpClientUtils
				.createDefaultHttpClient(null);
		boolean re = mailService.login(client, "131545230", "5201314cy");
		List<MailInfo> ls = mailService.getMails(client, 1);
		System.out.println(ls.get(0).getTitle());
	}

	@Test
	public void testSendMail() {
		CloseableHttpClient client = HttpClientUtils
				.createDefaultHttpClient(null);
		boolean re = mailService.login(client, "131545230", "5201314cy");
		System.out.println(re);

		
		 * Map<String, String> params = new HashMap<String, String>();
		 * params.put("title", "我能发邮件了"); params.put("addr", "57202");
		 * params.put("addr2", ""); params.put("count", "0");
		 * params.put("replyName", "陈广镇"); params.put("content", "hello");
		 

		String url = "http://www.gduf.edu.cn/mail/mail_addok.jsp";

		Map<String, String> headers = new HashMap<String, String>();
		MultipartEntityBuilder meb = MultipartEntityBuilder.create();
		ContentType contentType = ContentType.create("");

		try {
			meb.addPart("title", new StringBody("我能发邮件了gbk",Charset.forName("GBK")));
		
		meb.addPart("addr", new StringBody("黄晓强",Charset.forName(HTTP.ISO_8859_1)));
	
		meb.addPart("addr2", new StringBody("",contentType));
		meb.addPart("count", new StringBody("0",contentType));
		meb.addPart("replyName", new StringBody("陈广镇",contentType));
		meb.addPart("content", new StringBody("hello,中文乱码",ContentType.MULTIPART_FORM_DATA));
		meb.addPart("addr_id", new StringBody("57202",contentType));
		meb.addPart("copy_id", new StringBody("",contentType));
		meb.addPart("secret_id", new StringBody("",contentType));
		meb.addPart("addr_class", new StringBody("",contentType));
		meb.addPart("copy_depart", new StringBody("",contentType));
		meb.addPart("secret_depart", new StringBody("",contentType));
		meb.addPart("addr_group", new StringBody("",contentType));
		meb.addPart("specialCondition", new StringBody("",contentType));
		meb.addPart("reach", new StringBody("1",contentType));
		meb.addPart("read", new StringBody("1",contentType));
		meb.addPart("reply", new StringBody("1",contentType));
		meb.addPart("replyto", new StringBody("57192",contentType));
		meb.addPart("replyto2", new StringBody("1",contentType));
		meb.addPart("folder", new StringBody("1",contentType));
		meb.addPart("sendbefore", new StringBody("1",contentType));
		meb.addPart("sendlast", new StringBody("1",contentType));
		meb.addPart("last", new StringBody("1",contentType));
		meb.addPart("annex", new StringBody("",contentType));
		meb.addPart("subSave", new StringBody("",contentType));
		meb.addPart("saveto", new StringBody("2",contentType));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Charset chars = Charset.forName("GBK");
		meb.setCharset(chars);
		meb.addTextBody("title", "我能发邮件了UTF-8");
		meb.addTextBody("addr", "黄晓强");
		meb.addTextBody("addr2", "");
		meb.addTextBody("count", "0");
		meb.addTextBody("replyName", "陈广镇");
		meb.addTextBody("content", "hello,中文乱码");
		meb.addTextBody("addr_id", "57202");
		meb.addTextBody("copy_id", "");
		meb.addTextBody("secret_id", "");
		meb.addTextBody("secret", "");
		meb.addTextBody("addr_class", "");
		meb.addTextBody("addr_depart", "");
		meb.addTextBody("copy_depart", "");
		meb.addTextBody("secret_depart", "");
		meb.addTextBody("addr_group", "");
		meb.addTextBody("specialCondition", "");
		meb.addTextBody("reach", "1");
		meb.addTextBody("read", "1");
		meb.addTextBody("reply", "1");
		meb.addTextBody("replyto", "57192");
		meb.addTextBody("replyto2", "");
		meb.addTextBody("folder", "1");
		meb.addTextBody("sendbefore", "1");
		meb.addTextBody("sendlast", "1");
		meb.addTextBody("last", "1");
		meb.addTextBody("annex", "");
		meb.addTextBody("subSave", "");
		meb.addTextBody("saveto", "2");
      
		//byte[] b="aaaa".getBytes();
		//meb.addBinaryBody("bAddFJ", b);


		HttpPost post = new HttpPost(url);
		//post.addHeader("charset", "GBK");
		post.setEntity(meb.build());
		String body = null;
		try {
			HttpResponse response = client.execute(post);
			

			HttpEntity entity = response.getEntity();
			body = EntityUtils.toString(entity);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(body);
	}

}
*/