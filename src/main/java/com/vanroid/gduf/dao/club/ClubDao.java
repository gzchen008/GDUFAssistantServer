package com.vanroid.gduf.dao.club;

import java.util.List;

import com.vanroid.gduf.common.page.Page;
import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.entity.Message;

public interface ClubDao {
	public Club getClub(int cid);

	public int updateClub(Club club);

	public int deleteClub(int cid);

	public int addClub(Club club);

	/**
	 * 分页取得Club List
	 * 
	 * @param page
	 * @return
	 */
	public List<Club> getList(Page page);

	public Admin getAdminForClub(int cid);

	public int countClub();

	public void save(Message message);

	public List<Club> getAllClub();

	public void saveClub(Club club);

	public int countMsg(String flag);

	public List<Message> getMsgList(Page page, String flag);

	public Message getMessageById(int mid);

	public void deleteMsg(int i);

	public List<Message> getMsgByCId(int parseInt);
}
