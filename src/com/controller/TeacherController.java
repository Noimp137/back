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
import com.entity.Teacher;
import com.entity.User;
import com.service.TeacherService;
import com.service.UserService;
import com.util.Util;


//教师信息
@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Resource
	private TeacherService teacherService;

	@Resource
	private UserService userService;
	
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



	
	//教师信息列表
	@RequestMapping("/teacherlist.do")
	public String teacherlist(HttpServletRequest request,String pagenum,String classname,String jobno,String cname){
		
		String url = "teacher/teacherlist.do";//当前访问的地址
		
		String pageurl = "teacher/teacherlist.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_teacher where  ");
		
		//查询条件返回页面
		if (jobno != null && !"".equals(jobno)) {

			sb.append(" jobno like '%"+jobno+"%' ");
			sb.append(" and ");
			
			request.setAttribute("jobno", jobno);
		}
		
		if (cname != null && !"".equals(cname)) {

			sb.append(" cname like '%"+cname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("cname", cname);
		}
		
		

		sb.append(" 1=1 order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<Teacher>> map = teacherService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<Teacher> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "teacher/teacher");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "教师信息管理");
		
		


		return "teacherlist.jsp";

	}


	//跳转到添加教师信息页面
	@RequestMapping("/teacheradd.do")
	public String teacheradd(HttpServletRequest request){
		
		request.setAttribute("url", "teacher/teacheradd2.do");

		request.setAttribute("title", "添加教师信息");

		return "teacheradd.jsp";

	}


	//添加教师信息操作
	@RequestMapping("/teacheradd2.do")
	public void teacheradd2(HttpServletResponse response,HttpServletRequest request,Teacher bean){
		
		PrintWriter writer = this.getPrintWriter(response);

		List<User> userlist = userService.selectData(" select * from t_user where username='"+bean.getJobno()+"' ");
		
		if(userlist.size()>0){
			writer.print("<script language=javascript>alert('操作失败，该工号已经存在');" +
			"window.location.href='teacherlist.do';</script>");
			return ;
		}

		
		bean.setCtime(Util.getTime());
		
		teacherService.insertBean(bean);
		
		User user = new User();
		user.setUsername(bean.getJobno());
		
		user.setCreatetime(Util.getTime());
		user.setName(bean.getName());
		user.setPassword("111111");
		user.setRole(2);
		
		userService.insertBean(user);



		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='teacherlist.do';</script>");

	}


	//跳转到修改教师信息页面
	@RequestMapping("/teacherupdate.do")
	public String teacherupdate(HttpServletRequest request,int id){

		Teacher bean = teacherService.selectData(" select * from t_teacher where id= "+id).get(0);
		
		request.setAttribute("bean", bean);
	
		request.setAttribute("url", "teacher/teacherupdate2.do?id="+id);

		request.setAttribute("title", "修改教师信息");

		return "teacherupdate.jsp";

	}

	//修改教师信息操作
	@RequestMapping("/teacherupdate2.do")
	public void teacherupdate2(HttpServletResponse response,HttpServletRequest request,Teacher bean){

		teacherService.updateBean(bean);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='teacherlist.do';</script>");
	}


	//删除操作
	@RequestMapping("/teacherdelete.do")
	public void teacherdelete(HttpServletResponse response,HttpServletRequest request,int id){
		
		Teacher bean = teacherService.selectData(" select * from t_teacher where id= "+id).get(0);
		
		List<User> userlist = userService.selectData(" select * from t_user where username='"+bean.getJobno()+"' ");
		
		if(userlist.size()>0){
			User user = userlist.get(0);
			
			userService.deleteBean(user.getId());
		}

		teacherService.deleteBean(id);
		
		PrintWriter writer = this.getPrintWriter(response);
		
		writer.print("<script language=javascript>alert('操作成功');" +
				"window.location.href='teacherlist.do';</script>");
	}



	//跳转到查看详情页面
	@RequestMapping("/teachershow.do")
	public String teachershow(HttpServletRequest request,int id){
		
		Teacher bean = teacherService.selectData(" select * from t_teacher where id= "+id).get(0);

		request.setAttribute("bean", bean);

		request.setAttribute("title", "查看详情");

		return "teachershow.jsp";

	}
	
	
	
}












