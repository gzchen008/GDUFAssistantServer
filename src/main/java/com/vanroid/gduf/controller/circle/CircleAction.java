package com.vanroid.gduf.controller.circle;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vanroid.gduf.entity.Circle;
import com.vanroid.gduf.entity.CircleMes;
import com.vanroid.gduf.entity.ImagePath;
import com.vanroid.gduf.entity.User;
import com.vanroid.gduf.service.circle.CircleService;
import com.vanroid.gduf.service.circle.UpImgUtil;

@Controller("circleAction")
@Scope("prototype")
public class CircleAction extends ActionSupport implements ModelDriven<Circle> {
	private CircleService circleService;
	Map<String, Object> resultMap = new HashMap<String, Object>();
	private int myId;
	private int stuId;
	private int listnum;// 显示更多朋友圈时的朋友圈序号
	private Circle circle = new Circle();
	private String[] imgCode;// 前台传过来的图片base64码
	private List<File> img;// 上传的图片
	
	public String addCirclePage(){
		return Action.SUCCESS;
	}

	/**
	 * 添加朋友圈
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addCircle() throws IOException {
		System.out.println("进入方法中……");
		String path = ServletActionContext.getServletContext().getRealPath("/")
				+ "picture";
		System.out.println("path" + path);

		System.out.println("sender:" + circle.getSender().getId());
		if (img != null) {
			List<ImagePath> imges = UpImgUtil.imghanlder(circle, img, path);
			circle.setImages(imges);
		}
		System.out.println("shuju" + circle.getImages().size());
		circle.setCreateTime(new Date());
		circleService.addCircle(circle);
		List<Circle> circleList = circleService.queryCircles(0, 10);
		ServletActionContext.getRequest().getSession()
				.setAttribute("circles", circleList);
		return Action.SUCCESS;
	}

	/**
	 * 删除朋友圈
	 * 
	 * @return
	 */
	public String deleteCircle() {
		return Action.SUCCESS;
	}

	/**
	 * 查询相应的朋友圈列表 查询前先将自己的id传到
	 * 
	 * @return
	 */
	public String queryCircles() {
		//首页需要设置id和stuId
		if (listnum == 0) {
			User user = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("qtUser");
			ServletActionContext.getRequest().getSession()
					.setAttribute("myId", user.getId());
			ServletActionContext.getRequest().getSession()
					.setAttribute("myStuId", user.getStuId());
		}
		List<Circle> circleList = circleService.queryCircles(listnum, 10);
		ServletActionContext.getRequest().getSession()
				.setAttribute("circles", circleList);
		resultMap.put("circleList", circleList);
		return Action.SUCCESS;
	}

	public CircleService getCircleService() {
		return circleService;
	}

	@Resource
	public void setCircleService(CircleService circleService) {
		this.circleService = circleService;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	@Override
	public Circle getModel() {
		// TODO 自动生成的方法存根
		return circle;
	}

	public String[] getImgCode() {
		return imgCode;
	}

	public void setImgCode(String[] imgCode) {
		this.imgCode = imgCode;
	}

	public int getMyId() {
		return myId;
	}

	public void setMyId(int myId) {
		this.myId = myId;
	}

	public List<File> getImg() {
		return img;
	}

	public void setImg(List<File> img) {
		this.img = img;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public int getListnum() {
		return listnum;
	}

	public void setListnum(int listnum) {
		this.listnum = listnum;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
}
