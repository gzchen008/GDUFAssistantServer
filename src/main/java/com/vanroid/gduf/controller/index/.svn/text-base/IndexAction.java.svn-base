package com.vanroid.gduf.controller.index;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
@Controller("IndexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport{

	private Map result = new HashMap();
	@Override
	public String execute() throws Exception {
		System.out.println("hello word");
		result.put("status","0");
		result.put("msg","hello word");
		return Action.SUCCESS;
		
	}
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	}
	

}
