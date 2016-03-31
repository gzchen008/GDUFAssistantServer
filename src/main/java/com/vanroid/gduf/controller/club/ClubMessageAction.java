package com.vanroid.gduf.controller.club;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vanroid.gduf.entity.ClubMessage;
import com.vanroid.gduf.service.club.ClubMessageService;
import com.vanroid.gduf.util.PageBean;

/**
 * 处理关于社团消息的控制类
 * 
 * @author Joe_Huang
 * 
 */
@SuppressWarnings("serial")
public class ClubMessageAction extends ActionSupport implements
		ModelDriven<ClubMessage> {

	private ClubMessageService clubMessageService;
	private ClubMessage clubMessage = new ClubMessage();
	private PageBean pageBean; // 封装分页信息和数据内容的pageBean
	private List list = new ArrayList(); // 用于存储pageBean中被封装的ClubMessage信息
	private int page = 1;// 表示从网页返回的当前页的值，默认1表示默认显示第一页的内容

	/**
	 * 跳转到新增社团消息页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertPage() throws Exception {

		return "insertPage";
	}

	/**
	 * 插入消息
	 * 
	 * @return 成功：跳转社团消息列表页;失败: 跳转失败页
	 * @throws Exception
	 */
	public String insertNew() throws Exception {
		String club_id = ServletActionContext.getRequest().getParameter(
				"club_id");
		int clubId = Integer.parseInt(club_id);
		String message = clubMessage.getMessage_content();
		if (message == null || message.trim().length() == 0) {
			ServletActionContext.getRequest().setAttribute("error", "文章内容不能为空");
			return "insertPage";
		}
		clubMessageService.saveClubMessage(clubMessage, clubId);
		return SUCCESS;

	}

	/**
	 * 删除社团消息 根据参数 state判断是删除单项还是批量删除， state=batch是批量删除，state=single是单项删除
	 * 
	 * @return 跳转社团消息列表页
	 * @throws Exception
	 */
	public String deleteItem() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String state = request.getParameter("state");
		request.getSession().setAttribute("state", state);
		if (state.trim().equals("batch")) {
			String[] checked = request.getParameterValues("clubMessage_id");
			clubMessageService.deleteItemsIsChecked(checked);
		} else if (state.trim().equals("single")) {
			String id = request.getParameter("id");
			clubMessageService.deleteItemById(id);
		}
		return SUCCESS;
	}

	/**
	 * 更新社团信息页，每页显示的条目数为5条
	 * 
	 * @return 跳转社团消息页
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateList() throws Exception {
		this.pageBean = clubMessageService.queryForPage(5, page);
		this.list = (List<ClubMessage>) this.pageBean.getList();
		return "updateList";
	}

	/**
	 * 根据给定状态返回视图 state = -1 表示显示全部信息（包括已审核&未审核） state = 1 表示已经审核 state = 0
	 * 表示未审核
	 * 
	 * @return 跳转社团消息页
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateState() throws Exception {
		int state = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("state"));

		if (state == -1) {
			this.updateList();
		} else {
			this.pageBean = clubMessageService.queryByState(5, page, state);
			this.list = (List<ClubMessage>) this.pageBean.getList();
			if (list == null) {
				this.addActionMessage("没有查询到相关记录");
			}
		}

		return "updateList";

	}

	/**
	 * 根据给定Id阅读消息记录,跳转到信息详细页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String readMessage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		ClubMessage existClubMessage = clubMessageService
				.getClubMessageById(id);
		ServletActionContext.getRequest().setAttribute("clubMessage",
				existClubMessage);
		return "showMsg";
	}

	/**
	 * 跳转到更新消息页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateMessagePage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		ClubMessage existClubMessage = clubMessageService
				.getClubMessageById(id);
		ServletActionContext.getRequest().setAttribute("clubMessage",
				existClubMessage);
		return "update";
	}

	/**
	 * 修改更新消息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateMessage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String msg_id = request.getParameter("msg_id");
		String club_id = request.getParameter("club_id");
		String content = clubMessage.getMessage_content();
		if (content == null || content.trim().length() == 0) {
			ServletActionContext.getRequest().setAttribute("error", "文章内容不能为空");
			return "update";
		}
		clubMessageService.updateMessageById(clubMessage, msg_id, club_id);
		return SUCCESS;

	}

	/**
	 * 跳转到审核信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkMessagePage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String msg_id = request.getParameter("message_id");
		clubMessage = clubMessageService.getClubMessageById(msg_id);
		ServletActionContext.getRequest().setAttribute("clubMessage",
				clubMessage);
		return "check";
	}

	/**
	 * 审查后处理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkOver() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String msg_id = request.getParameter("message_id");
		String checkMsg = request.getParameter("checkMsg");
		clubMessage = clubMessageService.getClubMessageById(msg_id);
		if (checkMsg.trim().equals("-1")) {
			// 表示不通过审核
			clubMessage.setMessage_state(2);
		} else if (checkMsg.trim().equals("1")) {
			// 通过审核
			clubMessage.setMessage_state(1);
		}
		clubMessageService.updateMessage(clubMessage);
		return SUCCESS;
	}

	/**
	 * 将已经审查通过的社团消息封装成JSON数据返回给android客户端 请求地址:
	 * http://127.0.0.1:9999//gduf.web/clubMessage_club_messageListForAndroid
	 * 
	 * @return 封装好的社团消息JSON
	 */
	@SuppressWarnings("unchecked")
	public String club_messageListForAndroid() throws Exception {
		this.pageBean = clubMessageService.queryByState(10, 1, 1);
		List<ClubMessage> msglist = (List<ClubMessage>) pageBean.getList();
		for (int i = 0; i < msglist.size(); i++) {
			ClubMessage msg = msglist.get(i);
			Map<String, Object> map = new HashMap();
			map.put("msg_id", msg.getId());
			map.put("clubName", msg.getClub().getClub_name());
			map.put("pulishTime", msg.getMessage_publishdate());
			map.put("title", msg.getMessage_title());
			this.list.add(map);
		}
		return "msg_list_android";
	}

	/**
	 * 根据id将详细页面返回给android客户端 访问地址 msg_id:对应每一条消息的主键
	 * http://127.0.0.1:9999/gduf.web/clubMessage_clubMsgForAndroid?msg_id=XXX;
	 * 
	 * @return
	 * @throws Exception
	 */
	public String clubMsgForAndroid() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String msg_id = request.getParameter("msg_id");
		clubMessage = clubMessageService.getClubMessageById(msg_id);
		request.setAttribute("clubMessage", clubMessage);
		return "android";

	}

	/**
	 * 各个属性的getter和setter方法
	 * 
	 */
	public ClubMessage getClubMessage() {
		return clubMessage;
	}

	public void setClubMessage(ClubMessage clubMessage) {
		this.clubMessage = clubMessage;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 注入ClubMessageService实例
	 * 
	 * @param clubMessageService
	 */
	public void setClubMessageService(ClubMessageService clubMessageService) {
		this.clubMessageService = clubMessageService;
	}

	/**
	 * 模型驱动
	 */
	public ClubMessage getModel() {

		return clubMessage;
	}
}
