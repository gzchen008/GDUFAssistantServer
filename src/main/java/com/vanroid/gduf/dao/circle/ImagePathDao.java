package com.vanroid.gduf.dao.circle;

import com.vanroid.gduf.entity.ImagePath;

public interface ImagePathDao {
	public void addImage(ImagePath ip);
	public String queryPath(int tid);
}
