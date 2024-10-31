<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	
	<meta charset="utf-8">
	<title>教材管理系统</title>
	

	<%@ include file="css.jsp" %>

</head>

<body>
		<!-- topbar starts -->
		
	<%@ include file="head.jsp" %>
	
	<!-- topbar ends -->
	
		<div class="container-fluid">
		<c:if test="${manage.role==1}">
		<div class="row-fluid" style="background: url(beijing.jpg);">
		</c:if>	
		
		<c:if test="${manage.role==2}">
		<div class="row-fluid" >
		</c:if>	
		
			<!-- left menu starts -->
			
			<%@ include file="menu.jsp" %>
			
			<!--/span-->
			<!-- left menu ends -->
			
			
			
			<div id="content" class="span10">
			<!-- content starts -->
			
	<br/><br/><br/><br/><br/><br/><br/><br/>			
			
	<h1 class="title">
    <div align="center" style="font-size: 32px; color: black;">
    欢迎${manage.name }使用本平台
    <br/><br/>
    您的角色是
    <c:if test="${manage.role==1}">系统管理员</c:if>
    <c:if test="${manage.role==2}">教师</c:if>
   
    </div>
    </h1>
					
			

			
				  

		  
       
			<!-- content ends -->
			</div><!--/#content.span10-->
		
		
		</div><!--/fluid-row-->
				
		<hr>

		

		<footer  >
			
		</footer>
		
	</div><!--/.fluid-container-->

	<%@ include file="footer.jsp" %>
	
		
</body>
</html>

