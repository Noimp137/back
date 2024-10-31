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
import com.entity.Course;
import com.service.CourseService;


//课程信息
@Controller
@RequestMapping("/course")
public class CourseController {

	@Resource
	private CourseService courseService;

	
	
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



	
	//课程信息列表
	@RequestMapping("/courselist.do")
	public String courselist(HttpServletRequest request,String pagenum,String cname){
		
		String url = "course/courselist.do";//当前访问的地址
		
		String pageurl = "course/courselist.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_course where  ");
		
		//查询条件返回页面
		if (cname != null && !"".equals(cname)) {

			sb.append(" cname like '%"+cname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("cname", cname);
		}
		

		sb.append(" 1=1 order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Course>> map = courseService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Course> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "course/course");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "课程信息管理");

		return "courselist.jsp";

	}


	//跳转到添加课程信息页面
	@RequestMapping("/courseadd.do")
	public String courseadd(HttpServletRequest request){

		request.setAttribute("url", "course/courseadd2.do");

		request.setAttribute("title", "添加课程信息");

		return "courseadd.jsp";

	}


	//添加课程信息操作
	@RequestMapping("/courseadd2.do")
	public void courseadd2(HttpServletResponse response,HttpServletRequest request,Course bean){
		
		PrintWriter writer = this.getPrintWriter(response);

		courseService.insertBean(bean);



		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='courselist.do';</script>");

	}


	//跳转到修改课程信息页面
	@RequestMapping("/courseupdate.do")
	public String courseupdate(HttpServletRequest request,int id){

		Course bean = courseService.selectData(" select * from t_course where id= "+id).get(0);
		
		request.setAttribute("bean", bean);
		
		request.setAttribute("url", "course/courseupdate2.do?id="+id);

		request.setAttribute("title", "修改课程信息");

		return "courseupdate.jsp";

	}

	//修改课程信息操作
	@RequestMapping("/courseupdate2.do")
	public void courseupdate2(HttpServletResponse response,HttpServletRequest request,Course bean){

		courseService.updateBean(bean);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='courselist.do';</script>");
	}


	//删除操作
	@RequestMapping("/coursedelete.do")
	public void coursedelete(HttpServletResponse response,HttpServletRequest request,int id){

		courseService.deleteBean(id);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='courselist.do';</script>");
	}



	//跳转到查看详情页面
	@RequestMapping("/courseshow.do")
	public String courseshow(HttpServletRequest request,int id){
		
		Course bean = courseService.selectData(" select * from t_course where id= "+id).get(0);

		request.setAttribute("bean", bean);

		request.setAttribute("title", "查看详情");

		return "courseshow.jsp";

	}
	
	
	
}












