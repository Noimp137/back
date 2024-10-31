package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Book;
import com.entity.Classinfo;
import com.entity.Selectedlog;
import com.entity.User;
import com.service.BookService;
import com.service.ClassinfoService;
import com.service.SelectedlogService;
import com.util.Util;


//教材选定
@Controller
@RequestMapping("/selectedlog")
public class SelectedlogController {

	@Resource
	private SelectedlogService selectedlogService;
	
	@Resource
	private ClassinfoService classinfoService;
	
	@Resource
	private BookService bookService;


	
	private final int pageSize =12;


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

	//跳转到添加教材选定页面
	@RequestMapping("/selectedlogadd.do")
	public String selectedlogadd(HttpServletRequest request,String bookid){
		
		Book book = bookService.selectBean(" select * from t_book where id= "+bookid);
		
		request.setAttribute("book", book);

		List<Classinfo> classinfolist = classinfoService.selectData(" select * from t_classinfo  ");
		
		request.setAttribute("classinfolist", classinfolist);

		request.setAttribute("url", "selectedlog/selectedlogadd2.do?bookid="+bookid);

		request.setAttribute("title", "教材选定");

		return "selectedlogadd.jsp";

	}


	//教材选定操作
	@RequestMapping("/selectedlogadd2.do")
	public void selectedlogadd2(HttpServletResponse response,HttpServletRequest request){
		
		PrintWriter writer = this.getPrintWriter(response);
		
		String bookid = request.getParameter("bookid");
		
		String classinfoid = request.getParameter("classinfoid");
		
		String snumber = request.getParameter("snumber");
		
		String result = request.getParameter("result");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("manage");
		
		Book book = bookService.selectBean(" select * from t_book where id= "+bookid);
		
		Classinfo classinfo = classinfoService.selectBean(" select * from t_classinfo where id= "+classinfoid);
		
		
		Selectedlog bean = selectedlogService.selectBean(" select * from t_selectedlog " +
				"where userid="+user.getId()+" and courseid="+book.getCourseid()+" and classinfoid="+classinfo.getId()+" and status!='审核不通过'  ");
		
		if(bean!=null){
			writer.print("<script language=javascript>alert('操作失败，您已经给"+classinfo.getClassname()+"的"+book.getCname()+"提交了教材申请');" +
			"window.location.href='selectedloglist.do';</script>");
			return;
		}
		
		if(Integer.parseInt(snumber)-classinfo.getStunumber()>5){
			writer.print("<script language=javascript>alert('操作失败，选定的教材不能超过班级总人数的5本');" +
			"window.location.href='../book/booklist2.do';</script>");
			return;
		}
		
		if(Integer.parseInt(snumber)<classinfo.getStunumber()){
			writer.print("<script language=javascript>alert('操作失败，选定的教材不能少于班级人数');" +
			"window.location.href='../book/booklist2.do';</script>");
			return;
		}
		
		
		
		bean = new Selectedlog();
		
		bean.setUserid(user.getId());
		bean.setUsername(user.getUsername());
		bean.setName(user.getName());
		
		bean.setCourseid(book.getCourseid());
		bean.setCname(book.getCname());
		bean.setBookid(book.getId());
		bean.setBnumber(book.getBnumber());
		bean.setBname(book.getBname());
		
		bean.setClassinfoid(classinfo.getId());
		bean.setCollege(classinfo.getCollege());
		bean.setMajor(classinfo.getMajor());
		bean.setSessioninfo(classinfo.getSessioninfo());
		bean.setClassname(classinfo.getClassname());
		
		bean.setSnumber(Integer.parseInt(snumber));
		bean.setStatus("未提交");
		bean.setResult(result);
		
		bean.setReceive(0);
		
		selectedlogService.insertBean(bean);



		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='selectedloglist.do';</script>");

	}
	
	
	
	
	
	//我的教材选定
	@RequestMapping("/selectedloglist.do")
	public String selectedloglist(HttpServletRequest request,String pagenum,String bnumber,String bname){
		
		String url = "selectedlog/selectedloglist.do";//当前访问的地址
		
		String pageurl = "selectedlog/selectedloglist.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_selectedlog where  ");
		
		//查询条件返回页面
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("manage");

		sb.append(" userid="+user.getId()+" order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Selectedlog>> map = selectedlogService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Selectedlog> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "selectedlog/selectedlog");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "我的教材选定");
		

		return "selectedloglist.jsp";

	}


	


