package com.vanroid.gduf.service.club.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.dao.club.ClubDao;
import com.vanroid.gduf.dao.club.ClubMessageDao;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.entity.ClubMessage;
import com.vanroid.gduf.service.club.ClubMessageService;
import com.vanroid.gduf.util.PageBean;

@Transactional
public class ClubMessageServiceImp implements ClubMessageService {
	private ClubMessageDao clubMessageDao;
	private ClubDao clubDao;

	/**
	 * 保存社团信息信息
	 */
	public void saveClubMessage(ClubMessage clubMessage, Integer club_id) {
		Club club = clubDao.getClubById(club_id);
		clubMessage.setMessage_state(0);
		clubMessage.setMessage_publishdate(new Date());
		clubMessage.setClub(club);
		clubMessageDao.saveClubMessage(clubMessage);
	}

	/**
	 * 分页查询
	 */
	public PageBean queryForPage(int pageSize, int page) {
		final String hql = "from ClubMessage"; // 查询语句
		int allRow = clubMessageDao.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);// 当前页

		List<ClubMessage> list = clubMessageDao
				.queryByPage(hql, offset, length);
		// 分页信息保存在pageBean
		PageBean pageBean = new PageBean(list, allRow, totalPage, currentPage,
				pageSize);
		pageBean.init();

		return pageBean;
	}

	/**
	 * 批量删除选中的消息
	 */
	public void deleteItemsIsChecked(String[] checked) {
		for (int i = 0; i < checked.length; i++) {
			this.clubMessageDao.deleteItemById(checked[i]);
		}
	}

	/**
	 * 根据状态查询
	 */
	public PageBean queryByState(int pageSize, int page, int state) {
		final String hql = "from ClubMessage where message_state =" + state;
		int allRow = clubMessageDao.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);// 当前页
		List<ClubMessage> list = clubMessageDao
				.queryByPage(hql, offset, length);
		// 分页信息保存在pageBean
		PageBean pageBean = new PageBean(list, allRow, totalPage, currentPage,
				pageSize);
		pageBean.init();
		return pageBean;
	}

	/**
	 * 根据Id查询社团消息记录
	 */
	public ClubMessage getClubMessageById(String id) {
		return clubMessageDao.getClubMessageById(Integer.parseInt(id));
	}

	/**
	 * 根据ID更新消息内容
	 */

	public void updateMessageById(ClubMessage clubMessage, String msg_id,
			String club_id) {
		Club existClub = clubDao.getClubById(Integer.parseInt(club_id));
		ClubMessage existMsg = clubMessageDao.getClubMessageById(Integer
				.parseInt(msg_id));
		existMsg.setClub(existClub); // 更新社团
		existMsg.setMessage_content(clubMessage.getMessage_content()); // 更新内容
		existMsg.setMessage_title(clubMessage.getMessage_title()); // 更新标题
		existMsg.setMessage_publishdate(new Date()); // 更新时间
		clubMessageDao.updateMessage(existMsg);
	}

	/**
	 * 更新消息状态
	 */
	public void updateMessage(ClubMessage clubMessage) {
		clubMessageDao.updateMessage(clubMessage);
	}

	/**
	 * 注入setter方法
	 * 
	 */
	public void setClubDao(ClubDao clubDao) {
		this.clubDao = clubDao;
	}

	public void setClubMessageDao(ClubMessageDao clubMessageDao) {
		this.clubMessageDao = clubMessageDao;
	}

	public void deleteItemById(String id) {
		this.clubMessageDao.deleteItemById(id);
	}

}
