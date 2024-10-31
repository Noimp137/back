package com.service.impl;

import com.dao.OrdersDao;
import com.entity.Orders;
import com.service.OrdersService;
import com.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
	
	@Resource
	private OrdersDao ordersDao;
	
	public void insertBean(Orders bean) {
		ordersDao.insertBean(bean);
		
	}
	
	public void deleteBean(int id) {
		ordersDao.deleteBean(id);
		
	}
	
	public void updateBean(Orders bean){
		
		ordersDao.updateBean(bean);
		
	}

	public List<Orders> selectData(String sql){

		return ordersDao.queryData(sql);
	}
	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	@SuppressWarnings("unchecked")
	public Map<String,List<Orders>> selectBeanMap(int currentpage,int	pagesize,String url,String sql){
		
		
		List<Orders> list = this.selectData(sql);
		
		Pager pager = new Pager(list, pagesize);
		
		List<Orders> pagerlist = pager.getObjects(currentpage);

		int total = list.size();
		
		
		Map<String,List<Orders>> map = new HashMap<String,List<Orders>>();
		
		map.put(Pager.getPagerNormal(total, pagesize,currentpage, url, "共有" + total + "条记录"), pagerlist);
		String pagerinfo = map.keySet().iterator().next();
		List<Orders> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
		return map;
	}

	public Orders selectBean(String sql){

		List<Orders> list = this.selectData(sql);

		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}

	}

}











