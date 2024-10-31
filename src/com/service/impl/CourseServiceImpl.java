package com.service.impl;

import com.dao.CourseDao;
import com.entity.Course;
import com.service.CourseService;
import com.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("courseService")
public class CourseServiceImpl implements CourseService {
	
	@Resource
	private CourseDao courseDao;
	
	public void insertBean(Course bean) {
		courseDao.insertBean(bean);
		
	}
	
	public void deleteBean(int id) {
		courseDao.deleteBean(id);
		
	}
	
	public void updateBean(Course bean){
		
		courseDao.updateBean(bean);
		
	}

	public List<Course> selectData(String sql){

		return courseDao.queryData(sql);
	}
	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	@SuppressWarnings("unchecked")
	public Map<String,List<Course>> selectBeanMap(int currentpage,int	pagesize,String url,String sql){
		
		
		List<Course> list = this.selectData(sql);
		
		Pager pager = new Pager(list, pagesize);
		
		List<Course> pagerlist = pager.getObjects(currentpage);

		int total = list.size();
		
		
		Map<String,List<Course>> map = new HashMap<String,List<Course>>();
		
		map.put(Pager.getPagerNormal(total, pagesize,currentpage, url, "共有" + total + "条记录"), pagerlist);
		String pagerinfo = map.keySet().iterator().next();
		List<Course> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
		return map;
	}

	public Course selectBean(String sql){

		List<Course> list = this.selectData(sql);

		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}

	}

}











