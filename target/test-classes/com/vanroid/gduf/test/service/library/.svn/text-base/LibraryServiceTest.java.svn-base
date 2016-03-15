package com.vanroid.gduf.test.service.library;

import javax.annotation.Resource;

import org.apache.http.client.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanroid.gduf.entity.LibraryUserInfo;
import com.vanroid.gduf.service.library.LibrarySearchService;
import com.vanroid.gduf.service.library.MyLibraryService;
import com.vanroid.gduf.util.HttpClientUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LibraryServiceTest {
	@Resource
	private MyLibraryService myLibraryService;
	@Resource
	private LibrarySearchService librarySearchService;

	@Test
	public void testLogin() {
		LibraryUserInfo libUser = myLibraryService.login(
				HttpClientUtils.createDefaultHttpClient(null), "131545230",
				"123456");
		System.out.println(libUser);
	}

	@Test
	public void testSearch() {
		HttpClient httpClient = HttpClientUtils.createDefaultHttpClient(null);
		librarySearchService.search(httpClient, "java", 1);
	}

}
