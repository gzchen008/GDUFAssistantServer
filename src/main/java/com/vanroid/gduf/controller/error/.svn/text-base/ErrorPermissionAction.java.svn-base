package com.vanroid.gduf.controller.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("ErrorPermissionAction")
@Scope("prototype")
public class ErrorPermissionAction extends ActionSupport {
	private Map<String, Object> resultMap;

	public String noLoginJSON() {
		resultMap = new HashMap<String, Object>();
		resultMap.put("resultCode", 0);
		resultMap.put("msg", "未登录，不能操作，请先登录");
		return "success";
	}
	
	public String noBondJson(){
		resultMap = new HashMap<String, Object>();
		resultMap.put("resultCode", 0);
		resultMap.put("msg", "未绑定学校系统帐户，暂不可使用该功能");
		return "success";
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
