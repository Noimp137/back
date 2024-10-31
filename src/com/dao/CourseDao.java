package com.dao;


import com.entity.Course;

import java.util.List;

public interface CourseDao {
	
	public void insertBean(Course bean);
	
	public void deleteBean(int id);
	
	public void updateBean(Course bean);

	//根据查询的sql查询数据
	public List<Course> queryData(String sql);
}
