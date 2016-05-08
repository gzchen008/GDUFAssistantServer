package com.vanroid.gduf.controller.error;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @company vanroid.com
 * @author cgz
 * @date 2016年3月24日
 * @version 1.0
 * @description 错误处理
 */
@Controller("ErrorAction")
@Scope("prototype")
public class ErrorAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> resultMap;

	/**
	 * 没有登录
	 * @return
	 */
	public String noLoginJSON() {
		return "success";
	}

	/**
	 * 没有绑定
	 * @return
	 */
	public String noBondJson() {
		return "success";
	}

	/**
	 * 验证错误
	 * @return
	 */
	public String validateErrorExceptionJson() {
		return "success";

	}

	/**
	 * 系统访问异常错误
	 * 
	 * @return
	 */
	public String accessErrorJson() {
		return "success";
	}

	public Map<String, Object> getResultMap() {
		resultMap = (Map<String, Object>) ServletActionContext.getRequest().getAttribute("errorResultMap");
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