	//跳转到修改选定数量页面
	@RequestMapping("/selectedlogupdate.do")
	public String selectedlogupdate(HttpServletRequest request,int id){

		Selectedlog bean = selectedlogService.selectData(" select * from t_selectedlog where id= "+id).get(0);
		
		request.setAttribute("bean", bean);

		request.setAttribute("url", "selectedlog/selectedlogupdate2.do?id="+id);

		request.setAttribute("title", "修改选定数量");

		return "selectedlogupdate.jsp";

	}

	//修改选定数量操作
	@RequestMapping("/selectedlogupdate2.do")
	public void selectedlogupdate2(HttpServletResponse response,HttpServletRequest request){

		String id = request.getParameter("id");
		
		String snumber = request.getParameter("snumber");
		
		String result = request.getParameter("result");
		
		Selectedlog bean = selectedlogService.selectData(" select * from t_selectedlog where id= "+id).get(0);
		
		Classinfo classinfo = classinfoService.selectBean(" select * from t_classinfo where id= "+bean.getClassinfoid());
		
		PrintWriter writer = this.getPrintWriter(response);
		
		if(Integer.parseInt(snumber)-classinfo.getStunumber()>5){
			writer.print("<script language=javascript>alert('操作失败，选定的教材不能超过则上不超过班级人数总数的5本');" +
			"window.location.href='selectedloglist.do';</script>");
			return;
		}
		
		bean.setSnumber(Integer.parseInt(snumber));
		
		bean.setResult(result);
		
		
		selectedlogService.updateBean(bean);
		
		
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='selectedloglist.do';</script>");
	}


	//删除操作
	@RequestMapping("/selectedlogdelete.do")
	public void selectedlogdelete(HttpServletResponse response,HttpServletRequest request,int id){

		selectedlogService.deleteBean(id);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='selectedloglist.do';</script>");
	}



	//跳转到查看详情页面
	@RequestMapping("/selectedlogshow.do")
	public String selectedlogshow(HttpServletRequest request,int id){
		
		Selectedlog bean = selectedlogService.selectData(" select * from t_selectedlog where id= "+id).get(0);

		request.setAttribute("bean", bean);

		request.setAttribute("title", "查看详情");

		return "selectedlogshow.jsp";

	}
	
	
	
	//提交审核操作
	@RequestMapping("/selectedlogdelete2.do")
	public void selectedlogdelete2(HttpServletResponse response,HttpServletRequest request,int id){

		Selectedlog bean = selectedlogService.selectData(" select * from t_selectedlog where id= "+id).get(0);

		bean.setStatus("审核中");
		
		bean.setCtime(Util.getTime());
		
		selectedlogService.updateBean(bean);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='selectedloglist.do';</script>");
	}

	
	
	//待审核教材选定
	@RequestMapping("/selectedloglist2.do")
	public String selectedloglist2(HttpServletRequest request,String pagenum,String name,String bnumber,String bname){
		
		String url = "selectedlog/selectedloglist2.do";//当前访问的地址
		
		String pageurl = "selectedlog/selectedloglist2.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_selectedlog where  ");
		
		//查询条件返回页面
		
