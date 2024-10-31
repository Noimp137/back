package com.service;

import java.util.List;
import java.util.Map;
import com.entity.User;

/**
 * 业务层接口 
 */
public interface UserService {
	
	//添加
	void insertBean(User bean);
	
	//删除
	public void deleteBean(int id);
	
	//更新
	public void updateBean(User bean);
	
	//用户登录
	public User userlogin(String username, String password);
	
	
	//验证该用户名是否存在
	public User useryz(String username);
	
	
	//根据ID查询对象
	public User selectBeanById(int id);
	
	
	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	public Map<String,List<User>> selectBeanMap(int currentpage, int pagesize, String url, String sql);
	
	
	//根据定义的sql语句查询数据
	public List<User> selectData(String sql);
}






