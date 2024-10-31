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

import com.entity.Book;
import com.entity.Orders;
import com.service.BookService;
import com.service.OrdersService;
import com.util.Util;


//订购信息
@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Resource
	private OrdersService ordersService;

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

	//跳转到添加订购信息页面
	@RequestMapping("/ordersadd.do")
	public String ordersadd(HttpServletRequest request,String bookid){

		Book book = bookService.selectBean(" select * from t_book where id= "+bookid);
		
		request.setAttribute("book", book);
		
		request.setAttribute("url", "orders/ordersadd2.do?bookid="+bookid);

		request.setAttribute("title", "添加订购信息");

		return "ordersadd.jsp";

	}


	//添加订购信息操作
	@RequestMapping("/ordersadd2.do")
	public void ordersadd2(HttpServletResponse response,HttpServletRequest request,Orders bean){
		
		PrintWriter writer = this.getPrintWriter(response);

		Book book = bookService.selectBean(" select * from t_book where id= "+bean.getBookid());
		
		if(bean.getOrdernumber()>book.getTobeordered()){
			writer.print("<script language=javascript>alert('操作失败，订购数量不能超过待订购数量');" +
			"window.location.href='../book/booklist3.do';</script>");
			return;

		}
		
	
		bean.setBnumber(book.getBnumber());
		
		bean.setBname(book.getBname());
		
		bean.setBcategory(book.getBcategory());
		
		bean.setPress(book.getPress());
		
		bean.setCtime(Util.getTime());
		
		bean.setStatus("未订购");
		
		ordersService.insertBean(bean);
		
		
		book.setTobeordered(book.getTobeordered()-bean.getOrdernumber());
		
		bookService.updateBean(book);



		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='orderslist.do';</script>");

	}


	
	//订购信息列表
	@RequestMapping("/orderslist.do")
	public String orderslist(HttpServletRequest request,String pagenum,String bnumber,String bname){
		
		String url = "orders/orderslist.do";//当前访问的地址
		
		String pageurl = "orders/orderslist.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_orders where  ");
		
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
		

		sb.append(" 1=1 order by status desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Orders>> map = ordersService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Orders> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "orders/orders");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "订购信息管理");
		
		


		return "orderslist.jsp";

	}


	


	//确认订购操作
	@RequestMapping("/ordersupdate2.do")
	public void ordersupdate2(HttpServletResponse response,HttpServletRequest request,int id){

		Orders bean = ordersService.selectData(" select * from t_orders where id= "+id).get(0);
		
		bean.setStatus("已订购");
		
		bean.setStorageStatus("未入库");
		
		ordersService.updateBean(bean);
		
		Book book = bookService.selectBean(" select * from t_book where id= "+bean.getBookid());
		
		book.setOrdered(book.getOrdered()+bean.getOrdernumber());
		
		bookService.updateBean(book);
		
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='orderslist.do';</script>");
	}


	//取消订购操作
	@RequestMapping("/ordersdelete.do")
	public void ordersdelete(HttpServletResponse response,HttpServletRequest request,int id){
		
		Orders bean = ordersService.selectData(" select * from t_orders where id= "+id).get(0);

		ordersService.deleteBean(id);
		
		Book book = bookService.selectBean(" select * from t_book where id= "+bean.getBookid());
		
		book.setTobeordered(book.getTobeordered()+bean.getOrdernumber());
		
		bookService.updateBean(book);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='orderslist.do';</script>");
	}



	//跳转到查看详情页面
	@RequestMapping("/ordersshow.do")
	public String ordersshow(HttpServletRequest request,int id){
		
		Orders bean = ordersService.selectData(" select * from t_orders where id= "+id).get(0);

		request.setAttribute("bean", bean);

		request.setAttribute("title", "查看详情");

		return "ordersshow.jsp";

	}
	
	
	//教材入库管理
	@RequestMapping("/orderslist2.do")
	public String orderslist2(HttpServletRequest request,String pagenum,String bnumber,String bname){
		
		String url = "orders/orderslist2.do";//当前访问的地址
		
		String pageurl = "orders/orderslist2.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_orders where  ");
		
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
		

		sb.append(" status='已订购' order by storageStatus desc  ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Orders>> map = ordersService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Orders> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "orders/orders");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "教材入库管理");
		
		


		return "orderslist2.jsp";

	}
	
	
	//确认入库操作
	@RequestMapping("/ordersupdate3.do")
	public void ordersupdate3(HttpServletResponse response,HttpServletRequest request,int id){

		Orders bean = ordersService.selectData(" select * from t_orders where id= "+id).get(0);
		
		bean.setStoragetime(Util.getTime());
		
		bean.setStorageStatus("已入库");
		
		ordersService.updateBean(bean);
		
		Book book = bookService.selectBean(" select * from t_book where id= "+bean.getBookid());
		
		book.setKucunsl(book.getKucunsl()+bean.getOrdernumber());
		
		bookService.updateBean(book);
		
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='orderslist2.do';</script>");
	}
	
	
	
	
	
}