		if (name != null && !"".equals(name)) {

			sb.append(" name like '%"+name+"%' ");
			sb.append(" and ");
			
			request.setAttribute("name", name);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		

		sb.append(" status='审核中'  order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Selectedlog>> map = selectedlogService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Selectedlog> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "selectedlog/selectedlog");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "待审核教材选定");
		

		return "selectedloglist2.jsp";

	}
	
	
	//跳转到审核教材选定页面
	@RequestMapping("/selectedlogupdate3.do")
	public String selectedlogupdate3(HttpServletRequest request,int id){

		Selectedlog bean = selectedlogService.selectData(" select * from t_selectedlog where id= "+id).get(0);
		
		request.setAttribute("bean", bean);

		request.setAttribute("url", "selectedlog/selectedlogupdate4.do?id="+id);

		request.setAttribute("title", "审核教材选定");

		return "selectedlogupdate3.jsp";

	}

	//审核教材选定操作
	@RequestMapping("/selectedlogupdate4.do")
	public void selectedlogupdate4(HttpServletResponse response,HttpServletRequest request){

		String id = request.getParameter("id");
		
		String feedback = request.getParameter("feedback");
		
		String status = request.getParameter("status");
	
		
		Selectedlog bean = selectedlogService.selectData(" select * from t_selectedlog where id= "+id).get(0);
		
		bean.setStime(Util.getTime());
		bean.setFeedback(feedback);
		
		bean.setStatus(status);
		
		
		selectedlogService.updateBean(bean);
		
		
		if("审核通过".equals(status)){
			
			Book book = bookService.selectBean(" select * from t_book where id= "+bean.getBookid());
			
			book.setTobeordered(book.getTobeordered()+bean.getSnumber());
			
			bookService.updateBean(book);
			
		}
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='selectedloglist2.do';</script>");
	}
	
	
	//教材选定查询
	@RequestMapping("/selectedloglist3.do")
	public String selectedloglist3(HttpServletRequest request,String pagenum,String name,String bnumber,String bname){
		
		String url = "selectedlog/selectedloglist3.do";//当前访问的地址
		
		String pageurl = "selectedlog/selectedloglist3.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_selectedlog where  ");
		
		//查询条件返回页面
		
		if (name != null && !"".equals(name)) {

			sb.append(" name like '%"+name+"%' ");
			sb.append(" and ");
			
			request.setAttribute("name", name);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		

		sb.append(" 1=1  order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Selectedlog>> map = selectedlogService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Selectedlog> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "selectedlog/selectedlog");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "教材选定查询");
		

		return "selectedloglist3.jsp";

	}
	
	
	
	//待领取教材
	@RequestMapping("/selectedloglist4.do")
	public String selectedloglist4(HttpServletRequest request,String pagenum,String name,String bnumber,String bname){
		
		String url = "selectedlog/selectedloglist4.do";//当前访问的地址
		
		String pageurl = "selectedlog/selectedloglist4.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_selectedlog where  ");
		
		//查询条件返回页面
		
		if (name != null && !"".equals(name)) {

			sb.append(" name like '%"+name+"%' ");
			sb.append(" and ");
			
			request.setAttribute("name", name);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		

		sb.append(" receive=0 and status='审核通过'  order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Selectedlog>> map = selectedlogService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Selectedlog> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "selectedlog/selectedlog");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "待领取教材");
		

		return "selectedloglist4.jsp";

	}
	
	
	//领取教材操作
	@RequestMapping("/selectedlogupdate5.do")
	public void selectedlogupdate5(HttpServletResponse response,HttpServletRequest request){
		
		PrintWriter writer = this.getPrintWriter(response);
		
		String id = request.getParameter("id");
		
		Selectedlog bean = selectedlogService.selectData(" select * from t_selectedlog where id= "+id).get(0);
		
		Book book = bookService.selectBean(" select * from t_book where id= "+bean.getBookid());
		
		if(bean.getSnumber()>book.getKucunsl()){
			writer.print("<script language=javascript>alert('操作失败，库存数量不足');" +
			"window.location.href='selectedloglist4.do';</script>");
			return;
		}
		
		bean.setReceive(1);
		
		bean.setReceivetime(Util.getTime());
		
		
		selectedlogService.updateBean(bean);
		
		
		book.setKucunsl(book.getKucunsl()-bean.getSnumber());
		
		bookService.updateBean(book);
		
		
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='selectedloglist4.do';</script>");
	}
	
	
	//领取记录查询
	@RequestMapping("/selectedloglist5.do")
	public String selectedloglist5(HttpServletRequest request,String pagenum,String name,String bnumber,String bname){
		
		String url = "selectedlog/selectedloglist5.do";//当前访问的地址
		
		String pageurl = "selectedlog/selectedloglist5.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_selectedlog where  ");
		
		//查询条件返回页面
		
		if (name != null && !"".equals(name)) {

			sb.append(" name like '%"+name+"%' ");
			sb.append(" and ");
			
			request.setAttribute("name", name);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		

		sb.append(" receive=1   order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Selectedlog>> map = selectedlogService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Selectedlog> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "selectedlog/selectedlog");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "领取记录查询");
		

		return "selectedloglist5.jsp";

	}
	
	
	//领取记录查询
	@RequestMapping("/selectedloglist6.do")
	public String selectedloglist6(HttpServletRequest request,String pagenum,String name,String bnumber,String bname){
		
		String url = "selectedlog/selectedloglist6.do";//当前访问的地址
		
		String pageurl = "selectedlog/selectedloglist6.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_selectedlog where  ");
		
		//查询条件返回页面
		
		if (name != null && !"".equals(name)) {

			sb.append(" name like '%"+name+"%' ");
			sb.append(" and ");
			
			request.setAttribute("name", name);
		}
		
		if (bname != null && !"".equals(bname)) {

			sb.append(" bname like '%"+bname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bname", bname);
		}
		
		
		if (bnumber != null && !"".equals(bnumber)) {

			sb.append(" bnumber like '%"+bnumber+"%' ");
			sb.append(" and ");
			
			request.setAttribute("bnumber", bnumber);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("manage");

		sb.append(" receive=1  and userid="+user.getId()+"  order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Selectedlog>> map = selectedlogService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Selectedlog> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "selectedlog/selectedlog");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "领取记录查询");
		

		return "selectedloglist6.jsp";

	}
	
	
	
}












