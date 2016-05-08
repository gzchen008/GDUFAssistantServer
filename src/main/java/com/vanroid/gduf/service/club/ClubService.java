package com.vanroid.gduf.service.club;

import java.util.List;

import com.vanroid.gduf.entity.Admin;
import com.vanroid.gduf.entity.Club;
import com.vanroid.gduf.entity.Message;

public interface ClubService {

	public int countClub();

	public List<Club> getList(int curPage, int pageSize, int totalRecord);

	public void saveMessage(Message message);

	public List<Club> getAllClub();

	public void saveClub(Admin admin, String cdescribe, String cName);

	public Club getClubById(int clubId);

	public List<Message> getMessageList(int curPage, int pageSize, String flag);

	public Message getMessageById(int mid);

	public int countClubMessage(String flag);

	public void delMsg(int mid);

	public void delClub(int parseInt);

	public boolean update(Club club);

}
