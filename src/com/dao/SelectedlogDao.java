package com.dao;


import com.entity.Selectedlog;

import java.util.List;

public interface SelectedlogDao {
	
	public void insertBean(Selectedlog bean);
	
	public void deleteBean(int id);
	
	public void updateBean(Selectedlog bean);

	//根据查询的sql查询数据
	public List<Selectedlog> queryData(String sql);
}
