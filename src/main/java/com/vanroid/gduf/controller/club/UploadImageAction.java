package com.vanroid.gduf.controller.club;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("UploadImageAction")
@Scope("prototype")
public class UploadImageAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest request;
	private String savePath;

	public String execute() throws Exception {
		InputStream in = request.getInputStream();
		OutputStream out = new FileOutputStream(getSavePath() + "hello.png");

		byte[] buffer = new byte[1024];
		int len;
		System.out.println("文件保存路径:" + getSavePath());
		System.out.println("文件是否为空:" + in == null);
		Enumeration parmas = request.getParameterNames();
		while (parmas.hasMoreElements()) {
			Object object = (Object) parmas.nextElement();
			System.out.println(object);
		}

		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		this.closeIO(in, out);
		return NONE;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getSavePath() {
		return ServletActionContext.getServletContext().getContextPath()
				+ savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	private void closeIO(InputStream in, OutputStream out) {

		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
