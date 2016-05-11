package com.vanroid.gduf.controller.club;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.common.page.Page;
import com.vanroid.gduf.common.page.PageUtils;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.entity.Message;
import com.vanroid.gduf.service.club.ClubService;

@SuppressWarnings("serial")
@Controller("MessageAction")
@Scope("prototype")
public class MessageAction extends ActionSupport implements ServletRequestAware {
	@Resource
	private ClubService clubService;
	private List<Club> clubs;
	private HttpServletRequest request;

	public String addClubMsg() {
		String result = null;
		if (request.getMethod().equals("GET")) {
			System.out.println("GET方法");
			clubs = clubService.getAllClub();
			request.setAttribute("clubs", clubs);
			result = SUCCESS;
		} else if (request.getMethod().equals("POST")) {
			System.out.println("POST方法");
			String clubId = request.getParameter("clubId");
			String mTitle = request.getParameter("mTitle");
			String mContent = request.getParameter("content");
			String type = request.getParameter("type");
			String icon = request.getParameter("icon");
			System.out.println("mContent:" + mContent);
			Club club = clubService.getClubById(Integer.parseInt(clubId));
			System.out.println(club);
			Message message = new Message();
			message.setClub(club);
			message.setmTitle(mTitle);
			message.setmContent(mContent);
			message.setType(Integer.parseInt(type));
			String imageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/" + request.getContextPath() + "/images/club_icon.png";
			message.setmIcon(icon == null ? imageUrl : icon);
			clubService.saveMessage(message);
			result = LOGIN;
		}
		return result;
	}

	public String clubMsgList() {
		String curPage = request.getParameter("curPage");
		String type = request.getParameter("type");
		// String image = request.getContextPath() + "/images/club_icon.png";
		List<Message> messagelist = clubService.getMessageList(curPage == null ? 1 : Integer.parseInt(curPage), 10,
				type);
		System.out.println("<><><><><><><>");
		System.out.println("type:" + clubService.countClubMessage(type));
		System.out.println("<><><><><><><>");
		Page page = PageUtils.createPage(clubService.countClubMessage(type), 10,
				curPage == null ? 1 : Integer.parseInt(curPage));
		for (Message message : messagelist) {
			System.out.println(messagelist);
		}
		// request.setAttribute("image", image);
		request.setAttribute("msgList", messagelist);
		request.setAttribute("pager", page);
		return SUCCESS;
	}

	public String msgManager() {
		String curPage = request.getParameter("curPage");
		String type = request.getParameter("type");
		Page pager = PageUtils.createPage(clubService.countClubMessage(type == null ? "all" : type), 10,
				curPage == null ? 1 : Integer.parseInt(curPage));
		List<Message> messagelist = clubService.getMessageList(curPage == null ? 1 : Integer.parseInt(curPage), 10,
				type);
		request.setAttribute("pager", pager);
		request.setAttribute("msgList", messagelist);
		return SUCCESS;
	}

	public String deletMsg() {
		String mid = request.getParameter("mid");
		clubService.delMsg(Integer.parseInt(mid));
		return SUCCESS;
	}

	public String clubMsgDetail() {
		int mid = Integer.parseInt(request.getParameter("mid"));
		Message message = clubService.getMessageById(mid);
		request.setAttribute("msg", message);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
