package com.service.impl;

import com.dao.BookDao;
import com.entity.Book;
import com.service.BookService;
import com.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Resource
	private BookDao bookDao;
	
	public void insertBean(Book bean) {
		bookDao.insertBean(bean);
		
	}
	
	public void deleteBean(int id) {
		bookDao.deleteBean(id);
		
	}
	
	public void updateBean(Book bean){
		
		bookDao.updateBean(bean);
		
	}

	public List<Book> selectData(String sql){

		return bookDao.queryData(sql);
	}
	
	//支持数据的分页查询，currentpage表示当前页，pagesize表示每页显示的条数，url表示点击上一页或者下一页跳转的地址
	//sql表示查询的sql语句
	@SuppressWarnings("unchecked")
	public Map<String,List<Book>> selectBeanMap(int currentpage,int	pagesize,String url,String sql){
		
		
		List<Book> list = this.selectData(sql);
		
		Pager pager = new Pager(list, pagesize);
		
		List<Book> pagerlist = pager.getObjects(currentpage);

		int total = list.size();
		
		
		Map<String,List<Book>> map = new HashMap<String,List<Book>>();
		
		map.put(Pager.getPagerNormal(total, pagesize,currentpage, url, "共有" + total + "条记录"), pagerlist);
		String pagerinfo = map.keySet().iterator().next();
		List<Book> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
		return map;
	}

	public Book selectBean(String sql){

		List<Book> list = this.selectData(sql);

		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}

	}

}











