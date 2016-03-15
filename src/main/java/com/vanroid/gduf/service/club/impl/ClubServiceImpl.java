package com.vanroid.gduf.service.club.impl;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.constant.AdminConstant;
import com.vanroid.gduf.constant.ClubConstant;
import com.vanroid.gduf.dao.club.AdminDao;
import com.vanroid.gduf.dao.club.ClubDao;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.service.club.ClubService;

@Transactional
public class ClubServiceImpl implements ClubService {

	private ClubDao clubDao;
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void setClubDao(ClubDao clubDao) {
		this.clubDao = clubDao;
	}

	public List<Club> getAllClubs() {

		return clubDao.getAllCLubs();
	}

	public Club getClubById(Integer id) {

		return clubDao.getClubById(id);
	}

	/**
	 * 保存社团管理员的注册信息到数据库和发送激活邮件到用户邮箱激活帐号
	 */
	@Override
	public void saveRegiestInfo(Admin admin, Club club, String verifycode) {
		/**
		 * 保存社团管理员注册信息
		 */
		admin.setAdmin_rank(AdminConstant.NO_ADMIN);
		adminDao.saveAdmin(admin);
		club.setAdmin(admin);
		/**
		 * 设置社团状态为未激活状态
		 */
		club.setClub_verification(verifycode);
		club.setClub_state(ClubConstant.CLUB_NOT_ACTIVATED);
		club.setClub_setup(new Date());
		clubDao.saveClub(club);
	}

	/**
	 * 根据管理员ID寻找对应的社团
	 */
	@Override
	public Club findClubByAdminId(Integer id) {

		return clubDao.findClueByAdminId(id);
	}

	/**
	 * 更新社团信息
	 */
	@Override
	public void updateClub(Club existClub) {

		clubDao.updateClub(existClub);
	}

	/**
	 * 激活管理员帐号操作
	 */
	@Override
	public String activateClub(String verifycode, String admin_name) {
		// 根据给定的帐号名查找出对应的用户
		Admin existAdmin = adminDao.findAdminByName(admin_name);
		if (existAdmin != null) {
			// 根据管理员Id查找出Club
			Club existClub = clubDao.findClueByAdminId(existAdmin.getId());
			System.out.println("用户名:" + existAdmin.getAdmin_name());
			System.out.println("社团名称:" + existClub);
			System.out.println("验证码：" + verifycode);
			System.out.println("数据库验证码：" + existClub.getClub_verification());
			// 验证码一致
			if (verifycode.equals(existClub.getClub_verification())) {
				// 设置管理员权限为社团管理员
				existAdmin.setAdmin_rank(AdminConstant.CLUB_ADMIN);
				// 已经激活社团管理员
				existClub.setClub_verification(new Date().toString());
				// 设置社团状态为激活状态
				existClub.setClub_state(ClubConstant.CLUB_ACTIVATED);
				// 更新管理员操作
				adminDao.updateAdmin(existAdmin);
				// 更新社团操作
				clubDao.updateClub(existClub);
				return ClubConstant.OPERATION_SUCCESS;
			}
		}
		return ClubConstant.OPERTION_ERROR;

	}
}
