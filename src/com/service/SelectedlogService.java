package com.service;

import com.entity.Selectedlog;

import java.util.List;
import java.util.Map;

/**
 * 业务层接口 
 */
public interface SelectedlogService {
	
	//添加
	void insertBean(Selectedlog bean);
	
	//删除
	public void deleteBean(int id);
	
	//更新
	public void updateBean(Selectedlog bean);

	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	public Map<String,List<Selectedlog>> selectBeanMap(int currentpage, int pagesize, String url, String sql);
	
	

	//根据定义的sql语句查询数据
	public List<Selectedlog> selectData(String sql);


	//根据定义的sql语句查询单个对象
	public Selectedlog selectBean(String sql);
}






