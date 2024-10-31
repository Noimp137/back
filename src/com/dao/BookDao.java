package com.dao;


import com.entity.Book;

import java.util.List;

public interface BookDao {
	
	public void insertBean(Book bean);
	
	public void deleteBean(int id);
	
	public void updateBean(Book bean);

	//根据查询的sql查询数据
	public List<Book> queryData(String sql);
}
