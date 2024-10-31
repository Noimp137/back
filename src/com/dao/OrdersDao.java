package com.dao;


import com.entity.Orders;

import java.util.List;

public interface OrdersDao {
	
	public void insertBean(Orders bean);
	
	public void deleteBean(int id);
	
	public void updateBean(Orders bean);

	//根据查询的sql查询数据
	public List<Orders> queryData(String sql);
}
