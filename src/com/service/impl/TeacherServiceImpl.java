package com.service.impl;

import com.dao.TeacherDao;
import com.entity.Teacher;
import com.service.TeacherService;
import com.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	
	@Resource
	private TeacherDao teacherDao;
	
	public void insertBean(Teacher bean) {
		teacherDao.insertBean(bean);
		
	}
	
	public void deleteBean(int id) {
		teacherDao.deleteBean(id);
		
	}
	
	public void updateBean(Teacher bean){
		
		teacherDao.updateBean(bean);
		
	}

	public List<Teacher> selectData(String sql){

		return teacherDao.queryData(sql);
	}
	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	@SuppressWarnings("unchecked")
	public Map<String,List<Teacher>> selectBeanMap(int currentpage,int	pagesize,String url,String sql){
		
		
		List<Teacher> list = this.selectData(sql);
		
		Pager pager = new Pager(list, pagesize);
		
		List<Teacher> pagerlist = pager.getObjects(currentpage);

		int total = list.size();
		
		
		Map<String,List<Teacher>> map = new HashMap<String,List<Teacher>>();
		
		map.put(Pager.getPagerNormal(total, pagesize,currentpage, url, "共有" + total + "条记录"), pagerlist);
		String pagerinfo = map.keySet().iterator().next();
		List<Teacher> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
		return map;
	}

	public Teacher selectBean(String sql){

		List<Teacher> list = this.selectData(sql);

		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}

	}

}











