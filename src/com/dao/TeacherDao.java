package com.dao;


import com.entity.Teacher;

import java.util.List;

public interface TeacherDao {
	
	public void insertBean(Teacher bean);
	
	public void deleteBean(int id);
	
	public void updateBean(Teacher bean);

	//根据查询的sql查询数据
	public List<Teacher> queryData(String sql);
}
