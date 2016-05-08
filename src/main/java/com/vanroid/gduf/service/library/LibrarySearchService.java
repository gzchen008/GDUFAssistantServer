package com.vanroid.gduf.service.library;

import org.apache.http.client.HttpClient;

import com.vanroid.gduf.entity.LibrarySearchHistory;

/**
 * 
 * @ClassName LibrarySearchService.java Create on 2015-9-3
 * 
 * @company Copyright (c) 2015 by Vanroid Team
 * 
 * @author CGZ vsjosonadmin@163.com
 * 
 * @Description: 图书馆搜索服务
 * 
 * @version 1.0
 */
public interface LibrarySearchService {

	LibrarySearchHistory search(HttpClient httpClient, String keywords, int page);

}
