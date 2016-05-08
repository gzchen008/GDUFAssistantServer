package com.vanroid.gduf.controller.club;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.common.page.Page;
import com.vanroid.gduf.common.page.PageUtils;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.service.club.AdminService;
import com.vanroid.gduf.service.club.ClubService;

@SuppressWarnings("serial")
@Controller("ClubAction")
@Scope("prototype")
public class ClubAction extends ActionSupport implements ServletRequestAware {
	@Resource
	private ClubService clubService;
	@Resource
	private AdminService adminService;
	private List<Club> clubs;
	private int curPage;
	private int pageSize;
	private Page pager;
	private HttpServletRequest request;

	public String managerPage() {

		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("curPage:" + curPage);
		int totalRecord = clubService.countClub();
		Page page = PageUtils.createPage(totalRecord, pageSize, curPage);
		clubs = clubService.getList(curPage, pageSize, totalRecord);
		System.out.println(page);
		request.setAttribute("clubs", clubs);
		request.setAttribute("pager", page);
		return SUCCESS;
	}

	public String updateClub() {
		String result = "";
		try {
			String cid = request.getParameter("cid");
			int clubId = Integer.parseInt(cid);
			if (request.getMethod().equals("GET")) {
				Club club = clubService.getClubById(clubId);
				List<Admin> admins = adminService.getAllAdmin();
				request.setAttribute("club", club);
				request.setAttribute("admins", admins);
				result = INPUT;
			} else if (request.getMethod().equals("POST")) {
				String aid = request.getParameter("adminName");
				String cName = request.getParameter("cName");
				String cdescribe = request.getParameter("cdescribe");
				int adminId = Integer.parseInt(aid);
				Admin admin = adminService.getAdminById(adminId);
				Club club = new Club(clubId, cName, cdescribe, admin);
				clubService.update(club);
				result = SUCCESS;
			}
		} catch (Exception e) {
			return INPUT;
		}
		return result;
	}

	public String addClub() {
		String method = ServletActionContext.getRequest().getMethod();
		String result = null;
		if (method.equals("POST")) {
			String adminId = request.getParameter("adminName");
			System.out.println("adminId:" + adminId);
			String cName = request.getParameter("cName");
			String cdescribe = request.getParameter("cdescribe");
			Admin admin = adminService.getAdminById(adminId != null ? Integer.parseInt(adminId) : 1);
			clubService.saveClub(admin, cdescribe, cName);
			result = LOGIN;
		} else if (method.equals("GET")) {
			System.out.println("GET请求");
			List<Admin> admins = adminService.getAllAdmin();
			request.setAttribute("admins", admins != null ? admins : "缺失");
			result = SUCCESS;
		}
		return result;
	}

	public String delClub() {
		String[] cidArray = request.getParameter("list").split(",");

		for (int i = 0; i < cidArray.length; i++) {
			clubService.delClub(Integer.parseInt(cidArray[i]));
		}

		return SUCCESS;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Club> getClubs() {
		return clubs;
	}

	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

	public ClubService getClubService() {
		return clubService;
	}

	public int getCurPage() {
		return curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Page getPager() {
		return pager;
	}

	public void setPager(Page pager) {
		this.pager = pager;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
