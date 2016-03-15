package com.vanroid.gduf.service.jwc;

import com.vanroid.gduf.entity.Grade;
import com.vanroid.gduf.service.impl.jwc.JWCHandler;

/**
 * 
 * @ClassName GradeService.java Create on 2015年8月29日
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author Jerry 983951558@qq.com
 * 
 * @Description: 成绩service层接口
 * 
 * @version 1.0
 */

public interface GradeService {
	public void add(Grade c);

	public void update(Grade c);

	public Grade query();

	public Grade getGradeInfo(JWCHandler handler, Grade grade, String xm);

	public Grade queryExistInDb(Grade c);
}
