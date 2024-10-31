package com.service.impl;

import com.dao.ClassinfoDao;
import com.entity.Classinfo;
import com.service.ClassinfoService;
import com.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("classinfoService")
public class ClassinfoServiceImpl implements ClassinfoService {
	
	@Resource
	private ClassinfoDao classinfoDao;
	
	public void insertBean(Classinfo bean) {
		classinfoDao.insertBean(bean);
		
	}
	
	public void deleteBean(int id) {
		classinfoDao.deleteBean(id);
		
	}
	
	public void updateBean(Classinfo bean){
		
		classinfoDao.updateBean(bean);
		
	}

	public List<Classinfo> selectData(String sql){

		return classinfoDao.queryData(sql);
	}
	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	@SuppressWarnings("unchecked")
	public Map<String,List<Classinfo>> selectBeanMap(int currentpage,int	pagesize,String url,String sql){
		
		
		List<Classinfo> list = this.selectData(sql);
		
		Pager pager = new Pager(list, pagesize);
		
		List<Classinfo> pagerlist = pager.getObjects(currentpage);

		int total = list.size();
		
		
		Map<String,List<Classinfo>> map = new HashMap<String,List<Classinfo>>();
		
		map.put(Pager.getPagerNormal(total, pagesize,currentpage, url, "共有" + total + "条记录"), pagerlist);
		String pagerinfo = map.keySet().iterator().next();
		List<Classinfo> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
		return map;
	}

	public Classinfo selectBean(String sql){

		List<Classinfo> list = this.selectData(sql);

		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}

	}

}











