<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				
				<a class="brand" href="index.jsp"><span style="font-size: 18px;">教材管理系统</span></a>
				
				
				
				<!-- theme selector ends -->
				
				<!-- user dropdown starts -->
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone">${manage.username }</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="password.do">个人密码</a></li>
						<li class="divider"></li>
						<li><a href="loginout.do">安全退出</a></li>
					</ul>
				</div>
				
			</div>
		</div>
	</div>


