package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.entity.Book;
import com.entity.Course;
import com.service.BookService;
import com.service.CourseService;
import com.util.Util;


//教材
@Controller
@RequestMapping("/book")
public class BookController {

	@Resource
	private BookService bookService;

	@Resource
	private CourseService courseService;
	
	

	private final int pageSize = 12;


	// 获取输出对象
	public PrintWriter getPrintWriter(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return writer;
	}


	
	//教材信息列表
	@RequestMapping("/booklist.do")
	public String booklist(HttpServletRequest request,String pagenum,String bnumber,String bname,String cname){
		
		String url = "book/booklist.do";//当前访问的地址
		
		String pageurl = "book/booklist.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_book where  ");
		
		//查询条件返回页面
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (cname != null && !"".equals(cname)) {

			sb.append(" cname like '%"+cname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("cname", cname);
		}
		
	

		sb.append(" 1=1 order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Book>> map = bookService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Book> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "book/book");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "教材信息管理");
		

		return "booklist.jsp";

	}


	//跳转到添加教材信息页面
	@RequestMapping("/bookadd.do")
	public String bookadd(HttpServletRequest request){
		
		List<Course> courselist = courseService.selectData(" select * from t_course   ");
		
		request.setAttribute("courselist", courselist);

		request.setAttribute("url", "book/bookadd2.do");

		request.setAttribute("title", "添加教材信息");

		return "bookadd.jsp";

	}


	//添加教材信息操作
	@RequestMapping("/bookadd2.do")
	public void bookadd2(HttpServletResponse response,HttpServletRequest request,Book bean,MultipartFile uploadfile){
		
		PrintWriter writer = this.getPrintWriter(response);
		
		
		Book book = bookService.selectBean(" select * from t_book where bnumber='"+bean.getBnumber()+"' ");

		if(book!=null){
			writer.print("<script language=javascript>alert('操作失败，该教材编号已经存在');" +
			"window.location.href='booklist.do';</script>");
			return;
		}
		
		
		
		if(uploadfile!=null && uploadfile.getSize()>0){
			String pic =  Util.uploadFile(request, uploadfile);

			bean.setPic(pic);
		}
		
		
		Course course = courseService.selectBean(" select * from t_course where id= "+bean.getCourseid());
		
		bean.setCname(course.getCname());
		
		bean.setKucunsl(0);
		
		bean.setTobeordered(0);
		bean.setOrdered(0);
		
		
		bookService.insertBean(bean);



		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='booklist.do';</script>");

	}


	//跳转到修改教材信息页面
	@RequestMapping("/bookupdate.do")
	public String bookupdate(HttpServletRequest request,int id){

		Book bean = bookService.selectData(" select * from t_book where id= "+id).get(0);
		
		request.setAttribute("bean", bean);
		
		List<Course> courselist = courseService.selectData(" select * from t_course   ");
		
		request.setAttribute("courselist", courselist);
		
		request.setAttribute("url", "book/bookupdate2.do?id="+id);

		request.setAttribute("title", "修改教材信息");

		return "bookupdate.jsp";

	}

	//修改教材信息操作
	@RequestMapping("/bookupdate2.do")
	public void bookupdate2(HttpServletResponse response,HttpServletRequest request,Book bean,MultipartFile uploadfile){

		
		if(uploadfile!=null && uploadfile.getSize()>0){
			String pic =  Util.uploadFile(request, uploadfile);

			bean.setPic(pic);
		}
		
		Course course = courseService.selectBean(" select * from t_course where id= "+bean.getCourseid());
		
		bean.setCname(course.getCname());
		
		bean.setKucunsl(0);
		
		
		bookService.updateBean(bean);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='booklist.do';</script>");
	}


	//删除操作
	@RequestMapping("/bookdelete.do")
	public void bookdelete(HttpServletResponse response,HttpServletRequest request,int id){

		bookService.deleteBean(id);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='booklist.do';</script>");
	}



	//跳转到查看详情页面
	@RequestMapping("/bookshow.do")
	public String bookshow(HttpServletRequest request,int id){
		
		Book bean = bookService.selectData(" select * from t_book where id= "+id).get(0);

		request.setAttribute("bean", bean);

		request.setAttribute("title", "查看详情");

		return "bookshow.jsp";

	}
	
	
	
	//选定任课教材
	@RequestMapping("/booklist2.do")
	public String booklist2(HttpServletRequest request,String pagenum,String bnumber,String bname,String cname){
		
		String url = "book/booklist2.do";//当前访问的地址
		
		String pageurl = "book/booklist2.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_book where  ");
		
		//查询条件返回页面
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (cname != null && !"".equals(cname)) {

			sb.append(" cname like '%"+cname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("cname", cname);
		}
		
		

		sb.append(" 1=1 order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Book>> map = bookService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Book> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "book/book");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "选定任课教材");
		

		return "booklist2.jsp";

	}
	
	
	//教材订购列表
	@RequestMapping("/booklist3.do")
	public String booklist3(HttpServletRequest request,String pagenum,String bnumber,String bname,String cname){
		
		String url = "book/booklist3.do";//当前访问的地址
		
		String pageurl = "book/booklist3.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_book where  ");
		
		//查询条件返回页面
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (cname != null && !"".equals(cname)) {

			sb.append(" cname like '%"+cname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("cname", cname);
		}
		
	

		sb.append(" 1=1 order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Book>> map = bookService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Book> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "book/book");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "订购教材");
		

		return "booklist3.jsp";

	}
	
	
	//教材库存管理
	@RequestMapping("/booklist4.do")
	public String booklist4(HttpServletRequest request,String pagenum,String bnumber,String bname,String cname){
		
		String url = "book/booklist4.do";//当前访问的地址
		
		String pageurl = "book/booklist4.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_book where  ");
		
		//查询条件返回页面
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (cname != null && !"".equals(cname)) {

			sb.append(" cname like '%"+cname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("cname", cname);
		}
		
	

		sb.append(" 1=1 order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Book>> map = bookService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Book> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "book/book");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "教材库存管理");
		

		return "booklist4.jsp";

	}
	
	
	
}












