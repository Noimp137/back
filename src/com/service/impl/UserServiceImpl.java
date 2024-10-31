package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import com.util.Pager;



@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	public void insertBean(User bean) {
		userDao.insertBean(bean);
		
	}
	
	public void deleteBean(int id) {
		userDao.deleteBean(id);
		
	}
	
	public void updateBean(User bean){
		
		userDao.updateBean(bean);
		
	}
	

	//用户登录
	public User userlogin(String username,String password){
		
		return userDao.userlogin(username, password);
		
	}
	
	
	
	//验证该用户名是否存在
	public User useryz(String username){
		
		return userDao.useryz(username);
	}
	
	
	//根据ID查询对象
	public User selectBeanById(int id){
		
		return userDao.selectBeanById(id);
		
	}
	


	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	@SuppressWarnings("unchecked")
	public Map<String,List<User>> selectBeanMap(int currentpage,int	pagesize,String url,String sql){
		
		
		List<User> list = userDao.queryData(sql);
		
		Pager pager = new Pager(list, pagesize);
		
		List<User> pagerlist = pager.getObjects(currentpage);

		int total = list.size();
		
		
		Map<String,List<User>> map = new HashMap<String,List<User>>();
		
		map.put(Pager.getPagerNormal(total, pagesize,currentpage, url, "共有" + total + "条记录"), pagerlist);
		String pagerinfo = map.keySet().iterator().next();
		List<User> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
		return map;
	}
	
	
	public List<User> selectData(String sql){
		
		return userDao.queryData(sql);
	}
	
}











