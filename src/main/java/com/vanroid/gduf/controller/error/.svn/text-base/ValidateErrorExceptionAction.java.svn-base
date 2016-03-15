package com.vanroid.gduf.controller.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 
* @ClassName ValidateErrorExceptionAction.java Create on 2015-9-3     
*      
* @company Copyright (c) 2015 by Vanroid Team      
*      
* @author CGZ vsjosonadmin@163.com  
*  
* @Description: 输入认证错误信息
*
* @version 1.0
 */
@Controller("ValidateErrorExceptionAction")
@Scope("prototype")
public class ValidateErrorExceptionAction extends ActionSupport {
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	public String validateErrorExceptionJson(){
		resultMap.put("msg", "该功能密码错误，请重新绑定！");
		resultMap.put("resultCode", 0);
		return "success";
		
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	

}
