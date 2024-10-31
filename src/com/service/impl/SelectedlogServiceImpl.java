package com.service.impl;

import com.dao.SelectedlogDao;
import com.entity.Selectedlog;
import com.service.SelectedlogService;
import com.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("selectedlogService")
public class SelectedlogServiceImpl implements SelectedlogService {
	
	@Resource
	private SelectedlogDao selectedlogDao;
	
	public void insertBean(Selectedlog bean) {
		selectedlogDao.insertBean(bean);
		
	}
	
	public void deleteBean(int id) {
		selectedlogDao.deleteBean(id);
		
	}
	
	public void updateBean(Selectedlog bean){
		
		selectedlogDao.updateBean(bean);
		
	}

	public List<Selectedlog> selectData(String sql){

		return selectedlogDao.queryData(sql);
	}
	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	@SuppressWarnings("unchecked")
	public Map<String,List<Selectedlog>> selectBeanMap(int currentpage,int	pagesize,String url,String sql){
		
		
		List<Selectedlog> list = this.selectData(sql);
		
		Pager pager = new Pager(list, pagesize);
		
		List<Selectedlog> pagerlist = pager.getObjects(currentpage);

		int total = list.size();
		
		
		Map<String,List<Selectedlog>> map = new HashMap<String,List<Selectedlog>>();
		
		map.put(Pager.getPagerNormal(total, pagesize,currentpage, url, "共有" + total + "条记录"), pagerlist);
		String pagerinfo = map.keySet().iterator().next();
		List<Selectedlog> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
		return map;
	}

	public Selectedlog selectBean(String sql){

		List<Selectedlog> list = this.selectData(sql);

		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}

	}

}











