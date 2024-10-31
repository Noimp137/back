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

import com.entity.User;
import com.service.UserService;


//用户
@Controller
@RequestMapping("/user")
public class UserController {

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



	
	//用户列表
	@RequestMapping("/userlist.do")
	public String userlist(HttpServletRequest request,String pagenum,String username){
		
		String url = "user/userlist.do";//当前访问的地址
		
		String pageurl = "user/userlist.do";//当前访问的地址
		
		//默认第一页
		int currentpage = 1;
		//获取当前页
		if (pagenum != null) {
			currentpage = Integer.parseInt(pagenum);
		}
		
		//组装查询的sql语句
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from t_user where  ");
		
		if (username != null && !"".equals(username)) {

			sb.append(" username like '%"+username+"%' ");
			sb.append(" and ");
			
			request.setAttribute("username", username);
		}
		

		sb.append(" role!=1 order by id desc ");
		
		String sql = sb.toString();
		

		//查询列表
		Map<String,List<User>> map = userService.selectBeanMap(currentpage,pageSize,url,sql);
		String pagerinfo = map.keySet().iterator().next();
		List<User> list = map.get(pagerinfo);

		//列表返回页面
		request.setAttribute("list", list);

		//分页信息返回页面
		request.setAttribute("pagerinfo", pagerinfo);

		//查询按钮
		request.setAttribute("url", url);

		//添加，更新，删除等按钮
		request.setAttribute("url2", "user/user");
		
		request.setAttribute("pageurl", pageurl);

		request.setAttribute("title", "账户查询");
		
		


		return "userlist.jsp";

	}

	
}












