package com.vanroid.gduf.service.club.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanroid.gduf.common.page.Page;
import com.vanroid.gduf.common.page.PageUtils;
import com.vanroid.gduf.dao.club.ClubDao;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.entity.Message;
import com.vanroid.gduf.service.club.ClubService;

@Transactional
@Service
public class ClubServiceImp implements ClubService {
	private ClubDao clubDao;

	@Override
	public List<Club> getList(int curPage, int pageSize, int totalRecord) {
		Page page = PageUtils.createPage(totalRecord, pageSize, curPage);
		return clubDao.getList(page);
	}

	@Override
	public int countClub() {
		return clubDao.countClub();
	}

	@Resource()
	public void setClubDao(ClubDao clubDao) {
		this.clubDao = clubDao;
	}

	@Override
	public void saveMessage(Message message) {
		message.setM_publish_date(new Date());
		clubDao.save(message);
	}

	@Override
	public List<Club> getAllClub() {
		return clubDao.getAllClub();
	}

	@Override
	public void saveClub(Admin admin, String cdescribe, String cName) {
		Club club = new Club();
		club.setAdmin(admin);
		club.setCdate(new Date());
		club.setCdescribe(cdescribe);
		club.setcIcon("club_icon.png");
		club.setcName(cName);
		club.setStatus(1);
		clubDao.saveClub(club);
	}

	@Override
	public Club getClubById(int clubId) {
		return clubDao.getClub(clubId);
	}

	@Override
	public List<Message> getMessageList(int curPage, int pageSize, String flag) {
		Page page = PageUtils.createPage(countClubMessage(flag), pageSize, curPage);
		System.out.println("<><><><><><><><><><><><><><><><>");
		System.out.println(page);
		System.out.println("<><><><><><><><><><><><><><><><>");
		return clubDao.getMsgList(page, flag);
	}

	public int countClubMessage(String flag) {
		// String flag = "club";
		return clubDao.countMsg(flag);
	}

	@Override
	public Message getMessageById(int mid) {
		return clubDao.getMessageById(mid);
	}

	@Override
	public void delMsg(int mid) {
		clubDao.deleteMsg(mid);
	}

	@Override
	public void delClub(int parseInt) {
		List<Message> mlist = clubDao.getMsgByCId(parseInt);
		if (mlist != null) {
			for (Message message : mlist) {
				clubDao.deleteMsg(message.getMid());
			}
		}
		clubDao.deleteClub(parseInt);
	}

	@Override
	public boolean update(Club club) {
		if (club == null || club.getCid() < 0)
			return false;
		club.setCdate(new Date());
		club.setStatus(1);
		clubDao.updateClub(club);
		return true;
	}

}
