package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.User;

@Controller
@RequestMapping("/")
public class ManageController {

	@Resource
	private UserService userService;




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



	//登录
	@RequestMapping("/login.do")
	public void login(HttpServletRequest request,HttpServletResponse response,String username,String password){

		PrintWriter writer = this.getPrintWriter(response);

		User user = userService.userlogin(username, password);

		if(user==null){

			writer.print("<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");

		}else{
			HttpSession session = request.getSession();
			session.setAttribute("manage", user);

			writer.print("<script language=javascript>alert('登录成功');window.location.href='index.jsp';</script>");

		}

	}





	//安全退出
	@RequestMapping("/loginout.do")
	public void loginout(HttpServletRequest request,HttpServletResponse response,String username,String password){

		
		PrintWriter writer = this.getPrintWriter(response);

		HttpSession session = request.getSession();
		session.removeAttribute("manage");
		
		writer.print("<script language=javascript>alert('退出成功');window.location.href='login.jsp';</script>");

	}


	//跳转到修改密码页面
	@RequestMapping("/password.do")
	public String password(HttpServletRequest request){

		request.setAttribute("url", "password2.do");

		request.setAttribute("title", "修改密码");

		return "password.jsp";

	}

	//修改密码操作
	@RequestMapping("/password2.do")
	public void password2(HttpServletRequest request,HttpServletResponse response,String password1,String password2){

		PrintWriter writer = this.getPrintWriter(response);

		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("manage");

		User bean = userService.userlogin(user.getUsername(), password1);

		if(bean!=null){
			bean.setPassword(password2);
			userService.updateBean(bean);

			writer.print("<script language=javascript>alert('修改成功');window.location.href='password.do';</script>");


		}else{

			writer.print("<script language=javascript>alert('用户名或者密码错误');window.location.href='password.do';</script>");

		}
	}
	
	
	
	
	

}












