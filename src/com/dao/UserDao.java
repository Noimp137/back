package com.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

public interface UserDao {
	
	public void insertBean(User bean);
	
	public void deleteBean(int id);
	
	public void updateBean(User bean);
	
	public User userlogin(
            @Param("username") String username,
            @Param("password") String password
    );
	
	
	public User useryz(
            @Param("username") String username
    );
	
	
	public User selectBeanById(
            @Param("id") int id
    );
	

	//根据查询的sql查询数据
	public List<User> queryData(String sql);
}
