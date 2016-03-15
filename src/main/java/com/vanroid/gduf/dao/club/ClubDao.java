package com.vanroid.gduf.dao.club;

import java.util.List;

import com.vanroid.gduf.entity.Club;

public interface ClubDao {
	/**
	 * 保存社团信息到数据库
	 * 
	 * @param club
	 */
	public void saveClub(Club club);

	/**
	 * 根据名称返回社团信息
	 * 
	 * @param clubName
	 * @return List<CLub>
	 */
	public List<Club> getClubByName(Club clubName);

	/**
	 * 根据Id查询社团信息
	 * 
	 * @param clubId
	 * @return Club
	 */
	public Club getClubById(Integer clubId);

	/**
	 * 得到所有的社团
	 * 
	 * @return Club
	 */
	public List<Club> getAllCLubs();

	/**
	 * 根据管理员Id查找对应的社团
	 * 
	 * @param id
	 * @return
	 */
	public Club findClueByAdminId(Integer id);

	/**
	 * 更新社团
	 * 
	 * @param existClub
	 */
	public void updateClub(Club existClub);
}
