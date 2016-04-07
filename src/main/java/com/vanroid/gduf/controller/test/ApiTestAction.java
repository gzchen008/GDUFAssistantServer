package com.vanroid.gduf.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @company vanroid.com
 * @author cgz
 * @date 2016年3月17日
 * @version 1.0
 * @description 返回JSON的接口测试控制器
 */

@Controller("ApiTestAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace(value = "/api")
public class ApiTestAction extends ActionSupport {

	/**
	 * 测试页
	 * 
	 * @return
	 */
	@Action(value = "test", results = { @Result(location = "/WEB-INF/jsps/test/index.jsp") })
	public String testPage() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String baseUrl = req.getContextPath();
		req.setAttribute("baseUrl", baseUrl);
		return SUCCESS;
	}

}
