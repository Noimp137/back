package com.dao;


import com.entity.Classinfo;

import java.util.List;

public interface ClassinfoDao {
	
	public void insertBean(Classinfo bean);
	
	public void deleteBean(int id);
	
	public void updateBean(Classinfo bean);

	//根据查询的sql查询数据
	public List<Classinfo> queryData(String sql);
}
