package com.vanroid.gduf.service.club;

import com.vanroid.gduf.entity.ClubMessage;
import com.vanroid.gduf.util.PageBean;

public interface ClubMessageService {
	/**
	 * 保存用户信息到数据库
	 * 
	 * @param clubMessage
	 *            社团消息实体类
	 * @param club_id
	 *            社团的id
	 * 
	 */
	public void saveClubMessage(ClubMessage clubMessage, Integer club_id);

	/**
	 * 分页查询
	 * 
	 * @param pageSize
	 *            每页显示记录数
	 * @param page
	 *            当前页
	 * @return 封装分页信息的bean
	 */
	public PageBean queryForPage(int pageSize, int page);

	/**
	 * 根据给定的id删除社团发布的消息
	 * 
	 * @param id
	 *            社团消息的id
	 */
	public void deleteItemById(String id);

	/**
	 * 批量删除选中的社团消息
	 * 
	 * @param checked
	 *            包含选中的社团消息的id数组
	 */
	public void deleteItemsIsChecked(String[] checked);

	/**
	 * 根据给定状态查询出对应的分页信息
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param page
	 *            当前页
	 * @param state
	 *            状态码，1表示已经审核，2表示未审核
	 * @return
	 */
	public PageBean queryByState(int pageSize, int page, int state);

	/**
	 * 给定id查询消息记录
	 * 
	 * @param id
	 *            社团消息的id
	 * @return 社团消息记录
	 */
	public ClubMessage getClubMessageById(String id);

	/**
	 * 
	 * @param clubMessage
	 * @param msg_id
	 * @param club_id
	 */
	public void updateMessageById(ClubMessage clubMessage, String msg_id,
			String club_id);

	public void updateMessage(ClubMessage clubMessage);
}