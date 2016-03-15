package com.vanroid.gduf.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class UeditorFilter extends StrutsPrepareAndExecuteFilter {

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();
		if (url.endsWith("controller.jsp")) {
			chain.doFilter(req, resp);
		} else {
			super.doFilter(req, resp, chain);
		}
	}
}
