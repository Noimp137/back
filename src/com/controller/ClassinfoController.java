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

import com.entity.Classinfo;
import com.service.ClassinfoService;


//班级信息
@Controller
@RequestMapping("/classinfo")
public class ClassinfoController {

	@Resource
	private ClassinfoService classinfoService;

	
	
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



	
	//班级信息列表
	@RequestMapping("/classinfolist.do")
	public String classinfolist(HttpServletRequest request,String pagenum,String classname){
		
		String url = "classinfo/classinfolist.do";//当前访问的地址
		
		String pageurl = "classinfo/classinfolist.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_classinfo where  ");
		
		//查询条件返回页面
		if (classname != null && !"".equals(classname)) {

			sb.append(" classname like '%"+classname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("classname", classname);
		}
		

		sb.append(" 1=1 order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Classinfo>> map = classinfoService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Classinfo> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "classinfo/classinfo");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "班级信息管理");
		
		


		return "classinfolist.jsp";

	}


	//跳转到添加班级信息页面
	@RequestMapping("/classinfoadd.do")
	public String classinfoadd(HttpServletRequest request){

		request.setAttribute("url", "classinfo/classinfoadd2.do");

		request.setAttribute("title", "添加班级信息");

		return "classinfoadd.jsp";

	}


	//添加班级信息操作
	@RequestMapping("/classinfoadd2.do")
	public void classinfoadd2(HttpServletResponse response,HttpServletRequest request,Classinfo bean){
		
		PrintWriter writer = this.getPrintWriter(response);

		
		classinfoService.insertBean(bean);



		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='classinfolist.do';</script>");

	}


	//跳转到修改班级信息页面
	@RequestMapping("/classinfoupdate.do")
	public String classinfoupdate(HttpServletRequest request,int id){

		Classinfo bean = classinfoService.selectData(" select * from t_classinfo where id= "+id).get(0);
		
		request.setAttribute("bean", bean);
		
		request.setAttribute("url", "classinfo/classinfoupdate2.do?id="+id);

		request.setAttribute("title", "修改班级信息");

		return "classinfoupdate.jsp";

	}

	//修改班级信息操作
	@RequestMapping("/classinfoupdate2.do")
	public void classinfoupdate2(HttpServletResponse response,HttpServletRequest request,Classinfo bean){

		classinfoService.updateBean(bean);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='classinfolist.do';</script>");
	}


	//删除操作
	@RequestMapping("/classinfodelete.do")
	public void classinfodelete(HttpServletResponse response,HttpServletRequest request,int id){

		classinfoService.deleteBean(id);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='classinfolist.do';</script>");
	}



	//跳转到查看详情页面
	@RequestMapping("/classinfoshow.do")
	public String classinfoshow(HttpServletRequest request,int id){
		
		Classinfo bean = classinfoService.selectData(" select * from t_classinfo where id= "+id).get(0);

		request.setAttribute("bean", bean);

		request.setAttribute("title", "查看详情");

		return "classinfoshow.jsp";

	}
	
	
	
	
}












