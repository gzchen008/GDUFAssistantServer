/*
 * package com.vanroid.gduf.test.service.jwc;
 * 
 * import javax.annotation.Resource;
 * 
 * import org.apache.http.client.HttpClient; import org.junit.Test; import
 * org.junit.runner.RunWith; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 * 
 * import com.vanroid.gduf.dto.JwcInfo; import
 * com.vanroid.gduf.entity.ClassBean; import com.vanroid.gduf.entity.Course;
 * import com.vanroid.gduf.service.impl.jwc.JWCHandler; import
 * com.vanroid.gduf.service.impl.jwc.JwcLoginService; import
 * com.vanroid.gduf.service.jwc.CourseService; import
 * com.vanroid.gduf.util.HttpClientUtils;
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @ContextConfiguration(locations="classpath:applicationContext.xml") public
 * class CourseServiceTest {
 * 
 * @Resource(name="loginService") private JwcLoginService jwcLoginService;
 * 
 * @Resource private CourseService courseService;
 * 
 * @Test public void testgetCourse(){ Course course = new Course();
 * 
 * 
 * 
 * String xm = "余东杰"; HttpClient httpClient =
 * HttpClientUtils.createDefaultHttpClient(null);
 * 
 * JwcInfo jwcInfo = new JwcInfo(); jwcInfo.setXh("131545251");
 * jwcInfo.setPassword("fy18098135003@"); jwcLoginService.login(httpClient,
 * jwcInfo );
 * 
 * 
 * 
 * course.setStuId(jwcInfo.getXh()); course.setXq(1);
 * course.setYear("2015-2016");
 * 
 * Course course1 = courseService.getCourseInfo(new
 * JWCHandler(httpClient),course, xm); for(ClassBean c:course1.getClasses()){
 * System.out.println(c.getTitle()); } }
 * 
 * 
 * }
 */