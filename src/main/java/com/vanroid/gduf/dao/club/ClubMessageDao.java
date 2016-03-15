package com.vanroid.gduf.dao.club;

import java.util.List;

import com.vanroid.gduf.entity.ClubMessage;

public interface ClubMessageDao {
	/**
	 * 保存社团发布的信息到数据库
	 * 
	 * @param clubMessage
	 */
	public void saveClubMessage(ClubMessage clubMessage);

	/**
	 * 根据给定Id得到社团发布的信息
	 * 
	 * @param id
	 * @return ClubMessage
	 */
	public ClubMessage getClubMessageById(Integer id);

	/**
	 * 分页查询社团发布的消息
	 * 
	 * @param hql
	 *            查询条件
	 * @param offset
	 *            开始记录
	 * @param length
	 *            一次查询的记录
	 * @return 查询的记录集合
	 */
	public List<ClubMessage> queryByPage(final String hql, final int offset,
			final int length);

	/**
	 * 查询所有的记录数
	 * 
	 * @param hql
	 *            查询条件
	 * @return 总记录数
	 */
	public int getAllRowCount(String hql);

	/**
	 * 根据Id删除消息
	 * 
	 * @param id
	 *            消息的主键
	 */
	public void deleteItemById(String id);

	/**
	 * 根据给定Id更新消息记录
	 * 
	 * 
	 */
	public void updateMessage(ClubMessage clubMessage);

}
