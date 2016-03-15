package com.vanroid.gduf.service.club;

import java.util.List;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.entity.Club;

public interface ClubService {
	/**
	 * 获取所有的社团
	 * 
	 * @return List<Club>
	 */
	public List<Club> getAllClubs();

	/**
	 * 给定Id得到对应的Club实例
	 * 
	 * @param id
	 * @return Club
	 */
	public Club getClubById(Integer id);

	/**
	 * 保存注册社团管理员的信息
	 * 
	 * @param admin
	 *            管理员实体
	 * @param club
	 *            绑定社团
	 */
	public void saveRegiestInfo(Admin admin, Club club, String verifycode);

	/**
	 * 根据管理员ID查找社团
	 * 
	 * @param id
	 * @return
	 */
	public Club findClubByAdminId(Integer id);

	/**
	 * 更新社团信息
	 * 
	 * @param existClub
	 */
	public void updateClub(Club existClub);

	/**
	 * 激活用户所需要的信息
	 * 
	 * @param verifycode
	 *            验证码
	 * @param club_name
	 *            社团管理员帐号
	 * @return
	 */
	public String activateClub(String verifycode, String club_name);
}
